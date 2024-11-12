package com.padawansduckscoders.buscaminas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.padawansduckscoders.buscaminas.model.Grid;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5, 5);
    }

    @Test
    void testGridInitialization() {
        assertEquals(5, grid.getRows());
        assertEquals(5, grid.getCols());
        assertNotNull(grid.getBoardCells());
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                assertNotNull(grid.getCell(row, col));
            }
        }
    }

    @Test
    void testRevealCellFirstRevealDoesNotPlaceMine() {
        grid.revealCell(0, 0);
        assertFalse(grid.getCell(0, 0).isMine());
    }

    @Test
    void testRevealCellRevealsNearbyCellsWhenZeroMinesAround() {
        Grid smallGrid = new Grid(3, 3, 0); 
        smallGrid.revealCell(1, 1);
        for (int row = 0; row < smallGrid.getRows(); row++) {
            for (int col = 0; col < smallGrid.getCols(); col++) {
                assertTrue(smallGrid.getCell(row, col).isRevealed());
            }
        }
    }

    @Test
    void testPlaceMinesPlacesCorrectNumberOfMines() {
        grid.revealCell(0, 0); 
        int mineCount = 0;
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (grid.getCell(row, col).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(5, mineCount);
    }

    @Test
    void testCountMinesAroundSetsNearbyMinesCorrectly() {
        Grid smallGrid = new Grid(3, 3, 0);
        smallGrid.getCell(0, 0).setMine();
        smallGrid.getCell(0, 2).setMine();
        smallGrid.getCell(2, 0).setMine();
        smallGrid.getCell(2, 2).setMine();
        smallGrid.revealCell(1, 1); 

        assertEquals(4, smallGrid.getCell(1, 1).getNearbyMines());
    }

    @Test
    void testFlagCell() {
        grid.flag(2, 2);
        assertTrue(grid.getCell(2, 2).isFlag());
        grid.flag(2, 2);
        assertFalse(grid.getCell(2, 2).isFlag());
    }
}
