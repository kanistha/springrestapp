package com.client;

import com.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LegalDocumentClientTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getLegalDocumentReturnsDocumentForGivenLegalEntity() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);

         ResponseEntity<Resource> response = restTemplate.exchange(
                "/legalDocuments"
                , HttpMethod.GET
                , entity
                 , Resource.class, "");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
