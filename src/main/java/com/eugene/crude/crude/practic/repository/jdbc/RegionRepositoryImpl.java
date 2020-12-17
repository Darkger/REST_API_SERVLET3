package com.eugene.crude.crude.practic.repository.jdbc;

import com.eugene.crude.crude.practic.model.Region;
import com.eugene.crude.crude.practic.model.builder.builderImpl.RegionBuilderImpl;
import com.eugene.crude.crude.practic.repository.RegionRepository;
import com.eugene.crude.crude.practic.utils.JDBSConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    JDBSConnection bfConnection = JDBSConnection.getInstance();
    PreparedStatement statement;
    Connection connection;
    private final String sqlSelectAllRegion = "SELECT  *   FROM region ";
    private final String sqlSelectAllById = "SELECT * FROM region WHERE region_id=? ";
    private final String sqlInsertName = "INSERT INTO region (name) VALUES (?) ";
    private final String sqlUpdateRegion = "UPDATE region SET region=? WHERE region_id=? ";
    private final String sqlSelectIdbyName = "SELECT region_id FROM region WHERE name=?  ";
    private final String sqlDeleteRegion = "DELETE FROM region   WHERE region_id=? ";

    public RegionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Region getById(Integer aLong) {
        ResultSet resultSet;
        int id = 0;
        String name = "";

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlSelectAllById);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RegionBuilderImpl(id, name).build();
    }

    @Override
    public List<Region> getAll() {
        List<Region> listPost = new ArrayList<>();
        ResultSet resultSet;
        int id = 0;
        String name = "";

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlSelectAllRegion);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("region_id");
                name = resultSet.getString("name");
                Region region = new RegionBuilderImpl(id, name).build();
                listPost.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPost;
    }

    @Override
    public Region save(Region region) {
        ResultSet resultSet;

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlSelectAllRegion);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(region.getContent())) {
                    region.setId((resultSet.getInt("region_id")));
                    return region;
                }
            }
            statement = connection.prepareStatement(sqlInsertName);
            statement.setString(1, region.getContent());
            statement.executeUpdate();
            statement = connection.prepareStatement(sqlSelectIdbyName);
            statement.setString(1, region.getContent());
            resultSet = statement.executeQuery();
            resultSet.next();
            region.setId(resultSet.getInt("region_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Region update(Region region) {


        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlUpdateRegion);
            statement.setInt(2, region.getId());
            statement.setString(1, region.getContent());
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
        return region;
    }

    @Override
    public void deleteById(Integer aLong) {

        try (Connection connection = bfConnection.getConnection()) {
            statement = connection.prepareStatement(sqlDeleteRegion);
            statement.setInt(1, aLong);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
