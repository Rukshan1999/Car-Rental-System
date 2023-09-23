package lk.ijse.carrentalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarEntity implements SuperEntity{
    private int carid;
    private String vehinumber;
    private String model;
    private String brand;
    private String year;
    private double pricePerDay;
    private String status;
    private String catid;
}
