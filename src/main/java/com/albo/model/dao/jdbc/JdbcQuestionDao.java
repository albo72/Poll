package com.albo.model.dao.jdbc;

import com.albo.model.Poll;
import com.albo.model.Question;
import com.albo.model.dao.ConnectionFactory;
import com.albo.model.dao.DaoException;
import com.albo.model.dao.QuestionDao;

import java.sql.*;
import java.util.List;

public class JdbcQuestionDao implements QuestionDao {
    ConnectionFactory connectionFactory;

    public JdbcQuestionDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void create(Question question, Poll poll) throws DaoException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO question (poll_id,question_text) " +
                            "VALUES (?,?);"
            );
            preparedStatement.setInt(1, poll.getId());
            preparedStatement.setString(2, question.getQuestionName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't create a question.", troubles);
        }
    }

    @Override
    public void createListOfQuestions(List<Question> questions, Poll poll) throws DaoException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO question (poll_id,question_text) " +
                            "VALUES (?,?);"
            );
            for (int i = 0; i < questions.size(); i++) {
                preparedStatement.setInt(1, poll.getId());
                preparedStatement.setString(2, questions.get(i).getQuestionName());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("Can't create questions.", troubles);
        }
    }

    @Override
    public void update(Question question, Poll poll) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE question " +
                            "SET poll_id=?," +
                            "question_text=?" +
                            "WHERE id=" + question.getId()
            );
            preparedStatement.setInt(1, poll.getId());
            preparedStatement.setString(2, question.getQuestionName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
    }

    @Override
    public void updateListOfQuestions(List<Question> questions, Poll poll) {

    }

    @Override
    public Question getBy(int id) {
        Question question = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM question WHERE id=" + id
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                question = new Question(result.getInt(1), result.getString(3));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
        return question;
    }

    @Override
    public void deleteBy(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM question WHERE id=" + id);
            statement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
    }

    @Override
    public void deleteByPollId(int pollId) throws DaoException {
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM question WHERE poll_id=" + pollId);
            statement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new DaoException("can' delete questions. dao exception");
        }
    }
}