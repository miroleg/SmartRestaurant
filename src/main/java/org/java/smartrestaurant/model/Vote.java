package org.java.smartrestaurant.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private LocalDate datev;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="user_id", nullable = false)
    private User user;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="restaurant_id", nullable = false)
    private Restaurant restaurant;

}
