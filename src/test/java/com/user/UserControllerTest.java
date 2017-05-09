package com.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    private ObjectMapper objectMapper;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetUserForValidId() throws Exception {

        //Given
        Department department = new Department(1,"healthCare");
        User user = new User(1,"foo","boo");
        user.setDepartment(department);
        BDDMockito.given(userService.getUser(1)).willReturn(user);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk()) //Then
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstName",is("foo")))
                .andExpect(jsonPath("$.lastName",is("boo")))
                .andExpect(jsonPath("$.department.id",is(1)))
                .andExpect(jsonPath("$.department.name", is("healthCare")));
    }

    @Test
    public void testCreateUser() throws Exception {
        //Given
        Department department = new Department(1,"healthCare");
        User user = new User(1,"foo","boo");
        user.setDepartment(department);

        given(userService.save(user)).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstName",is("foo")))
                .andExpect(jsonPath("$.lastName",is("boo")))
                .andExpect(jsonPath("$.department.id",is(1)))
                .andExpect(jsonPath("$.department.name", is("healthCare")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isNoContent());

    }

}