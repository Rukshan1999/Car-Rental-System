package lk.ijse.carrentalsystem.service.custom.impl;

import javafx.collections.FXCollections;
import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.CarDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.dto.CarDto;
import lk.ijse.carrentalsystem.entity.CarEntity;
import lk.ijse.carrentalsystem.entity.CustomerEntity;
import lk.ijse.carrentalsystem.service.custom.CarService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    private CarDao carDao = DaoFactory.getInstance().getDao(DaoType.CAR);

    @Override
    public boolean addCar(CarDto carDto, Connection connection) {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarid(carDto.getCarid());
        carEntity.setVehinumber(carDto.getVehinumber());
        carEntity.setModel(carDto.getModel());
        carEntity.setBrand(carDto.getBrand());
        carEntity.setYear(carDto.getYear());
        carEntity.setPricePerDay(carDto.getPricePerDay());
        carEntity.setStatus(carDto.getStatus());
        carEntity.setCatid(carDto.getCatid());

        return carDao.addCar(carEntity, connection);
    }

    @Override
    public CarEntity getCarById(int carid, Connection connection) {
        return carDao.getCarById(carid,connection);
    }

    @Override
    public boolean updateCar(CarDto dto, Connection connection) {

        CarEntity entity =new CarEntity();
        entity.setCarid(dto.getCarid());
        entity.setVehinumber(dto.getVehinumber());
        entity.setModel(dto.getModel());
        entity.setBrand(dto.getBrand());
        entity.setYear(dto.getYear());
        entity.setPricePerDay(dto.getPricePerDay());
        entity.setStatus(dto.getStatus());
        entity.setCatid(dto.getCatid());
        return carDao.updatecar(entity,connection);
    }


    @Override
    public boolean deleteCar(int carid, Connection connection) {
        return carDao.deleteCar(carid, connection);
    }

    @Override
    public List<String> getAllAvailableCarIds(Connection connection) {
        List<String> allAvilableCarIds = carDao.getAllAvailableCarIds(connection);


        return FXCollections.observableArrayList(allAvilableCarIds);
    }

}
