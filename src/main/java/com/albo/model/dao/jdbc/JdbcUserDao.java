package com.albo.model.dao.jdbc;

import com.albo.exception.JdbcException;
import com.albo.model.entities.User;
import com.albo.model.dao.ConnectionFactory;
import com.albo.model.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    ConnectionFactory connectionFactory;

    public JdbcUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User save(User user) throws JdbcException {
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
            throw new JdbcException("Can't create a user. This login already exists. Jdbc exception", troubles);
        }
    }

    @Override
    public User saveAnonymousUser(User user) throws JdbcException {
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
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getDateJoined()));
            preparedStatement.execute();
            preparedStatement.close();
            return user;
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't create a user. Jdbc exception", troubles);
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
            throw new JdbcException("Can't update user. Jdbc exception", troubles);
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

    @Override
    public User getById(int id) throws JdbcException {
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
            throw new JdbcException("Can't get user by id. Jdbc exception", troubles);
        }
        return user;
    }


    @Override
    public User getByLogin(String login) throws JdbcException {
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
            throw new JdbcException("Can't find user. This login doesn't exist. Jdbc exception", troubles);
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
            throw new JdbcException("Can't find user. Wrong login or password. Jdbc exception", troubles);
        }
        return user;
    }

    @Override
    public List<User> getListOfUsersByPollId(int pollId) throws JdbcException {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT distinct (users.id), users.* " +
                            "FROM answer LEFT JOIN question ON answer.question_id=question.id " +
                            "LEFT JOIN users ON answer.user_id = users.id WHERE question.poll_id = " + pollId +
                            " ORDER BY users.id "
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt(2), result.getString(3),
                        result.getString(4), result.getBoolean(5),
                        result.getString(6), result.getString(7),
                        result.getString(8), result.getTimestamp(9).toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get users who have passed the poll. Jdbc exception", troubles);
        }
        return users;
    }
}
