package com.eugene.crude.crude.practic.repository.jdbc;


import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.utils.JDBSConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private final String sqlSelectAllPost = "SELECT * FROM post ";
    private final String sqlAllpostById = "SELECT * FROM post WHERE post_id=? ";
    private final String sqlInsertName = "INSERT INTO post (name) VALUES (?) ";
    private final String sqlSelectIdByName = "SELECT post_id FROM post WHERE name=?  ";
    private final String sqlUpdateNameById = "UPDATE post SET name=? WHERE post_id=? ";
    private final String sqlDeleteBlogbyId = "DELETE FROM blog   WHERE post_id=? ";
    private final String sqlDeletePostById = "DELETE FROM post   WHERE post_id=? ";
    PreparedStatement statement;
    JDBSConnection bfConnection = JDBSConnection.getInstance();
    Connection connection ;

    public PostRepositoryImpl() throws SQLException, IOException, ClassNotFoundException {

        Connection connection ;

    }

    public PostRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Post getById(Integer aLong) {
        System.out.println(aLong);
        ResultSet resultSet;
        int id = 0;
        String name = "";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlAllpostById);
            statement.setInt(1, aLong);
            statement.execute();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("post_id");
                name = resultSet.getString("name");

            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


        return new PostBuilderImpl(id, name).build();
    }

    @Override
    public List<Post> getAll() {

        List<Post> listPost = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = bfConnection.getConnection()) {
            try {
                statement = connection.prepareStatement(sqlSelectAllPost);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Post post = new PostBuilderImpl(resultSet.getInt("post_id"), resultSet.getString("name")).build();
                listPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return listPost;

    }

    @Override
    public Post save(Post post) {
        ResultSet resultSet;

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlInsertName);
            statement.setString(1, post.getName());
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlSelectIdByName);
            statement.setString(1, post.getName());
            resultSet = statement.executeQuery();
            resultSet.next();
            post.setId(resultSet.getInt("post_id"));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlUpdateNameById);
            statement.setInt(2, post.getId());
            statement.setString(1, post.getName());
            if (statement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer aLong) {

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlDeleteBlogbyId);
            statement.setInt(1, aLong);
            statement.execute();
            statement = connection.prepareStatement(sqlDeletePostById);
            statement.setInt(1, aLong);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
