package com.eugene.crude.crude.practic.repository.jdbc;


import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;
import com.eugene.crude.crude.practic.repository.UserRepository;
import com.eugene.crude.crude.practic.utils.JDBSConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String sqlAllFromUsersAndBlogById = "SELECT * FROM users LEFT JOIN blog on users.user_id=blog.user_id where  users.user_id =?  ";
    private final String sqlSelectAll = "SELECT *  FROM users    ";
    private final String sqlInsertFNameLNameRegion = "INSERT INTO users (first_name,last_name,region_id) VALUES (?,?,?) ";
    private final String sqlSelectIdByFnameLNameRegion = "SELECT user_id  FROM users WHERE first_name=? and last_name=? and region_id=? ";
    private final String sqlInsertId = "INSERT INTO blog (post_id,user_id) VALUES (?,?)";
    private final String sqlUpdateUsersById = "UPDATE   users   SET first_name=?,last_name=?,region_id=? WHERE user_id=? ";
    private final String sqlSelectIdById = "SELECT  post_id FROM blog where user_id=?";
    private final String sqlUpdateBlogbyId = "UPDATE    blog  SET post_id=? WHERE user_id=? and post_id=?";
    private final String sqlDeleteUsers = " DELETE FROM users   WHERE user_id=? ";
    private final String sqlDeleteBlog = "DELETE FROM blog   WHERE user_id=? ";
    JDBSConnection bfConnection = JDBSConnection.getInstance();
    PreparedStatement statement;
    Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(Integer aLong) {
        UserBuilderImpl userBuilder = new UserBuilderImpl();
        ResultSet resultSet;
        List<Post> listPost = new ArrayList<>();

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlAllFromUsersAndBlogById);
            statement.setInt(1, aLong);
            statement.execute();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listPost.add(new PostBuilderImpl().setId(resultSet.getInt("post_id")).build());
                userBuilder = new UserBuilderImpl().setId(resultSet.getInt("user_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLasName(resultSet.getString("last_name"))
                        .setRegion(new RegionBuilderImpl().setId(resultSet.getInt("region_id")).build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userBuilder.setPosts(listPost).build();


    }

    @Override
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlSelectAll);
            statement.execute();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new UserBuilderImpl()
                        .setId(resultSet.getInt("user_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLasName(resultSet.getString("last_name"))
                        .setRegion(new RegionBuilderImpl().setId(resultSet.getInt("region_id")).build())
                        .build();

                listUser.add(user);
                listInt.add(user.getId());
            }


            statement = connection.prepareStatement(sqlAllFromUsersAndBlogById);
            int i = 0;
            for (int n : listInt) {
                List<Post> listPost = new ArrayList<>();
                statement.setInt(1, n);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    listPost.add(new PostBuilderImpl().setId(resultSet.getInt("post_id")).build());
                }
                listUser.get(i).setPosts(listPost);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listUser;


    }

    @Override
    public User save(User user) {
        ResultSet resultSet;

        try {

            statement = connection.prepareStatement(sqlInsertFNameLNameRegion);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlSelectIdByFnameLNameRegion);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("user_id"));
            statement = connection.prepareStatement(sqlInsertId);
            for (Post e : user.getPosts()) {
                statement.setInt(1, e.getId());
                statement.setInt(2, user.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        ResultSet resultSet;
        List<Integer> listBlogId = new ArrayList<>();
        try   {
            statement = connection.prepareStatement(sqlUpdateUsersById);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            statement.setInt(4, user.getId());
            statement = connection.prepareStatement(sqlSelectIdById);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listBlogId.add(resultSet.getInt("post_id"));
            }
            statement = connection.prepareStatement(sqlUpdateBlogbyId);
            int i = 0;
            for (Post e : user.getPosts()) {
                statement.setInt(1, e.getId());
                statement.setInt(2, user.getId());
                statement.setInt(3, listBlogId.get(i));
                statement.execute();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Integer aLong) {

        try {
            statement = connection.prepareStatement(sqlDeleteBlog);
            statement.setInt(1, aLong);
            statement.execute();
            statement = connection.prepareStatement(sqlDeleteUsers);
            statement.setInt(1, aLong);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
