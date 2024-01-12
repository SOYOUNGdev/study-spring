package com.example.aop.sevice;

import com.example.aop.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ServiceTest {
    @Autowired
    private SampleService sampleService;

    @Test
    public void doAddTest(){
        log.info("result: " + sampleService.doAdd("1", "2"));
    }
}
