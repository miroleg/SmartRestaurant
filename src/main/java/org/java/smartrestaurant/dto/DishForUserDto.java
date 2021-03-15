package org.java.smartrestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishForUserDto {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
