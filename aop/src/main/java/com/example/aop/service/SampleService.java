package com.example.aop.service;

import com.example.aop.aspect.Plus10;
import org.springframework.stereotype.Service;

import static java.lang.Integer.parseInt;

@Service
public class SampleService {
    @Plus10
    public Integer doAdd(String str1, String str2){
        return parseInt(str1) + parseInt(str2);
    }
}
