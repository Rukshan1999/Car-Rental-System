package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.service.SuperService;

public interface HomeService extends SuperService {
    int getCustomerCount();

    int getAllCarCount();

    int getCarCatCount();

    int getTotalIncome();
}
