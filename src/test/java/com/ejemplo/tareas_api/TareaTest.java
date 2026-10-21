package com.ejemplo.tareas_api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TareaTest {

    @Test
    void unaTareaNuevaNoEstaCompletada() {
        Tarea tarea = new Tarea("Escribir tests");

        assertFalse(tarea.isCompletada());
    }

    @Test
    void completarMarcaLaTareaComoCompletada() {
        Tarea tarea = new Tarea("Escribir tests");

        tarea.completar();

        assertTrue(tarea.isCompletada());
    }
}
