package lk.ijse.carrentalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginEntity implements SuperEntity{

    private Integer userID;
    private String userName;
    private String password;
}
