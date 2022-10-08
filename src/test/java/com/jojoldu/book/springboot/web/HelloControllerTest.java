package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith은 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴. 여기서는 SpringRunner를 실행! 즉, 스프링부트테스트와 JUnit 사이에 연결자 역할
// @WebMvcTest : 여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션. 컨트롤러 관련 어노테이션만 사용할 수 있음
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용, 이 클래스를 통해서 HTTP GET, POST 등에 대한 API 테스트 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증, HTTP Header의 Status 검증, 200 / 400 / 500에 대한 검증으로 200(OK)에 대한 상태 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증, 응답 본문에 대한 내용을 검증 즉 Controller에서 'hello'를 리턴하는지 검증

    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        /*
        [jsonPath]
            JSON 응답값을 필드별로 검증할 수 있는 메소드
            $를 기준으로 필드명을 명시
            name과 amount를 검증 -> $.name / $.amout
        */
    }
}
