package lk.ijse.carrentalsystem.dao.custom;

import lk.ijse.carrentalsystem.dao.SuperDao;
import lk.ijse.carrentalsystem.dto.CarCategoryDto;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;

import java.sql.Connection;
import java.util.List;

public interface CarCategoryDao extends SuperDao {
    boolean addCarCategory(CarCategoryEntity carCategoryEntity, Connection connection);

    CarCategoryEntity getcarCatById(int carCattId, Connection connection);

    boolean updatCarCategory(CarCategoryEntity entity, Connection connection);

    boolean deleteCarCategory(int catId, Connection connection);

    List<CarCategoryEntity> getAllCategories(Connection connection);

    List<CarCategoryDto> getAllCarCategories();
}
