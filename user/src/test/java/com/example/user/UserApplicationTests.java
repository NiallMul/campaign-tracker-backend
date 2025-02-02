package com.example.user;

import com.example.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

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

        user = postForObject(USER_SERVICE, user, UserModel.class);

        assertThat(user.getUuid()).isNotNull();
    }

    @Test
    void shouldAuthenticateUser() {
        UserModel user = generateUserModel("test2");
        postForObject(USER_SERVICE, user, UserModel.class);

        Boolean successFullAuthenticate = restTemplate.postForObject(USER_SERVICE + "/authenticate", user, Boolean.class);
        assertThat(successFullAuthenticate).isTrue();
    }

    @Test
    void shouldFailAuthenticateUser() {
        UserModel user = generateUserModel("test3");
        postForObject(USER_SERVICE, user, UserModel.class);

        user.setPassword("wrongpsswrd");
        Boolean successFullAuthenticate = restTemplate.postForObject(USER_SERVICE + "/authenticate", user, Boolean.class);
        assertThat(successFullAuthenticate).isFalse();
    }

    private UserModel postForObject(String serviceUrl, UserModel requestObject, Class<UserModel> returnModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<UserModel> request = new HttpEntity<>(requestObject, headers);
        ResponseEntity<UserModel> response = restTemplate.postForEntity(serviceUrl, request, returnModel);
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
