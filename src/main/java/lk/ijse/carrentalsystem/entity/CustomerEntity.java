package lk.ijse.carrentalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity implements SuperEntity{
    private Integer custid;
    private String custTitle;
    private String custName;
    private String custNic;
    private String Address;
    private String mobile;
}
