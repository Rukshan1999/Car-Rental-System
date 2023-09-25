package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.HomeDao;
import lk.ijse.carrentalsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDaoImpl implements HomeDao {
    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int getAllCarCount() {
        String sql = "SELECT COUNT(*) FROM car";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int getTotalIncome() {
        String sql = "SELECT SUM(total) FROM rent";
        double totalIncome = 0.0;

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                totalIncome =resultSet.getDouble(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (int) totalIncome;
    }

    @Override
    public int getAllCarCatCount() {
        String sql = "SELECT COUNT(*) FROM carcategory";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
