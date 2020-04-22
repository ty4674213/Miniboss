package com.example.Miniboss.Controller;

import com.example.Miniboss.Model.ShoppingCart;
import com.example.Miniboss.Repository.ShoppingCartJdbcTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController

public class ShoppingCartAPIController {

    @Autowired
    private ShoppingCartJdbcTemplate shoppingCartJdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    //Get shopping cart
    @RequestMapping("/shopping-cart")
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAll()
    {
        return ResponseEntity.ok(shoppingCartJdbcTemplate.findAll());
    }

    //Get pet from external API
    @RequestMapping("/external/petfood")
    @GetMapping
    public String getPetFoodList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:8081/petfood", HttpMethod.GET,entity,String.class).getBody();
        }

    @RequestMapping("/shopping-cart/add-petfood/{id}")
    @PostMapping
    public ResponseEntity<List<ShoppingCart>> create(@PathVariable Long id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String response = restTemplate.exchange("http://localhost:8081/petfood/"+id, HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        ShoppingCart shoppingCart = objectMapper.readValue(response, ShoppingCart.class);
        return ResponseEntity.ok(shoppingCartJdbcTemplate.insert(shoppingCart));
    }
}
