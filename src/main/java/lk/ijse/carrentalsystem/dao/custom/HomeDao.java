package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;

public interface HomeDao extends SuperDao {
    int getCustomerCount();

    int getAllCarCount();

    int getTotalIncome();

    int getAllCarCatCount();
}
