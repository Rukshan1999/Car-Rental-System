package lk.ijse.carrentalsystem.service.custom.impl;

import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.CarDao;
import lk.ijse.carrentalsystem.dao.custom.RentDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.dto.RentDto;
import lk.ijse.carrentalsystem.service.custom.RentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RentServiceImpl implements RentService {

    RentDao rentDao = DaoFactory.getInstance().getDao(DaoType.RENT);
    CarDao carDao = DaoFactory.getInstance().getDao(DaoType.CAR);


    @Override
    public boolean rentCar(RentDto dto, Connection connection) {
        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Call the DAO to save the rental record
            boolean success = rentDao.rentCar(dto, connection);

            if (success) {

                // Update car status to "Not Available"
                carDao.updateCarStatus(dto.getCarid(),"Not Available",connection);

                // If successful, commit the transaction
                connection.commit();
                return true;
            } else {
                // If unsuccessful, roll back the transaction
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Always set auto-commit back to true to avoid issues
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<RentDto> getAllRentDetails() {
        List<RentDto> rentDtoList;
        return rentDtoList = rentDao.getAllRentDetails();
    }
}
