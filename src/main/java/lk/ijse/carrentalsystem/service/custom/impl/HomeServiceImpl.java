package lk.ijse.carrentalsystem.service.custom.impl;

import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.HomeDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.service.custom.HomeService;

public class HomeServiceImpl implements HomeService {
    private HomeDao homedao = DaoFactory.getInstance().getDao(DaoType.HOME);

    @Override
    public int getCustomerCount() {
        return homedao.getCustomerCount();
    }

    @Override
    public int getAllCarCount() {
        return homedao.getAllCarCount();
    }

    @Override
    public int getCarCatCount() {
        return homedao.getAllCarCatCount();
    }

    @Override
    public int getTotalIncome() {
        return homedao.getTotalIncome();
    }
}
