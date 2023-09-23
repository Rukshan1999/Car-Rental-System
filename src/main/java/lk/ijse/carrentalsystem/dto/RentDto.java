package lk.ijse.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDto implements SuperDto{
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
