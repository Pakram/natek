package com.task.natek.controller;

import com.task.natek.NatekApplicationTests;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidateControllerTest extends NatekApplicationTests {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    private MockMvc mockMvc;

    private String testJson;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        testJson = "[" +
                "{" +
                "\"customer\": \"PLUTO22\"," +
                "\"ccyPair\": \"EURUSD\"," +
                "\"type\": \"Spot\"," +
                "\"direction\": \"BUY\"," +
                "\"tradeDate\": \"2016-08-11\"," +
                "\"amount1\": 1000000.00," +
                "\"amount2\": 1120000.00," +
                "\"rate\": 1.12," +
                "\"valueDate\": \"2016-08-15\"," +
                "\"legalEntity\": \"CS Zurich\"," +
                "\"trader\": \"Johann Baumfiddler\"" +
                "}" +
                "]";
    }

    @Test
    public void validateTradeData() throws Exception {


        this.mockMvc.
                perform(post("/validate/tradeData")
                        .content(testJson)
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422))
                .andDo(document("index", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }
}

