package com.padawansduckscoders.buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.padawansduckscoders.buscaminas.model.Cell;

public class CellTest {

    private Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void testInicializarMina() {
        cell.setMine();
        assertTrue(cell.isMine());
    }

    @Test
    public void testRevelarCelda() {
        cell.reveal();
        assertTrue(cell.isRevealed());
    }

    @Test
    public void testConfigurarMinasCercanas() {
        cell.setNearbyMines(3);
        assertEquals(3, cell.getNearbyMines());
    }
}
