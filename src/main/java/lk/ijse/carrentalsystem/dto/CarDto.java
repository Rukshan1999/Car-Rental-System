package lk.ijse.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto implements SuperDto{
    private int carid;
    private String vehinumber;
    private String model;
    private String brand;
    private String year;
    private double pricePerDay;
    private String status;
    private String catid;
}
