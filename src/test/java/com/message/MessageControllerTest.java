package com.message;

import com.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnMessageOnMessageRequest() throws Exception {
        mockMvc.perform(get("/greeting")
                .param("name", "Shammie"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"message\":\"Hello, Shammie!\"}"));
    }


}

