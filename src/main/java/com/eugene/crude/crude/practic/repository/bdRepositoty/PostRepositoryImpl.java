package com.eugene.crude.crude.practic.repository.bdRepositoty;


import com.eugene.crude.crude.practic.factory.FactoryImpl.PostFactoryImpl;
import com.eugene.crude.crude.practic.factory.PostFactory;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.repository.PostRepository;
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

public class PostRepositoryImpl implements PostRepository {
    PostFactory factory = new PostFactoryImpl();
    BfConnection bfConnection = new BfConnection();
    Connection connection;
    PreparedStatement statement;



    @Override
    public Post getById(Integer aLong) {
        System.out.println(String.valueOf(aLong));
        ResultSet resultSet;
        Post post = factory.create();
        int id = 0;
        String name = "";
        String sql = "SELECT * FROM post WHERE post_id=? ";
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
                    id = resultSet.getInt("post_id");
                    name = resultSet.getString("post");
                    System.out.println(id + " " + name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            post.setId(String.valueOf(id));
            post.setContent(name);
        } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return post;
    }

    @Override
    public List<Post> getAll() {

        List<Post> listPost = new ArrayList<>();
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql = "SELECT * FROM post ";
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("post_id");
                name = resultSet.getString("post");
                Post post = factory.create();
                post.setId(String.valueOf(id));
                post.setContent(name);
                listPost.add(post);
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
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
        return listPost;

    }

    @Override
    public Post save(Post post) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql2 = "INSERT INTO post (post_id,post) VALUES (?,?) ";
        String sql1 = "SELECT max(post_id)   FROM post  ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        try {

            statement = connection.prepareStatement(sql1);
            resultSet = statement.executeQuery(sql1);
            resultSet.next();
            id = resultSet.getInt("max") + 1;

            statement = connection.prepareStatement(sql2);
            statement.setInt(1, id);
            statement.setString(2, post.getContent());
            statement.executeUpdate();
            post.setId(String.valueOf(id));

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
        return post ;
    }

    @Override
    public Post update(Post post) {

        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql = "UPDATE post SET post=? WHERE post_id=? ";
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
            statement.setInt(2, Integer.parseInt(post.getId()));
            statement.setString(1, post.getContent());
            int B = statement.executeUpdate();
            if (B == 0) {
                return null;
            }
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

        return post;
    }

    @Override
    public void deleteById(Integer aLong) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql2 = "DELETE FROM blog   WHERE post_id=? ";
        String sql = "DELETE FROM post   WHERE post_id=? ";
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
