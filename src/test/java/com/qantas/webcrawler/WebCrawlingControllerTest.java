package com.qantas.webcrawler;

import com.qantas.webcrawler.api.controllers.WebCrawlingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WebCrawlingController.class)
@ContextConfiguration
@WebAppConfiguration
@ActiveProfiles("test")
@ComponentScan("com.qantas.webcrawler")
public class WebCrawlingControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testGetWebPageTreeInfo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/crawler").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
