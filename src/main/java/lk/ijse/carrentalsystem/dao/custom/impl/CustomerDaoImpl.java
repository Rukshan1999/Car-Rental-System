package lk.ijse.carrentalsystem.dao.custom.impl;

import lk.ijse.carrentalsystem.dao.custom.CustomerDao;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;
import lk.ijse.carrentalsystem.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;



public class CustomerDaoImpl implements CustomerDao {

    public static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class.getName());


    @Override
    public boolean addCustomer(CustomerEntity entity, Connection connection) throws Exception {
        String sql = "INSERT INTO customer (custid,custTitle, custName, custNic, address, mobile) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement =connection.prepareStatement(sql);

            preparedStatement.setInt(1, entity.getCustid());
            preparedStatement.setString(2, entity.getCustTitle());
            preparedStatement.setString(3, entity.getCustName());
            preparedStatement.setString(4, entity.getCustNic());
            preparedStatement.setString(5, entity.getAddress());
            preparedStatement.setString(6, entity.getMobile());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected>0;
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;

    }

    @Override
    public CustomerEntity getCustomerById(int customerId, Connection connection) throws Exception {
        String sql = "SELECT * FROM customer WHERE custid = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){


                if (resultSet.next()){
                    // Customer found, create and return a CustomerEntity object
                    CustomerEntity customerEntity = new CustomerEntity();
                    customerEntity.setCustid(resultSet.getInt("custid"));
                    customerEntity.setCustTitle(resultSet.getString("custTitle"));
                    customerEntity.setCustName(resultSet.getString("custName"));
                    customerEntity.setCustNic(resultSet.getString("custNic"));
                    customerEntity.setAddress(resultSet.getString("address"));
                    customerEntity.setMobile(resultSet.getString("mobile"));

                    return customerEntity;

                }else {
                    //Customer not found return null
                    return null;
                }
            }
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE, "An error occurred while fetching customer by ID: " + e.getMessage(), e);

            return null;
        }
    }

    @Override
    public List<CustomerEntity> getAllCustomers(Connection connection) {
        List<CustomerEntity> customerEntityList = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                customerEntityList.add(new CustomerEntity(
                        resultSet.getInt("custid"),
                        resultSet.getString("custTitle"),
                        resultSet.getString("custName"),
                        resultSet.getString("custNic"),
                        resultSet.getString("address"),
                        resultSet.getString("custTitle")

                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerEntityList;
    }

    @Override
    public boolean deleteCustomer(int custid, Connection connection) {
        String sql = "DELETE FROM customer WHERE custid = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(1, custid);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(CustomerEntity entity, Connection connection) {
        String sql = "UPDATE customer SET custTitle=?, custName=?, custNic=?, address=?, mobile=? WHERE custid=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getCustTitle());
            preparedStatement.setString(2, entity.getCustName());
            preparedStatement.setString(3, entity.getCustNic());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setString(5, entity.getMobile());
            preparedStatement.setInt(6, entity.getCustid());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected>0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}