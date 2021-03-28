package org.java.smartrestaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_u")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "dateord", nullable = false)
    private LocalDate dateord;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="user_id", nullable = false)
    private User user;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "totalcookingtime", nullable = false)
    private Integer totalCookingTime;
}
