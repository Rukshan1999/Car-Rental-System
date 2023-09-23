package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.CarCategoryDao;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarCategoryDaoImpl implements CarCategoryDao {
    @Override
    public boolean addCarCategory(CarCategoryEntity carCategoryEntity, Connection connection) {
        String sql = "INSERT INTO carcategory (catid, name) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1, carCategoryEntity.getCatid());
            preparedStatement.setString(2,carCategoryEntity.getName());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public CarCategoryEntity getcarCatById(int carCattId, Connection connection) {
        String sql = "SELECT * FROM carcategory WHERE catid=?";


        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,carCattId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){
                    CarCategoryEntity entity =new CarCategoryEntity();
                    entity.setName(resultSet.getString("catid"));
                    entity.setName(resultSet.getString("name"));

                    return entity;
                }else {
                    return null;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean updatCarCategory(CarCategoryEntity entity, Connection connection) {

        String sql = "UPDATE carcategory SET name=? WHERE catid=?";

        try {
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setString(1, entity.getName());
            pstm.setString(2, entity.getCatid());

            int rowsAffected = pstm.executeUpdate();

            return rowsAffected>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteCarCategory(int catId, Connection connection) {
        String sql = "DELETE FROm carcategory WHERE catid =?";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1,catId);

            int rowsAffected = pstm.executeUpdate();

            return rowsAffected>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarCategoryEntity> getAllCategories(Connection connection) {
        List<CarCategoryEntity> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM carcategory";
        try  {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                categoryList.add(new CarCategoryEntity(
                        resultSet.getString("catid"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}
