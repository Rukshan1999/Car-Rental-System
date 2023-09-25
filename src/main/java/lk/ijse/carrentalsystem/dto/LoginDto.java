package lk.ijse.carrentalsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto implements SuperDto{

    private Integer userID;
    private String userName;
    private String password;
}
