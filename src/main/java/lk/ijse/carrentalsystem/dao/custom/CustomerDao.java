package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CustomerEntity;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

public interface CustomerDao extends SuperDao{
    boolean addCustomer(CustomerEntity entity, Connection connection) throws Exception;

    CustomerEntity getCustomerById(int customerId, Connection co) throws Exception;


    boolean updateCustomer(CustomerEntity customerEntity, Connection connection);


    List<CustomerEntity> getAllCustomers(Connection connection);

    boolean deleteCustomer(int custid, Connection connection);


    List<CustomerDto> getAllCustomersTable();
}
