package com.example.ex00.example01;

import com.example.ex00.example01.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class example01Test {
    @Autowired
    private Restaurant restaurant;

    @Test
    public void restaurantTest(){
        log.info("price: " + ((Outback)restaurant).getPrice());
        log.info("selfbar: " + restaurant.selfBar());
    }
}
