package com.albo.model.dao.jdbc;

import com.albo.exception.JdbcException;
import com.albo.model.entities.Poll;
import com.albo.model.builders.PollBuilder;
import com.albo.model.entities.Question;
import com.albo.model.dao.ConnectionFactory;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.PollDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPollDao implements PollDao {
    ConnectionFactory connectionFactory;

    public JdbcPollDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Poll createAndGet(Poll poll) throws DaoException {
        Poll newPoll = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO poll (poll_name,description,date_start,date_end,is_active)" +
                            "VALUES (?,?,?,?,?);"
            );
            setValuesInPollDao(poll, preparedStatement);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM poll WHERE " +
                            "poll_name=? AND " +
                            "description=? AND " +
                            "date_start=? AND " +
                            "date_end=? AND " +
                            "is_active=?"
            );
            setValuesInPollDao(poll,preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            newPoll = new PollBuilder().withId(result.getInt(1)).withName(result.getString(2)).
                    withDescription(result.getString(3)).
                    withDateStart(result.getTimestamp(4).toLocalDateTime()).
                    withDateEnd(result.getTimestamp(6).toLocalDateTime()).
                    withActivity(result.getBoolean(5)).build();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't create new poll. Jdbc exception",troubles);
        }
        return newPoll;
    }

    @Override
    public void update(Poll poll) throws JdbcException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE poll " +
                            "SET poll_name=?," +
                            "description=?," +
                            "date_start=?," +
                            "date_end=?," +
                            "is_active=?" +
                            "WHERE id=" + poll.getId()
            );
            setValuesInPollDao(poll, preparedStatement);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't update poll. Jdbc exception", troubles);
        }
    }

    @Override
    public void updateActivity(Poll poll, boolean activity) throws JdbcException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE poll " +
                            "SET is_active=?" +
                            "WHERE id=" + poll.getId()
            );
            preparedStatement.setBoolean(1, activity);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't update activity. Jdbc exception",troubles);
        }
    }

    @Override
    public Poll getBy(int id) throws JdbcException {
        Poll poll = null;
        List<Question> questions = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT poll.* FROM poll left join question " +
                            "ON poll.id=question.poll_id " +
                            "WHERE poll.id=" + id
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                questions.add(new Question(result.getInt(7), result.getString(8)));
                if (result.isLast()) {
                    poll = new Poll(result.getInt(1), result.getString(2),
                            result.getString(3), result.getTimestamp(4).toLocalDateTime(),
                            result.getTimestamp(6).toLocalDateTime(), result.getBoolean(5),
                            questions);
                }
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("No poll with this id or server exception. Jdbc exception", troubles);
        }
        return poll;
    }

    private void setValuesInPollDao(Poll poll, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, poll.getName());
        preparedStatement.setString(2, poll.getDescription());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(poll.getDateStart()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(poll.getDateEnd()));
        preparedStatement.setBoolean(5, poll.isActivity());
    }

    @Override
    public List<Poll> getAllPolls() throws JdbcException {
        List<Poll> polls = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM poll "
            );
            getResultsForListOfPolls(polls, preparedStatement);
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get active polls. Jdbc exception",troubles);
        }
        return polls;
    }

    @Override
    public List<Poll> getAllPollsWithLimitAndOffset(int limit, int offset) throws JdbcException {
        List<Poll> polls = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM poll LIMIT " + limit + " OFFSET " + offset
            );
            getResultsForListOfPolls(polls, preparedStatement);
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get active polls. Jdbc exception",troubles);
        }
        return polls;
    }

    @Override
    public List<Poll> getActivePolls() throws JdbcException {
        List<Poll> activePolls = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM poll WHERE date_end >= CURRENT_TIMESTAMP"
            );
            getResultsForListOfPolls(activePolls, preparedStatement);
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get active polls. Jdbc exception",troubles);
        }
        return activePolls;
    }

    @Override
    public List<Poll> getInactivePolls() throws JdbcException {
        List<Poll> inactivePolls = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM poll WHERE is_active=" + false +
                            " OR date_end < CURRENT_TIMESTAMP "
            );
            getResultsForListOfPolls(inactivePolls, preparedStatement);
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get inactive polls. Jdbc exception",troubles);
        }
        return inactivePolls;
    }

    private void getResultsForListOfPolls(List<Poll> polls, PreparedStatement preparedStatement) throws SQLException {
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            polls.add(new PollBuilder().withId(result.getInt(1)).withName(result.getString(2)).
                    withDescription(result.getString(3)).
                    withDateStart(result.getTimestamp(4).toLocalDateTime()).
                    withDateEnd(result.getTimestamp(6).toLocalDateTime()).
                    withActivity(result.getBoolean(5)).build());
        }
        preparedStatement.close();
    }

    @Override
    public void deleteBy(int id) throws JdbcException {
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM poll WHERE id=" + id);
            statement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("can't delete poll. Jdbc exception", troubles);
        }
    }

    @Override
    public int getCountOfAllPolls() throws JdbcException {
        int countOfPolls;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(id) FROM poll"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            countOfPolls = resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get count of polls. Jdbc exception",troubles);
        }
        return countOfPolls;
    }
}
