package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.LoginDao;
import lk.ijse.carrentalsystem.db.DbConnection;
import lk.ijse.carrentalsystem.dto.LoginDto;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public LoginDto getUserByUserName(String userName) {

        String sql="SELECT * FROM user WHERE username = ?";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    LoginDto dto = new LoginDto();
                    dto.setUserID(rs.getInt("userID"));
                    dto.setUserName(rs.getString("userName"));
                    dto.setPassword(rs.getString("password"));

                    return dto;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return null;
    }
}
