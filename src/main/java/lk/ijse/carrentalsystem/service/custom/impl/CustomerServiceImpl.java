package lk.ijse.carrentalsystem.service.custom.impl;


import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.CustomerDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CustomerEntity;
import lk.ijse.carrentalsystem.service.custom.CustomerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);


    @Override
    public boolean addCustomer(CustomerDto dto, Connection connection) throws Exception {
       // Convert the DTO to an Entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustTitle(dto.getCustTitle());
        customerEntity.setCustName(dto.getCustName());
        customerEntity.setCustNic(dto.getCustNic());
        customerEntity.setAddress(dto.getAddress());
        customerEntity.setMobile(dto.getMobile());
        customerEntity.setCustid(dto.getCustid());


        return customerDao.addCustomer(customerEntity, connection);
    }

    @Override
    public CustomerEntity getCustomerById(int customerId, Connection connection) throws Exception {
        return customerDao.getCustomerById(customerId,connection);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto, Connection connection) throws SQLException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustid(dto.getCustid());
        customerEntity.setCustTitle(dto.getCustTitle());
        customerEntity.setCustTitle(dto.getCustTitle());
        customerEntity.setCustName(dto.getCustName());
        customerEntity.setCustNic(dto.getCustNic());
        customerEntity.setAddress(dto.getAddress());
        customerEntity.setMobile(dto.getMobile());
        return customerDao.updateCustomer(customerEntity,connection);
    }


    @Override
    public List<String> getAllCategoryIds(Connection connection) {
        List<CustomerEntity> allCategories = customerDao.getAllCustomers(connection);
        List<String> custids = new ArrayList<>();

        for (CustomerEntity customer : allCategories) {
            custids.add(String.valueOf(customer.getCustid()));
        }

        return custids;
    }

    @Override
    public boolean deleteCar(int custid, Connection connection) {
        return customerDao.deleteCustomer(custid,connection);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtoList;
        return  customerDtoList = customerDao.getAllCustomersTable();
    }


}
