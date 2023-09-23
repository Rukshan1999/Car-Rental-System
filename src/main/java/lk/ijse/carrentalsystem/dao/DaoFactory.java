package lk.ijse.carrentalsystem.dao;

import lk.ijse.carrentalsystem.dao.custom.impl.CarCategoryDaoImpl;
import lk.ijse.carrentalsystem.dao.custom.impl.CarDaoImpl;
import lk.ijse.carrentalsystem.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.carrentalsystem.dao.custom.impl.RentDaoImpl;
import lk.ijse.carrentalsystem.dao.util.DaoType;

import static lk.ijse.carrentalsystem.service.util.ServiceType.*;

public class DaoFactory {
    private static DaoFactory instance;

    public DaoFactory() {
    }
    public static DaoFactory getInstance(){
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:{
                return (T) new CustomerDaoImpl();
            }

            case CARCATEGORY:{
                return (T) new CarCategoryDaoImpl();
            }

            case CAR:{
                return (T) new CarDaoImpl();
            }

            case RENT:{
                return (T) new RentDaoImpl();
            }

            //CarDaoImpl
            default:
                throw new RuntimeException();
        }
    }


}
