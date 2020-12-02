package com.eugene.crude.crude.practic.repository.bdRepositoty;


import com.eugene.crude.crude.practic.factory.FactoryImpl.RegionFactoryImpl;
import com.eugene.crude.crude.practic.factory.FactoryImpl.UserFactoryImpl;
import com.eugene.crude.crude.practic.factory.RegionFactory;
import com.eugene.crude.crude.practic.factory.UserFactory;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.User;
import com.eugene.crude.crude.practic.repository.UserRepository;
import com.eugene.crude.crude.practic.service.BfConnection;
import com.eugene.crude.crude.practic.utils.IOUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {


    UserFactory factory = new UserFactoryImpl();
    BfConnection bfConnection = new BfConnection();
    Connection connection;
    PreparedStatement statement;


    @Override
    public User getById(Integer aLong) {

        ResultSet resultSet;
        User user = factory.create();
        int id = 0;
        String first_name = "";
        String last_name = "";
        int region = 0;
        List<Integer> listPost = new ArrayList<>();
        String sql = "SELECT *\n FROM users\n LEFT JOIN blog on users.user_id=blog.user_id where  users.user_id =?  ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, aLong);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("user_id");
                    first_name = resultSet.getString("first_name");

                    listPost.add(resultSet.getInt("post_id"));
                    region = resultSet.getInt("region_id");

                }


                user.setId(String.valueOf(id));
                user.setFirstName(first_name);
                user.setLasName(last_name);
                user.setPosts(listPost);
                user.setRegion(region);


            } catch (SQLException e) {
                return null;

            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;


    }

    @Override
    public List<User> getAll() {

        List<User> listUser = new ArrayList<>();
        List<Integer> listInt = new ArrayList<>();
        ResultSet resultSet;

        int id = 0;
        String first_name = "";
        String last_name = "";
        int region = 0;


        String sql = "SELECT *  FROM users    ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement = connection.prepareStatement(sql);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                resultSet = statement.executeQuery();
                int preId = 0;
                while (resultSet.next()) {
                    id = resultSet.getInt("user_id");
                    first_name = resultSet.getString("first_name");
                    last_name = resultSet.getString("last_name");
                    region = resultSet.getInt("region_id");

                    User user = factory.create();

                    user.setId(String.valueOf(id));
                    user.setFirstName(first_name);
                    user.setLasName(last_name);
                    user.setRegion(region);

                    listUser.add(user);
                    listInt.add(id);

                }
                listUser.stream().forEach(e -> System.out.println(e.getId()));
                String sql2 = "SELECT *  FROM users  LEFT JOIN blog on users.user_id=blog.user_id WHERE users.user_id=? ";
                try {
                    statement = connection.prepareStatement(sql2);
                    int i = 0;
                    for (int n : listInt) {
                        List<Integer> listPost = new ArrayList<>();
                        statement.setInt(1, n);
                        resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                            listPost.add(resultSet.getInt("post_id"));


                        }
                        listUser.get(i).setPosts(listPost);
                        i++;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (SQLException e) {
                return null;

            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listUser;


    }

    @Override
    public User save(User user) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql2 = "INSERT INTO users (user_id,first_name,last_name,region_id) VALUES (?,?,?,?) ";
        String sql1 = "SELECT max(user_id)   FROM users ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Statement statement1 = connection.createStatement();
                statement = connection.prepareStatement(sql1);
                resultSet = statement1.executeQuery(sql1);
                resultSet.next();
                id = resultSet.getInt("max") + 1;
                System.out.println(id);
                statement = connection.prepareStatement(sql2);
                statement.setInt(1, id);
                user.setId(String.valueOf(id));
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLasName());
                statement.setInt(4, user.getRegion());
                int B = statement.executeUpdate();
                System.out.println(B);
                String sql3 = "SELECT max(id) FROM blog ";
                statement1 = connection.createStatement();
                resultSet = statement1.executeQuery(sql3);
                resultSet.next();
                id = resultSet.getInt("max") + 1;

                String sql4 = "INSERT INTO blog (id,post_id,user_id) VALUES (?,?,?)";

                statement = connection.prepareStatement(sql4);
                for (int e : user.getPosts()) {
                    statement.setInt(1, id);
                    statement.setInt(2, e);
                    statement.setInt(3, Integer.parseInt(user.getId()));
                    statement.execute();
                    id++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        ResultSet resultSet;

        List<Integer> listBlogId = new ArrayList<>();
        String sql2 = "UPDATE   users   SET first_name=?,last_name=?,region_id=? WHERE user_id=? ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {


                System.out.println(user.getId());
                statement = connection.prepareStatement(sql2);


                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLasName());
                statement.setInt(3, user.getRegion());
                statement.setInt(4, Integer.parseInt(user.getId()));
                int B = statement.executeUpdate();
                System.out.println(B);


                String sql3 = "SELECT id from   blog where user_id=?";
                String sql4 = "UPDATE    blog  SET post_id=? WHERE id=?";
                statement = connection.prepareStatement(sql3);
                statement.setInt(1, Integer.parseInt(user.getId()));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    listBlogId.add(resultSet.getInt("id"));
                }
                statement = connection.prepareStatement(sql4);

                int i = 0;
                for (int e : user.getPosts()) {

                    statement.setInt(1, e);
                    statement.setInt(2, listBlogId.get(i));
                    statement.execute();
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public void deleteById(Integer aLong) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql =  " DELETE FROM users   WHERE user_id=? ";
        String sql2 = "DELETE FROM blog   WHERE user_id=? ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement = connection.prepareStatement(sql2);
                statement.setInt(1, aLong);

                statement.execute();
                statement = connection.prepareStatement(sql);
                statement.setInt(1, aLong);

                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
