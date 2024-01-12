package com.example.ex00.example01;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Data
@Qualifier("vips")
public class Vips implements Restaurant{
    private int price = Restaurant.price - 3000;

    @Override
    public boolean selfBar() {
        return true;
    }
}
