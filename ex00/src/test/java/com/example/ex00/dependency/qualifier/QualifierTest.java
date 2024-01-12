package com.example.ex00.dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QualifierTest {
//    @Autowired @Qualifier("desktop")
//    @Autowired @Qualifier("laptop")
    @Autowired
    private Computer computer;

    @Test
    public void computerTest(){
        log.info("computer: " + computer);
    }
}
