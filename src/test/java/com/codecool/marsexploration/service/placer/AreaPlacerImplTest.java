package com.codecool.marsexploration.service.placer;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.placer.AreaPlacerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AreaPlacerImplTest {
    @Mock
    private TerrainMap mockMap;

    @Mock
    private Configuration mockConfig;

    @InjectMocks
    private AreaPlacerImpl areaPlacer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockMap.getWidth()).thenReturn(5);
    }

    @Test
    public void testPlaceWithoutOverlap() {
        Set<Coordinate> area = new HashSet<>();
        area.add(new Coordinate(1, 1));

        String[][] cells = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = " ";
            }
        }
        when(mockMap.getCells()).thenReturn(cells);

        areaPlacer.placeArea(area, "^");

        //Assert the setting of the cell
        verify(mockMap).setCell(any(Coordinate.class), eq("^"));
    }
}
