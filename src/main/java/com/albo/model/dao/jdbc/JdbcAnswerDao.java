package com.albo.model.dao.jdbc;

import com.albo.exception.JdbcException;
import com.albo.model.Answer;
import com.albo.model.Poll;
import com.albo.model.Question;
import com.albo.model.User;
import com.albo.model.dao.AnswerDao;
import com.albo.model.dao.ConnectionFactory;
import com.albo.model.dao.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcAnswerDao implements AnswerDao {
    ConnectionFactory connectionFactory;

    public JdbcAnswerDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void create(Answer answer) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO answer (user_id,question_id,answer,answer_date) " +
                            "VALUES (?,?,?,?);"
            );
            preparedStatement.setInt(1, answer.getUser().getId());
            preparedStatement.setInt(2, answer.getQuestion().getId());
            preparedStatement.setString(3, answer.getAnswer());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(answer.getDate()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
    }

    @Override
    public Answer getBy(int id) {
        Answer answer = null;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT answer.id,answer.answer,answer.answer_date,users.*,question.id,question.question_text " +
                            "FROM answer LEFT JOIN users ON answer.user_id=users.id " +
                            "LEFT JOIN question ON answer.question_id=question.id " +
                            "WHERE answer.id =" + id
            );
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                answer = new Answer(result.getInt(1), new User(result.getInt(4),
                        result.getString(5), result.getString(6), result.getBoolean(7),
                        result.getString(8), result.getString(9), result.getString(10),
                        result.getTimestamp(11).toLocalDateTime()), new Question(result.getInt(12),
                        result.getString(13)), result.getString(2),
                        result.getTimestamp(3).toLocalDateTime());
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            troubles.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Answer> getListOfAnswersByUserId(int userId) throws DaoException {
        List<Answer> answers = new LinkedList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT answer.*, question.id, question.question_text FROM answer LEFT JOIN question ON " +
                            "answer.question_id = question.id WHERE answer.user_id = " + userId +
                    " ORDER BY answer.question_id "
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                answers.add(new Answer(resultSet.getInt(1),new User(resultSet.getInt(2)),
                        new Question(resultSet.getInt(3),resultSet.getString(7)),
                        resultSet.getString(4),resultSet.getTimestamp(5).toLocalDateTime()));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException troubles) {
            throw new JdbcException("Can't get answers.",troubles);
        }
        return answers;
    }
}
