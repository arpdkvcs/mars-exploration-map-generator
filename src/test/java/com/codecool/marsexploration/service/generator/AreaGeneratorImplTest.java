package com.codecool.marsexploration.service.generator;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.service.generator.AreaGenerator;
import com.codecool.marsexploration.service.generator.AreaGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AreaGeneratorImplTest {
    private AreaGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new AreaGeneratorImpl();
    }

    @Test
    public void testGenerateArea() {
        Set<Coordinate> area = generator.generateArea(4);
        assertNotNull(area);
        assertEquals(4, area.size());
    }
}
