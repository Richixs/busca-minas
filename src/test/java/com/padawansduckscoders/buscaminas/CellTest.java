package com.padawansduckscoders.buscaminas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.padawansduckscoders.buscaminas.model.Cell;

class CellTest {
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell();
    }

    @Test
    void testCellIsInitiallyNotAMine() {
        assertFalse(cell.isMine());
    }

    @Test
    void testSetMine() {
        cell.setMine();
        assertTrue(cell.isMine());
    }

    @Test
    void testCellIsInitiallyNotRevealed() {
        assertFalse(cell.isRevealed());
    }

    @Test
    void testReveal() {
        cell.reveal();
        assertTrue(cell.isRevealed());
    }

    @Test
    void testCellIsInitiallyNotFlagged() {
        assertFalse(cell.isFlag());
    }

    @Test
    void testFlagToggle() {
        cell.flag();
        assertTrue(cell.isFlag());
        cell.flag();
        assertFalse(cell.isFlag());
    }

    @Test
    void testNearbyMinesInitiallyZero() {
        assertEquals(0, cell.getNearbyMines());
    }

    @Test
    void testSetNearbyMines() {
        cell.setNearbyMines(3);
        assertEquals(3, cell.getNearbyMines());
    }

    @Test
    void testSetNearbyMinesNegative() {
        cell.setNearbyMines(-1);
        assertEquals(-1, cell.getNearbyMines());
    }
}