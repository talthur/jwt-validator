package com.talthur.jwtvalidator.entrypoint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JWTControllerTest {

    private final static String VALID_JWT = "{\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\"}";
    private final static String INVALID_JWT = "{\"token\":\"eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\"}";
    private final static String INVALID_JWT_NAME = "{\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs\"}";
    private final static String INVALID_JWT_CLAIMS = "{\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY\"}";


    public static final String PATH = "/v1/jwt/validate";
    public static final String CONTENT_TYPE = "application/json";


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTrueToValidJWT() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/v1/jwt/validate")
                        .contentType(CONTENT_TYPE)
                        .content(VALID_JWT))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9." +
                        "eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9." +
                        "QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\",\"valid\":true}",
                actualResponseBody);
    }

    @Test
    public void shouldReturnFalseToInvalidJWT() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .contentType(CONTENT_TYPE)
                        .content(INVALID_JWT))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("{\"jwt\":\"eyJhbGciOiJzI1NiJ9." +
                        "dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9." +
                        "QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg\",\"valid\":false}",
                actualResponseBody);
    }

    @Test
    public void shouldReturnFalseToJWTWithInvalidName() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .contentType(CONTENT_TYPE)
                        .content(INVALID_JWT_NAME))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9." +
                        "eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0." +
                        "6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs\",\"valid\":false}",
                actualResponseBody);
    }

    @Test
    public void shouldReturnFalseToJWTWithMoreThan3Claims() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post(PATH)
                        .contentType(CONTENT_TYPE)
                        .content(INVALID_JWT_CLAIMS))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals("{\"jwt\":\"eyJhbGciOiJIUzI1NiJ9." +
                        "eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9." +
                        "cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY\",\"valid\":false}",
                actualResponseBody);
    }

}