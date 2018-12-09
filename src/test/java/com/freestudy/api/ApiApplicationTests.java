package com.freestudy.api;

import com.freestudy.api.index.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ApiApplicationTests {

  @Autowired
  ApplicationContext ctx;

  @Test
  public void contextLoads() {
    assertThat(ctx).isNotNull();
  }

}