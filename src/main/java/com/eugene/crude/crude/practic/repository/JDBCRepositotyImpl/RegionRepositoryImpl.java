package com.eugene.crude.crude.practic.repository.JDBCRepositotyImpl;

import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.utils.JDBSConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    JDBSConnection bfConnection = new JDBSConnection();
    PreparedStatement statement;
    final String sqlRegion = "SELECT  *   FROM region ";
    @Override
    public Region getById(Integer aLong) {
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql = "SELECT * FROM region WHERE region_id=? ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, aLong);
            statement.execute();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("region_id");
                name = resultSet.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new RegionBuilderImpl(id,name).build();
    }

    @Override
    public List<Region> getAll() {
        List<Region> listPost = new ArrayList<>();
        ResultSet resultSet;
        int id = 0;
        String name = "";
        String sql = "SELECT * FROM region ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("region_id");
                name = resultSet.getString("name");
                Region region = new RegionBuilderImpl(id,name).build();
                listPost.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listPost;
    }

    @Override
    public Region save(Region region) {
        ResultSet resultSet;
        String sql2 = "INSERT INTO region (name) VALUES (?) ";

        String sql4 = "SELECT region_id FROM region WHERE name=?  ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlRegion);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(region.getContent())) {
                    region.setId((resultSet.getInt("region_id")));
                    return region;
                }
            }
            statement = connection.prepareStatement(sql2);
            statement.setString(1, region.getContent());
            statement.executeUpdate();
           statement = connection.prepareStatement(sql4);
           statement.setString(1,region.getContent());
           resultSet = statement.executeQuery();
           resultSet.next();
           region.setId(resultSet.getInt("region_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {

        String sql = "UPDATE region SET region=? WHERE region_id=? ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(2, region.getId());
            statement.setString(1, region.getContent());
            if (statement.executeUpdate()== 0) {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public void deleteById(Integer aLong) {
        String sql = "DELETE FROM region   WHERE region_id=? ";
        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
