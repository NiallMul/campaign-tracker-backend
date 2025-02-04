package com.example.user;

import com.example.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

    private static final String USER_SERVICE = "/user";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnUser() {
        UserModel user = generateUserModel("test1");
        Object token = postForObject(USER_SERVICE, user, Object.class);
        assertThat(token).isNotNull();
    }

    @Test
    void shouldAuthenticateUser() {
        UserModel user = generateUserModel("test2");

        Map<?, ?> bearerToken = (Map<?, ?>) postForObject(USER_SERVICE, user, Object.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken.get("Bearer"));
        HttpEntity<UserModel> entity = new HttpEntity<>(user, headers);

        Object successFullAuthenticate = restTemplate.postForObject(USER_SERVICE + "/authenticate", entity, Object.class);
        assertThat(successFullAuthenticate.toString().contains("Bearer")).isTrue();
    }

    @Test
    void shouldFailAuthenticateUser() {
        UserModel user = generateUserModel("test3");
        Map<?, ?> bearerToken = (Map<?, ?>) postForObject(USER_SERVICE, user, Object.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken.get("Bearer"));
        HttpEntity<UserModel> entity = new HttpEntity<>(user, headers);

        user.setPassword("wrongpsswrd");
        Object successFullAuthenticate = restTemplate.postForObject(USER_SERVICE + "/authenticate", user, Object.class);
        assertThat(successFullAuthenticate).isNull();
    }

    private Object postForObject(String serviceUrl, UserModel requestObject, Class<?> returnModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<UserModel> request = new HttpEntity<>(requestObject, headers);
        ResponseEntity<?> response = restTemplate.postForEntity(serviceUrl, request, returnModel);
        return response.getBody();
    }

    public UserModel generateUserModel(String testSuffix) {
        return UserModel.builder()
                .firstName("user" + testSuffix)
                .lastName("user" + testSuffix)
                .password("pswrdd111")
                .username(testSuffix + "@test.com")
                .build();
    }
}
