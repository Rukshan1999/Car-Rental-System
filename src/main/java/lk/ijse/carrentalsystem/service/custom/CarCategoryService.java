package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.dto.CarCategoryDto;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;
import lk.ijse.carrentalsystem.service.SuperService;

import java.sql.Connection;
import java.util.List;

public interface CarCategoryService extends SuperService {
    boolean addCarCategory(CarCategoryDto carCategoryDto, Connection connection);

    CarCategoryEntity getCarCatById(int carCattId, Connection connection);

    boolean updateCarCategory(CarCategoryDto carCategoryDto, Connection connection);


    boolean deleteCarCategory(int catId, Connection connection);

    List<String> getAllCategoryIds(Connection connection);

    List<CarCategoryDto> getAllCarTypes();
}
