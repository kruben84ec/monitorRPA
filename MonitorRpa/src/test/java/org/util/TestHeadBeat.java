package org.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestHeadBeat {
    private HeadBeat headBeat;
    @BeforeEach
    void initTest() {
        this.headBeat = new HeadBeat();
    }
    @Test
    void testNombreBot() {
        //Given
        HeadBeat healthBeat = this.headBeat;
        String nombre = "TestBot";
        //When
        healthBeat.setNombreBot(nombre);
        String nombreEsperado = healthBeat.getNombreBot();
        //Then
        assertEquals("TestBot", nombreEsperado);
    }
    @Test
    public void testMonitoreoRecursos() {
        HeadBeat monitor = this.headBeat;
        // Prueba CPU
        String cpuInfo = monitor.getUsoCPU();
        assertNotNull(cpuInfo);
        assertTrue(cpuInfo.contains("CPU - Uso:"));

        // Prueba Memoria
        String memoriaInfo = monitor.getUsoMemoria();
        assertNotNull(memoriaInfo);
        assertTrue(memoriaInfo.contains("Memoria - Total:"));

        // Prueba Disco
        String discoInfo = monitor.getUsoDisco();
        assertNotNull(discoInfo);
        assertTrue(discoInfo.contains("Disco:"));

        // Imprimir información para verificación visual
        System.out.println("=== Información del Sistema ===");
        System.out.println(cpuInfo);
        System.out.println(memoriaInfo);
        System.out.println(discoInfo);
    }
}
