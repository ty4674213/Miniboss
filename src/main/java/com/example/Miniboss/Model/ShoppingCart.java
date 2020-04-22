package com.example.Miniboss.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.Nullable;
import lombok.Data;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long id;
    private String brand;
    private String name;
    private String description;
    private BigDecimal price;

}
