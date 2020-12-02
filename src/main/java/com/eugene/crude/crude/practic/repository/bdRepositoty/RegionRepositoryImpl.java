package com.eugene.crude.crude.practic.repository.bdRepositoty;


import com.eugene.crude.crude.practic.factory.FactoryImpl.PostFactoryImpl;
import com.eugene.crude.crude.practic.factory.FactoryImpl.RegionFactoryImpl;
import com.eugene.crude.crude.practic.factory.PostFactory;
import com.eugene.crude.crude.practic.factory.RegionFactory;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.repository.Genericrepository;
import com.eugene.crude.crude.practic.repository.RegionRepository;
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

public class RegionRepositoryImpl implements RegionRepository {


    RegionFactory factory = new RegionFactoryImpl();
    BfConnection bfConnection = new BfConnection();
    Connection connection;
    PreparedStatement statement;


    @Override
    public Region getById(Integer aLong) {
        Region region = factory.create();
        ResultSet resultSet;
        int id=0;
        String name="";
        String sql = "SELECT * FROM region WHERE region_id=? ";
        try {
            connection = bfConnection.getConnection();

        try {
            statement =connection.prepareStatement(sql);
            statement.setInt(1,aLong);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("region_id");
                name = resultSet.getString("region");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        region.setId(String.valueOf(id));
        region.setContent(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return region;

    }

    @Override
    public List<Region> getAll() {

        List<Region> listPost = new ArrayList<>();
        ResultSet resultSet;
        int id=0;
        String name="";
        String sql = "SELECT * FROM region ";
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
                    id = resultSet.getInt("region_id");
                    name = resultSet.getString("region");
                    Region region = factory.create();
                    region.setId(String.valueOf(id));
                    region.setContent(name);
                    listPost.add(region);
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
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPost;

    }

    @Override
    public Region save(Region region) {
        ResultSet resultSet;
        int id = 0;
        List<String> listReg= new ArrayList<>();
        List<Integer> listId= new ArrayList<>();
        String name = "";
        String sql2 = "INSERT INTO region (region_id,region) VALUES (?,?) ";
        String sql1 = "SELECT max(region_id)   FROM region ";
        String sql3 = "SELECT  *   FROM region ";
        try {
            connection = bfConnection.getConnection();
        try {
            statement = connection.prepareStatement(sql3);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("region").equals(region.getContent())){
                    region.setId(String.valueOf(resultSet.getInt("region_id")));
                    return region;
                }
            }

            statement = connection.prepareStatement(sql1);
            resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("max") + 1;
            System.out.println(id);
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, id);
            statement.setString(2, region.getContent());
            statement.executeUpdate();
            region.setId(String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  region;
    }

    @Override
    public Region update(Region region) {

        ResultSet resultSet;
        int id=0;
        String name="";
        String sql = "UPDATE region SET region=? WHERE region_id=? ";
        try {
            try {
                connection = bfConnection.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
            statement =connection.prepareStatement(sql);
            statement.setInt(2,Integer.parseInt(region.getId()));
            statement.setString(1,region.getContent());
            int B =statement.executeUpdate() ;
            if(B==0){
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
        return region;
    }

    @Override
    public void deleteById(Integer aLong) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
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
