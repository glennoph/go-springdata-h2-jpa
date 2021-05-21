package go.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @BeforeEach
    void setUp() {
        System.out.println("ApplicationTest");
    }

    @Test
    public void contextLoads() {
    }

}