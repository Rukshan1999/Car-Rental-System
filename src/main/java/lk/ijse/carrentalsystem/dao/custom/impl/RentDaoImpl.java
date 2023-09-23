package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.RentDao;
import lk.ijse.carrentalsystem.dto.RentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDaoImpl implements RentDao {

    @Override
    public boolean rentCar(RentDto dto, Connection connection) {
        String sql = "INSERT INTO rent (rentid, fromdate, todate, perdayrent, total,balance, advancedpayment, custid, carid) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, dto.getRentid());
            pstm.setDate(2, Date.valueOf(dto.getFromdate()));
            pstm.setDate(3, Date.valueOf(dto.getTodate()));
            pstm.setDouble(4,dto.getTotal());
            pstm.setDouble(5,dto.getPerdayrent());
            pstm.setDouble(6,dto.getAdvancedpayment());
            pstm.setDouble(7,dto.getBalance());
            pstm.setInt(8,dto.getCustid());
            pstm.setInt(9,dto.getCarid());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
