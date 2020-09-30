package dsb.web.service;

import dsb.web.domain.Customer;
import dsb.web.domain.User;
import dsb.web.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

/*
* Check if method checkCredentials correctly verifies user credentials
* Test various edge cases for password
* */
@ExtendWith(MockitoExtension.class)
public class CheckCredentialsTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private SignInService signInService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setup() {
        customer1 = new Customer(12345678, "H", "van", "Thienen", null, "huub", "thienen", null);
        customer2 = new Customer(87654321, "M", "", "Oey", null, "michel", "oey", null);
    }

    @Test
    void testAuthentication() {
        User returnedUser;
        when(userRepository.findOneByUsername("huub")).thenReturn(Optional.of(customer1));
        when(userRepository.findOneByUsername("michel")).thenReturn(Optional.of(customer2));

        returnedUser = signInService.checkCredentials("huub", "thienen");
        assertThat(returnedUser.equals(customer1));

        returnedUser = signInService.checkCredentials("michel", "oey");
        assertThat(returnedUser.equals(customer2));

        returnedUser = signInService.checkCredentials("michel", "thienen");
        assertThat(returnedUser).isNull();

        returnedUser = signInService.checkCredentials("huub", "oey");
        assertThat(returnedUser).isNull();
    }

    @Test
    void testUsernameEdgeCases() {
        User returnedUser;
        when(userRepository.findOneByUsername("michel")).thenReturn(Optional.of(customer2));

        // Test trimming
        returnedUser = signInService.checkCredentials("michel ", "oey");
        assertThat(returnedUser.equals(customer2));

        returnedUser = signInService.checkCredentials(" michel", "oey");
        assertThat(returnedUser.equals(customer2));

        // Test casing
        returnedUser = signInService.checkCredentials("Michel", "oey");
        assertThat(returnedUser.equals(customer2));

        // Test random characters
        returnedUser = signInService.checkCredentials("mich el", "oey");
        assertThat(returnedUser).isNull();

        returnedUser = signInService.checkCredentials("mi.chel", "oey");
        assertThat(returnedUser).isNull();
    }

    @Test
    void testPasswordEdgeCases() {
        User returnedUser;
        when(userRepository.findOneByUsername("huub")).thenReturn(Optional.of(customer1));

        // Test trimming
        returnedUser = signInService.checkCredentials("huub", " thienen");
        assertThat(returnedUser.equals(customer1));

        returnedUser = signInService.checkCredentials("huub", "thienen ");
        assertThat(returnedUser.equals(customer1));

        // Test casing
        returnedUser = signInService.checkCredentials("huub", "thieneN");
        assertThat(returnedUser).isNull();

        // Test random characters
        returnedUser = signInService.checkCredentials("huub", "thiene n");
        assertThat(returnedUser).isNull();

        returnedUser = signInService.checkCredentials("huub", "thie.nen");
        assertThat(returnedUser).isNull();
    }

}
