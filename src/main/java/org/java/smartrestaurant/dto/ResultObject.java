package org.java.smartrestaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObject {
    @JsonProperty("restaurantName")
    private String restaurantName;

    private Long votes;
}
