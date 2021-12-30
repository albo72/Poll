package com.albo.model.dao.jdbc;

import com.albo.exception.JdbcException;
import com.albo.model.Answer;
import com.albo.model.User;
import com.albo.model.dao.ConnectionFactory;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JdbcUserDao implements UserDao {
    ConnectionFactory connectionFactory;

    public JdbcUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User save(User user) throws DaoException {
        try (Connection connection = connectionFactory.getConnection()) {
            int id = 0;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT nextval('users_id_seq')");
            result.next();
            id = result.getInt(1);
            statement.close();
            user.setId(id);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (id,login,password,is_admin,first_name,last_name,email,date_joined) " +
                            "VALUES (?,?,?,?,?,?,?,?);"
            );
            setValuesInUserDaoWithId(user, preparedStatement);
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't create a user. This login already exists.", troubles);
        }
    }

    @Override
    public User saveAnonymousUser(User user) throws DaoException {
        try (Connection connection = connectionFactory.getConnection()) {
            int id = 0;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT nextval('users_id_seq')");
            result.next();
            id = result.getInt(1);
            statement.close();
            user.setId(id);
            user.setLogin(String.valueOf(id));
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (id,login,password,date_joined) " +
                            "VALUES (?,?,?,?);"
            );
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,String.valueOf(id));
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(user.getDateJoined()));
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't create a user.", troubles);
        }
    }

    @Override
    public void update(User user) throws JdbcException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET login=?," +
                            "password=?," +
                            "first_name=?," +
                            "last_name=?," +
                            "email=? " +
                            "WHERE id=" + user.getId()
            );
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't update user");
        }
    }

    private void setValuesInUserDaoWithId(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setBoolean(4, user.getIsAdmin());
        preparedStatement.setString(5, user.getFirstName());
        preparedStatement.setString(6, user.getLastName());
        preparedStatement.setString(7, user.getEmail());
        preparedStatement.setTimestamp(8, Timestamp.valueOf(user.getDateJoined()));
    }

    private void setValuesInUserDaoWithoutId(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.getIsAdmin());
        preparedStatement.setString(4, user.getFirstName());
        preparedStatement.setString(5, user.getLastName());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(user.getDateJoined()));
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id=" + id
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getBoolean(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getTimestamp(8).toLocalDateTime());
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
        return user;
    }


    @Override
    public User getByLogin(String login) throws DaoException {
        User user = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE login = " + login
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getBoolean(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getTimestamp(8).toLocalDateTime());
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't find user. This login doesn't exist.", troubles);
        }
        return user;
    }


    @Override
    public User getByLoginAndPassword(String login, String password) throws JdbcException {
        User user = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE login = ? AND password = ?"
            );
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getBoolean(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getTimestamp(8).toLocalDateTime());
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't find user. Wrong login or password", troubles);
        }
        return user;
    }

    @Override
    public List<User> getListOfUsersByPollId(int pollId) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT distinct (users.id), users.login, users.first_name, users.last_name, users.email, users.date_joined " +
                            "FROM answer LEFT JOIN question ON answer.question_id=question.id " +
                            "LEFT JOIN users ON answer.user_id = users.id WHERE question.poll_id = " + pollId +
                            " ORDER BY users.id "
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get users who have passed the poll.",troubles);
        }
        return users;
    }
}
