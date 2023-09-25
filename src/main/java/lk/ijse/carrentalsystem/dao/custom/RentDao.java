package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.dto.RentDto;

import java.sql.Connection;
import java.util.List;

public interface RentDao extends SuperDao {

    boolean rentCar(RentDto dto, Connection connection);

    List<RentDto> getAllRentDetails();
}
