package com.service;

import com.model.Department;
import com.model.User;
import com.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserHelper userHelper;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testGetUser() throws Exception {
        Department department = new Department(1, "healthCare");
        User user = new User(1, "foo", "boo");
        // user.setDepartment(department);
        given(userRepository.findOne(1L)).willReturn(user);

        assertThat(userService.getUser(1)).isEqualTo(user);
    }

    @Test
    public void testCreateExternalUser() throws Exception {
        //Given
        given(userHelper.isExternalUser()).willReturn(true);
        Department department = new Department(1, "healthCare");
        User user = new User(1, "foo", "boo");
        // user.setDepartment(department);
       given(userRepository.save(user)).willReturn(user);

        user.setLastName("Kanistha");
        User savedUser = userService.save(user);

        assertThat(savedUser).isEqualTo(user);
        assertThat(savedUser.getLastName()).isEqualTo(user.getLastName());
        verify(userHelper).isExternalUser();
        verify(userRepository).save(user);
        verify(userRepository,times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testCreateInternalUser() throws Exception {
        //Given
        given(userHelper.isExternalUser()).willReturn(false);
        Department department = new Department(1, "healthCare");
        User user = new User(1, "foo", "boo");
        // user.setDepartment(department);
        given(userRepository.count()).willReturn(100L);

        user.setLastName("Kanistha");
        User savedUser = userService.save(user);

        assertThat(savedUser).isEqualTo(user);
        assertThat(savedUser.getLastName()).isEqualTo(user.getLastName());
        verify(userHelper).isExternalUser();
        verify(userRepository).save(user);
        verify(userRepository).count();
        verifyNoMoreInteractions(userRepository);
    }
}
