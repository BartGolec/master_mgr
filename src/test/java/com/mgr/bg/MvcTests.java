package com.mgr.bg;

import com.mgr.bg.Controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Bartosz on 11/25/2018.
 */


//TODO Code taken from https://spring.io/guides/gs/serving-web-content/   reposityory at GitHub

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class MvcTests {
    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    public void homePage() throws Exception {
//        // N.B. jsoup can be useful for asserting HTML content
//        mockMvc.perform(get("/index.html"))
//                .andExpect(content().string(containsString("Get your greeting")));
//    }
//
//    @Test
//    public void greeting() throws Exception {
//        mockMvc.perform(get("/greeting"))
//                .andExpect(content().string(containsString("Hello, World!")));
//    }
//
//    @Test
//    public void greetingWithUser() throws Exception {
//        mockMvc.perform(get("/greeting").param("name", "Greg"))
//                .andExpect(content().string(containsString("Hello, Greg!")));
//    }
}
