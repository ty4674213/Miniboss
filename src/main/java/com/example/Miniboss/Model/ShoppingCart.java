package com.example.Miniboss.Model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShoppingCart {

    private Long id;
    private Long petfoodId;
    private String petfoodBrand;
    private String petfoodName;
    private String petfoodDescription;
    private BigDecimal petfoodPrice;

}
