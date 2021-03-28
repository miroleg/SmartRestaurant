package org.java.smartrestaurant.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
//@ToString(exclude = {"dish", "restaurant"})
@ToString
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateo;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="order_u_id", nullable = false)
    private OrderU orderU;


    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="dish_id", nullable = false)
    private Dish dish;

    @NotNull
    private BigDecimal price;


}
