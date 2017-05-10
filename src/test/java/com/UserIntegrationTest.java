package com;

import com.model.User;
import com.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1,"foo","boo");
       ResponseEntity<User> responseEntity = restTemplate.postForEntity("/users",user,User.class,User.class);
       assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
       assertThat(responseEntity.getBody().getId()).isEqualTo(1);
       assertThat(responseEntity.getBody().getFirstName()).isEqualTo("foo");
    }


}
