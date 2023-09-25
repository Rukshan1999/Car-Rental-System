package lk.ijse.carrentalsystem.service;

import lk.ijse.carrentalsystem.service.custom.impl.*;
import lk.ijse.carrentalsystem.service.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance(){
       return (instance ==null) ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService> T getService(ServiceType type){
        switch (type){

            case CUSTOMER :{
                return (T) new CustomerServiceImpl();
            }

            case CARCATEGORY :{
                return (T) new CarCategoryServiceImpl();
            }

            case CAR :{
                return (T) new CarServiceImpl();
            }

            case RENT: {
                return (T) new RentServiceImpl();
            }

            case HOME: {
                return (T) new HomeServiceImpl();
            }
            default:
                //return null;
                throw new RuntimeException("invalid service type!");
        }
    }
}
