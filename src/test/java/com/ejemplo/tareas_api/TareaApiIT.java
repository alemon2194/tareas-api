package com.ejemplo.tareas_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TareaApiIT {

    @Value("${local.server.port}")
    int puerto;

    @Autowired
    TareaRepository repositorio;

    private final HttpClient cliente = HttpClient.newHttpClient();

    @BeforeEach
    void limpiar() {
        repositorio.deleteAll();
    }

    @Test
    void guardaYRecuperaUnaTarea() {
        repositorio.save(new Tarea("Probar la base de datos"));

        assertEquals(1, repositorio.count());
        assertEquals("Probar la base de datos", repositorio.findAll().get(0).getTitulo());
    }

    @Test
    void laApiCreaYListaTareas() throws Exception {
        String base = "http://localhost:" + puerto + "/api/tareas";

        HttpRequest crear = HttpRequest.newBuilder(URI.create(base))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"titulo\":\"Desde el test\"}"))
                .build();
        assertEquals(201, cliente.send(crear, HttpResponse.BodyHandlers.ofString()).statusCode());

        HttpRequest listar = HttpRequest.newBuilder(URI.create(base)).GET().build();
        HttpResponse<String> respuesta = cliente.send(listar, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, respuesta.statusCode());
        assertTrue(respuesta.body().contains("Desde el test"));
    }
}
