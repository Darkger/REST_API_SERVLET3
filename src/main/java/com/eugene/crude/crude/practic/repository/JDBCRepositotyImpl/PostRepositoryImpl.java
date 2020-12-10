package com.eugene.crude.crude.practic.repository.JDBCRepositotyImpl;


import com.eugene.crude.crude.practic.factory.FactoryImpl.PostFactoryImpl;
import com.eugene.crude.crude.practic.factory.PostFactory;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import com.eugene.crude.crude.practic.repository.PostRepository;
import com.eugene.crude.crude.practic.utils.JDBSConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    PostFactory factory = new PostFactoryImpl();
    JDBSConnection bfConnection = new JDBSConnection();

    PreparedStatement statement;


    @Override
    public Post getById(Integer aLong) {
        System.out.println(aLong);
        ResultSet resultSet;

        int id = 0;
        String name = "";
        String sql = "SELECT * FROM post WHERE post_id=? ";

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, aLong);
            statement.execute();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("post_id");
                name = resultSet.getString("name");

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return new PostBuilderImpl(id, name).build();
    }

    @Override
    public List<Post> getAll() {

        List<Post> listPost = new ArrayList<>();
        ResultSet resultSet;
        String sql = "SELECT * FROM post ";
        try (Connection connection = bfConnection.getConnection()) {
            try {
                statement = connection.prepareStatement(sql);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listPost;

    }

    @Override
    public Post save(Post post) {
 ResultSet resultSet;
        String sql2 = "INSERT INTO post (name) VALUES (?) ";
        String sql3 = "SELECT post_id FROM post WHERE name=?  ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql2);
            statement.setString(1, post.getName());
            statement.executeUpdate();
            statement = connection.prepareStatement(sql3);
            statement.setString(1,post.getName());
            resultSet=statement.executeQuery();
            resultSet.next();
            post.setId(resultSet.getInt("post_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        String sql = "UPDATE post SET name=? WHERE post_id=? ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(2, post.getId());
            statement.setString(1, post.getName());
            if (statement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer aLong) {
        String sql2 = "DELETE FROM blog   WHERE post_id=? ";
        String sql = "DELETE FROM post   WHERE post_id=? ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, aLong);
            statement.execute();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, aLong);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
