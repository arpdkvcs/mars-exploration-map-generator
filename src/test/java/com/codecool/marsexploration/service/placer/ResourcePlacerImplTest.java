package com.codecool.marsexploration.service.placer;

import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.logger.ConsoleLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ResourcePlacerImplTest {

    @Mock
    private TerrainMap mockMap;

    private ResourcePlacerImpl resourcePlacerImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resourcePlacerImpl = new ResourcePlacerImpl(mockMap);
    }

    @Test
    public void testPlaceResources() {
        String groupSymbol = "^";
        String resourceSymbol = "*";
        int numberOfResources = 3;

        TerrainMap map = new TerrainMap(3);
        map.setCell(new Coordinate(0, 0), groupSymbol);
        map.setCell(new Coordinate(1, 1), groupSymbol);
        map.setCell(new Coordinate(2, 2), groupSymbol);

        ResourcePlacerImpl resourcePlacerImpl = new ResourcePlacerImpl(map);
        resourcePlacerImpl.placeResources(groupSymbol, resourceSymbol, numberOfResources);

        int placementsMade = getPlacementsMade(map, resourceSymbol);

        assertEquals(numberOfResources, placementsMade, "Expected resources to be placed next to groupSymbol");
    }

    private static int getPlacementsMade(TerrainMap map, String resourceSymbol) {
        Coordinate[] potentialPlacements = {
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(1, 2),
            new Coordinate(2, 1),
            new Coordinate(2, 0),
            new Coordinate(0, 2)
        };

        int placementsMade = 0;
        String[][] resultCells = map.getCells();
        for (Coordinate coordinate : potentialPlacements) {
            if (resultCells[coordinate.x()][coordinate.y()].equals(resourceSymbol)) {
                placementsMade++;
            }
        }
        return placementsMade;
    }

    @Test
    public void testFindCoordinatesOf() {
        when(mockMap.getCells()).thenReturn(new String[][]{
            {" ", " ", " "},
            {" ", "^", " "},
            {" ", " ", " "}
        });

        when(mockMap.getWidth()).thenReturn(3);

        var result = resourcePlacerImpl.findCoordinatesOf("^");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).x());
        assertEquals(1, result.get(0).y());
    }

    @Test
    public void testIsCellEmpty() {
        when(mockMap.getCells()).thenReturn(new String[][]{
            {" ", "^", " "},
            {" ", "^", " "},
            {" ", " ", " "}
        });
        when(mockMap.getWidth()).thenReturn(3);


        assertTrue(resourcePlacerImpl.isCellEmpty(new Coordinate(0, 0)));
        assertFalse(resourcePlacerImpl.isCellEmpty(new Coordinate(0, 1)));
        assertTrue(resourcePlacerImpl.isCellEmpty(new Coordinate(2, 2)));
    }

    @Test
    public void testIsOutOfBounds() {

        when(mockMap.getWidth()).thenReturn(3);

        assertTrue(resourcePlacerImpl.isOutOfBounds(new Coordinate(-1, 0)));
        assertTrue(resourcePlacerImpl.isOutOfBounds(new Coordinate(0, 3)));
        assertFalse(resourcePlacerImpl.isOutOfBounds(new Coordinate(0, 2)));
        assertFalse(resourcePlacerImpl.isOutOfBounds(new Coordinate(1, 1)));
    }
}
