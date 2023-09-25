package lk.ijse.carrentalsystem.service.custom;

import lk.ijse.carrentalsystem.service.SuperService;

public interface LoginService extends SuperService {
    boolean authenticateUser(String userName, String password);
}
