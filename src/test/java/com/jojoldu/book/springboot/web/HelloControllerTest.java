package com.jojoldu.book.springboot.web;

// import org.junit.Test; 아래로 변경됨! 사용하면 에러 뜸
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다
//여기선 SpringExtension이라는 스프링 실행자를 실행시킨다.
//즉 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@ExtendWith(SpringExtension.class) //구 : @RunWith(SpringRunner.class)
@WebMvcTest(controllers=HelloController.class)
public class HelloControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void hello_return() throws Exception{
    String hello = "hello";

    mvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(hello));
  }

  @Test
  public void lombok_func_test2() throws Exception{
    String name ="hello";
    int amount = 1000;

    mvc.perform(get("/hello/dto").param("name",name).param("amount", String.valueOf(amount)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
  }
}
