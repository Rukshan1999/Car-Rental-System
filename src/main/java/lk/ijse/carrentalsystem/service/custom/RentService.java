package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.dto.RentDto;
import lk.ijse.carrentalsystem.service.SuperService;

import java.sql.Connection;
import java.util.List;

public interface RentService extends SuperService {


    boolean rentCar(RentDto dto, Connection connection);

    List<RentDto> getAllRentDetails();
}
