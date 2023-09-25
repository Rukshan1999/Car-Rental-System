package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.CarDao;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.CarDto;
import lk.ijse.carrentalsystem.entity.CarEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    @Override
    public boolean addCar(CarEntity carEntity, Connection connection) {
        String sql = "INSERT INTO car (carid, vehinumber, model, brand, year, priceperday, status, catid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(carEntity.getCarid()));
            preparedStatement.setString(2, carEntity.getVehinumber());
            preparedStatement.setString(3, carEntity.getModel());
            preparedStatement.setString(4, carEntity.getBrand());
            preparedStatement.setString(5, carEntity.getYear());
            preparedStatement.setDouble(6, carEntity.getPricePerDay());
            preparedStatement.setString(7, carEntity.getStatus());
            preparedStatement.setString(8, carEntity.getCatid());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarEntity getCarById(int carid, Connection connection) {
        String sql = "SELECT * FROM car WHERE carid=?";

        try {
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setInt(1,carid);


                ResultSet rs =pstm.executeQuery();

                if (rs.next()){
                    CarEntity carEntity = new CarEntity();
                    carEntity.setCarid(rs.getInt("carid"));
                    carEntity.setVehinumber(rs.getString("vehinumber"));
                    carEntity.setModel(rs.getString("model"));
                    carEntity.setBrand(rs.getString("brand"));
                    carEntity.setYear(rs.getString("year"));
                    carEntity.setPricePerDay(rs.getDouble("priceperday"));
                    carEntity.setStatus(rs.getString("status"));
                    carEntity.setCatid(String.valueOf(rs.getInt("catid")));

                    return carEntity;

                }else {
                    return null;
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatecar(CarEntity entity, Connection connection) {
        String sql ="UPDATE car SET vehinumber =?, model=?, brand=?, year=?, priceperday=?, status=?, catid=? WHERE carid=? ";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, entity.getVehinumber());
            pstm.setString(2, entity.getModel());
            pstm.setString(3, entity.getBrand());
            pstm.setString(4,entity.getYear());
            pstm.setDouble(5,entity.getPricePerDay());
            pstm.setString(6, entity.getStatus());
            pstm.setString(7, entity.getCatid());
            pstm.setInt(8,entity.getCarid());

            int rowsAffected = pstm.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCatIdValid(String catid, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteCar(int carid, Connection connection) {
        String sql = "DELETE FROM car WHERE carid = ?";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, carid);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getAllAvailableCarIds(Connection connection) {
        String sql = "SELECT carid FROM car WHERE status = 'Available'";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            List<String>allAvilableCarIds = new ArrayList<>();
            ResultSet rst =pstm.executeQuery();
            while (rst.next()){
                allAvilableCarIds.add(rst.getString("carid"));
            }
            return allAvilableCarIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCarStatus(Integer carid, String notAvailable, Connection connection) {
        String sql ="UPDATE car SET status = ? WHERE carid = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,notAvailable);
            ps.setInt(2,carid);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarDto> getAllCarsTable() {
        List<CarDto> carDtoList = new ArrayList<>();
        String  sql = "SELECT * FROM car";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                CarDto dto =new CarDto();
                dto.setCarid(rs.getInt("carid"));
                dto.setVehinumber(rs.getString("vehinumber"));
                dto.setModel(rs.getString("model"));
                dto.setBrand(rs.getString("brand"));
                dto.setYear(rs.getString("year"));
                dto.setPricePerDay(rs.getDouble("priceperday"));
                dto.setStatus(rs.getString("status"));
                dto.setCatid(rs.getString("catid"));
                carDtoList.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carDtoList;
    }


}
