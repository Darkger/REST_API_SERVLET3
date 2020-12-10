package com.eugene.crude.crude.practic.repository.bdRepositoty;


import com.eugene.crude.crude.practic.factory.FactoryImpl.UserFactoryImpl;
import com.eugene.crude.crude.practic.factory.UserFactory;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.model.builder.builderImpl.UserBuilderImpl;
import com.eugene.crude.crude.practic.repository.UserRepository;
import com.eugene.crude.crude.practic.utils.BfConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {


    UserFactory factory = new UserFactoryImpl();
    BfConnection bfConnection = new BfConnection();

    PreparedStatement statement;


    @Override
    public User getById(Integer aLong) {
        UserBuilderImpl userBuilder = new UserBuilderImpl();
        ResultSet resultSet;
        List<Post> listPost = new ArrayList<>();
        String sql = "SELECT * FROM users LEFT JOIN blog on users.user_id=blog.user_id where  users.user_id =?  ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
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
        }
        return userBuilder.setPosts(listPost).build();


    }

    @Override
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();
        int id = 0;
        ResultSet resultSet;
        String sql = "SELECT *  FROM users    ";
        String sql2 = "SELECT *  FROM users  LEFT JOIN blog on users.user_id=blog.user_id WHERE users.user_id=? ";
        try (Connection connection = bfConnection.getConnection()) {

            statement = connection.prepareStatement(sql);
            statement.execute();
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("user_id");

                User user = new UserBuilderImpl()
                        .setId(id)
                        .setFirstName(resultSet.getString("first_name"))
                        .setLasName(resultSet.getString("last_name"))
                        .setRegion(new RegionBuilderImpl().setId(resultSet.getInt("region_id")).build())
                        .build();

                listUser.add(user);
                listInt.add(id);
            }
            listUser.stream().forEach(e -> System.out.println(e.getId()));


            statement = connection.prepareStatement(sql2);
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
        }

        return listUser;


    }

    @Override
    public User save(User user) {
        ResultSet resultSet;
        String sql2 = "INSERT INTO users (first_name,last_name,region_id) VALUES (?,?,?) ";
        String sql1 = "SELECT user_id  FROM users WHERE first_name=? and last_name=? and region_id=? ";
        try (Connection connection = bfConnection.getConnection()) {

            statement = connection.prepareStatement(sql2);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(sql1);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("user_id"));
            String sql4 = "INSERT INTO blog (post_id,user_id) VALUES (?,?)";
            statement = connection.prepareStatement(sql4);
            for (Post e : user.getPosts()) {
                statement.setInt(1, e.getId());
                statement.setInt(2, user.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        ResultSet resultSet;
        List<Integer> listBlogId = new ArrayList<>();
        String sql2 = "UPDATE   users   SET first_name=?,last_name=?,region_id=? WHERE user_id=? ";
        String sql3 =  "SELECT  post_id FROM blog where user_id=?";
        String sql4 = "UPDATE    blog  SET post_id=? WHERE user_id=? and post_id=?";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql2);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLasName());
            statement.setInt(3, user.getRegion().getId());
            statement.setInt(4, user.getId());
            statement = connection.prepareStatement(sql3);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listBlogId.add(resultSet.getInt("post_id"));
            }
            statement = connection.prepareStatement(sql4);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Integer aLong) {
        String sql = " DELETE FROM users   WHERE user_id=? ";
        String sql2 = "DELETE FROM blog   WHERE user_id=? ";
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
