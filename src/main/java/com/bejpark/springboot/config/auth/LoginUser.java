package com.bejpark.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //이 어노테이션이 생성될 수 있는 위치 저장, 메소드의 파라미터로 선언된 객체에서만 사용할 수 있게된다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //어노테이션 클래스로 지정한다.
}
