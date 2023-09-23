package lk.ijse.carrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarCategoryDto implements SuperDto{
    private String carCatId;
    private String catName;
}
