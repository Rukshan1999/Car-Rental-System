package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.dto.LoginDto;

public interface LoginDao extends SuperDao {
    LoginDto getUserByUserName(String userName);
}
