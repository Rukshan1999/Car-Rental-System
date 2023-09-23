package lk.ijse.carrentalsystem.dao.custom;

import com.mysql.cj.Session;
import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.entity.SuperEntity;

public interface CrudDao<T extends SuperEntity> extends SuperDao {
    Boolean addCustomer(T entity, Session session) throws Exception;

    Boolean updateCustomer(T entity, Session session);

    Boolean deleteCustomer(String id, Session session);
}
