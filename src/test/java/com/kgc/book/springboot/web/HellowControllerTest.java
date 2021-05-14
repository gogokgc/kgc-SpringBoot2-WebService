package com.kgc.book.springboot.web;

import com.kgc.book.springboot.HelloController;
import com.kgc.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할때 JUnit 내장 실행자 외의 다른 실행자 실행(스프링 부트와 JUnit 사이의 연결자 역할)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}) // Web(spring MVC) 에 집중할수있는 어노테이션, @Controller, @ControllerAdvice등 사용가능(@Service, @Repository 등은 사용불가)
public class HellowControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 테스트시 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통해서  HTTP GET, POST 등의 대한 API 테스트 가능

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform 의 결과 검증
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void 습helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(name))) // jsonPath JSON 응답값을 필드별로 검증할수잇는 메소드, $ 을 기준으로 필드명 명시
                        .andExpect(jsonPath("$.amount", is(amount))); // 여기선 name 과 amount 검증이니 $.name, $.amount 로 검증
    }
}
