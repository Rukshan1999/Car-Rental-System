package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CustomerEntity;
import lk.ijse.carrentalsystem.service.SuperService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    boolean addCustomer(CustomerDto dto, Connection connection) throws Exception ;

    CustomerEntity getCustomerById(int customerId, Connection connection) throws Exception;

    boolean updateCustomer(CustomerDto customerDto, Connection connection) throws SQLException;


    List<String> getAllCategoryIds(Connection connection);

    boolean deleteCar(int custid, Connection connection);
}
