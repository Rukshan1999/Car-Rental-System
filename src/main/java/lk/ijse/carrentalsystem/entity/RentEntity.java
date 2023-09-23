package lk.ijse.carrentalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentEntity implements SuperEntity{
    private Integer rentid;
    private LocalDate fromdate;
    private LocalDate todate;
    private Double perdayrent;
    private Double total;
    private Double balance;
    private Double advancedpayment;
    private Integer custid;
    private Integer carid;
}
