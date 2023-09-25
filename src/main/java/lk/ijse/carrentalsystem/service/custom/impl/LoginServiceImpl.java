package lk.ijse.carrentalsystem.service.custom.impl;

import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.LoginDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.dto.LoginDto;
import lk.ijse.carrentalsystem.service.custom.LoginService;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao = DaoFactory.getInstance().getDao(DaoType.LOGIN);
    @Override
    public boolean authenticateUser(String userName, String password) {
        LoginDto loginDto = loginDao.getUserByUserName(userName);

        if (loginDto != null && loginDto.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
