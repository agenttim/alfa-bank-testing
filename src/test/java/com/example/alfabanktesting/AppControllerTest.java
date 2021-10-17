package com.example.alfabanktesting;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController appController;

    @Test
    void getGifRedirectTest() throws Exception {
        this.mockMvc.perform(get("/compare/USD"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getRandomGifTest() {
        int actual = appController.getRandomGif(10);
        Assert.assertTrue(actual <=10 && actual >= 0);
    }
}
