package com.techchallenge.restaurant.api.findfood;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
class FindfoodApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deveSubirAplicacaoNaPortaEsperada() {

        int expectedPort = 8080;
        assertThat(this.port).isEqualTo(expectedPort);

        String url = "http://localhost:" + this.port + "/";
        String response = this.restTemplate.getForObject(url, String.class);

        assertThat(response).isNotNull();
    }

    @Test
    void deveLancarExceptionFalhaPortaUtilizada() {
        // Removed unnecessary exception type (ApplicationContextException)
        // Spring Boot throws BeanCreationException on startup failures.
        assertThatThrownBy(() -> new SpringApplication(FindfoodApplication.class).run())
                .isInstanceOf(BeanCreationException.class);
    }

    @Test
    void deveLancarExceptionConfiguracaoBancoDeDadosInvalido() {
        String invalidUrl = "jdbc:mysql://localhost:3306/invalid-database";
        System.setProperty("spring.datasource.url", invalidUrl);

        assertThatThrownBy(() -> new SpringApplication(FindfoodApplication.class).run())
                .isInstanceOf(BeanCreationException.class);

        System.clearProperty("spring.datasource.url");
    }

    @Test
    void deveLancarExceptionConfiguracaoBancoDeDadosNaoExisteInvalida() {
        String invalidUrl = "jdbc:mysql://localhost:3306/non-existent-database";
        System.setProperty("spring.datasource.url", invalidUrl);

        assertThatThrownBy(() -> new SpringApplication(FindfoodApplication.class).run())
                .isInstanceOf(BeanCreationException.class);

        System.clearProperty("spring.datasource.url");
    }
}
