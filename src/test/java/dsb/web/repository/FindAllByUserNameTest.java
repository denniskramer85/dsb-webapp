package dsb.web.repository;

import dsb.web.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
class FindAllByUserNameTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;


    @BeforeEach
    public void setUpData () {
        Customer c1 = new Customer();
        c1.setUsername("anton");
        Customer c2 = new Customer();
        c2.setUsername("bernhard");
        Customer c3 = new Customer();
        c3.setUsername("cornelis");
        Customer c4 = new Customer();
        c4.setUsername("dirk");

        List<Customer> customerList = Arrays.asList(c1, c2, c3, c4);
        for (Customer c : customerList) {
            entityManager.persist(c);
        }
        entityManager.flush();
    }


    public FindAllByUserNameTest() {
        super();
    }



    @Test
    @DisplayName("FindAllByUserNameTest 1_pass")
    void findAllByUsername() {
        List<Customer> found = customerRepository.findAllByUsername("cornelis");
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getUsername()).isEqualTo("cornelis");
    }

    @Test
    @DisplayName("FindAllByUserNameTest 2_fail")
    void findAllByUsername_fail() {
        List<Customer> found = customerRepository.findAllByUsername("evert");
        assertThat(found.size()).isEqualTo(0);
    }

}