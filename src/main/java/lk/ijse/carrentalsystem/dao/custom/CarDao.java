package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.entity.CarEntity;

import java.sql.Connection;
import java.util.List;

public interface CarDao extends SuperDao {
    boolean addCar(CarEntity carEntity, Connection connection);

    CarEntity getCarById(int carid, Connection connection);

    boolean updatecar(CarEntity entity, Connection connection);

    boolean isCatIdValid(String catid, Connection connection);

    boolean deleteCar(int carid, Connection connection);


    List<String> getAllAvailableCarIds(Connection connection);

    boolean updateCarStatus(Integer carid, String notAvailable, Connection connection);
}
