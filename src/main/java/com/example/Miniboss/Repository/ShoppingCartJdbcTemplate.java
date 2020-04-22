package com.example.Miniboss.Repository;

import com.example.Miniboss.Model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShoppingCartJdbcTemplate {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class ShoppingCartRowMapper implements RowMapper<ShoppingCart> {
        @Override
        public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {
            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setCartId(rs.getLong("id"));
            shoppingCart.setId(rs.getLong("petfood_id"));
            shoppingCart.setBrand(rs.getString("petfood_brand"));
            shoppingCart.setName(rs.getString("petfood_name"));
            shoppingCart.setDescription(rs.getString("petfood_description"));
            shoppingCart.setPrice(rs.getBigDecimal("petfood_price"));

            return shoppingCart;
        }
    }

    //GET All Cart Item
    public List<ShoppingCart> findAll(){
        return jdbcTemplate.query("SELECT * FROM shopping_cart", new ShoppingCartRowMapper());
    }

    //Add item into Cart
    public List<ShoppingCart> insert(ShoppingCart shoppingCart){
        jdbcTemplate.update("INSERT INTO shopping_cart (id, petfood_id, petfood_brand, petfood_name, petfood_description, petfood_price)"+"VALUES(?,?,?,?,?,?)",
                new Object[] {shoppingCart.getCartId(),shoppingCart.getId(),shoppingCart.getBrand(),shoppingCart.getName(),shoppingCart.getDescription(),shoppingCart.getPrice()});
        return jdbcTemplate.query("SELECT * FROM shopping_cart ORDER BY id DESC LIMIT 1", new ShoppingCartRowMapper());
    }

}
