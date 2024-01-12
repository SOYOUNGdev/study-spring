package com.example.ex00.example01;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Data
@Qualifier("outback") @Primary
public class Outback implements Restaurant{
    private int price = Restaurant.price + 5000;

    @Override
    public boolean selfBar() {
        return true;
    }
}
