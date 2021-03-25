package org.java.smartrestaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orderfromauser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderFromAUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private LocalDate dateord;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="user_id", nullable = false)
    private User user;

    @ManyToOne (optional=false, fetch = FetchType.LAZY)
    @JoinColumn (name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    int totalCookingTime;
    /*

    @JsonProperty("dishes")
    private List<RequestDish> dishes;



    public class RequestDish {

        private String name;
        private String content;

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


    }


 */

}
