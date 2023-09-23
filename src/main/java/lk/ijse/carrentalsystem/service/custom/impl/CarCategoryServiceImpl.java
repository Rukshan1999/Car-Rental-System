package lk.ijse.carrentalsystem.service.custom.impl;

import lk.ijse.carrentalsystem.dao.DaoFactory;
import lk.ijse.carrentalsystem.dao.custom.CarCategoryDao;
import lk.ijse.carrentalsystem.dao.util.DaoType;
import lk.ijse.carrentalsystem.dto.CarCategoryDto;
import lk.ijse.carrentalsystem.dto.CustomerDto;
import lk.ijse.carrentalsystem.entity.CarCategoryEntity;
import lk.ijse.carrentalsystem.entity.CustomerEntity;
import lk.ijse.carrentalsystem.service.custom.CarCategoryService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CarCategoryServiceImpl implements CarCategoryService {
    private CarCategoryDao carCategoryDao =  DaoFactory.getInstance().getDao(DaoType.CARCATEGORY);
    @Override
    public boolean addCarCategory(CarCategoryDto carCategoryDto, Connection connection) {

        //Convert the Dto to entity
        CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
        carCategoryEntity.setCatid(carCategoryDto.getCarCatId());
        carCategoryEntity.setName(carCategoryDto.getCatName());

        return carCategoryDao.addCarCategory(carCategoryEntity,connection);
    }

    @Override
    public CarCategoryEntity getCarCatById(int carCattId, Connection connection) {
        return carCategoryDao.getcarCatById(carCattId,connection);
    }

    @Override
    public boolean updateCarCategory(CarCategoryDto carCategoryDto, Connection connection) {
        CarCategoryEntity entity =new CarCategoryEntity();
        entity.setCatid(carCategoryDto.getCarCatId());
        entity.setName(carCategoryDto.getCatName());
        return carCategoryDao.updatCarCategory(entity,connection);
    }


    @Override
    public boolean deleteCarCategory(int catId, Connection connection) {
        return carCategoryDao.deleteCarCategory(catId,connection);
    }

    @Override
    public List<String> getAllCategoryIds(Connection connection) {
        List<CarCategoryEntity> allCategories = carCategoryDao.getAllCategories(connection);
        List<String> categoryIds = new ArrayList<>();

        for (CarCategoryEntity category : allCategories) {
            categoryIds.add(category.getCatid());
        }

        return categoryIds;

    }
}
