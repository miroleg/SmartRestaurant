package org.java.smartrestaurant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.java.smartrestaurant.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFromAUserDto {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private int user_id;
    private RestaurantDto restaurant;

//    private List<DishForUserDto> dishes;

    @JsonProperty("dishes")
    private List<RequestDish> dishes;
    public List<RequestDish> getParameters() {
        return dishes;
    }

    public void setParameters(List<RequestDish> dishes) {
        this.dishes = dishes;
    }




    public static class RequestDish {
/*
private Integer id;
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;
 */
        private Integer id;
        private String name;
        private String description;
        private Integer duration;
        private String content;
        private BigDecimal price;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        /*
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

 */
/* TODO in controller
        public int getTotalCookingTime() {
            int cookingTime = 0;
            for (RequestDish dish : dishes) {
                cookingTime += dish.getDuration();
            }
            return cookingTime;
        }

 */
    }









}
