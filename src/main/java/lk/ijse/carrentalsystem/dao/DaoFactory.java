package lk.ijse.carrentalsystem.dao;

import lk.ijse.carrentalsystem.dao.custom.impl.*;
import lk.ijse.carrentalsystem.dao.util.DaoType;

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

            case HOME:{
                return (T) new HomeDaoImpl();
            }

            case LOGIN:{
                return (T) new LoginDaoImpl();
            }

            //CarDaoImpl
            default:
                throw new RuntimeException();
        }
    }


}
