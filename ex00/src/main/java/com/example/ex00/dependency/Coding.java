package com.example.ex00.dependency;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor //final 생성자만 초기화
public class Coding {

//    필드 주입
//    굉장히 편하게 주입할 수 있다.
//    순환 참조 시 예외가 발생하지 않기 때문에 StackOverFlow 발생
//    다른 곳에서 수정이 가능하기 때문에 심각한 문제가 생길 수 있음
//    주입이 되지 않아도 객체는 생성되기 때문에 NPE(NullPointException) 방어 불가
//    @Autowired

//    생성자 주입
//    메모리에 필드릴 할당하면서 초기값이 주입되므로 final 키워드를 사용할 수 있음.
//    주입이 되지 않으면 객체 자체가 생성되지 않으므로 NPE 방어 가능
    private final Computer computer;

//    @Autowired
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }
}
