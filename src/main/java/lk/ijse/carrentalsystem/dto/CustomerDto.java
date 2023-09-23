package lk.ijse.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements SuperDto{

    private Integer custid;
    private String custTitle;
    private String custName;
    private String custNic;
    private String Address;
    private String mobile;

}
