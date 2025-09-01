package org.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

public class TestHeadBeat {
    private HeadBeat headBeat;
    @BeforeEach
    void initTest() {
        this.headBeat = new HeadBeat();
    }

    @Test
    void testGetUsoRed() {
        //Given
        HeadBeat healthBeat = this.headBeat;
        //When
        String usoRed = healthBeat.getUsoRed();
        //Then
        assertNotNull(usoRed);
        assertTrue(usoRed.contains("Interfaz:"));
    }
    @Test
    void testInitHealthBeat() {
        //Given
        HeadBeat healthBeat = this.headBeat;
        Time time = new Time(System.currentTimeMillis());
        //When
        Time timeExpected = healthBeat.initHealthBeat();
        assertNotNull(healthBeat.initHealthBeat());
        assertEquals(time, timeExpected);
    }
    @Test
    void testStatus() {
        //Given
        HeadBeat healthBeat = this.headBeat;
        Boolean statusInicial = healthBeat.getStatus();
        //When
        healthBeat.setStatus(true);
        Boolean statusEsperado = healthBeat.getStatus();
        //Then
        assertFalse(statusInicial);
        assertTrue(statusEsperado);
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
