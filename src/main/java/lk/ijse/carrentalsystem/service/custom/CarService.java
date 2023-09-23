package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.dto.CarDto;
import lk.ijse.carrentalsystem.entity.CarEntity;
import lk.ijse.carrentalsystem.service.SuperService;

import java.sql.Connection;
import java.util.List;

public interface CarService extends SuperService {
    boolean addCar(CarDto carDto, Connection connection);

    CarEntity getCarById(int carid, Connection connection);

    boolean updateCar(CarDto dto, Connection connection);



    boolean deleteCar(int carid, Connection connection);


    List<String> getAllAvailableCarIds(Connection connection);
}
