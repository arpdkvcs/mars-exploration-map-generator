package com.codecool.marsexploration.service.placer;

import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.validation.exceptions.MapGenerationException;

import java.util.List;
import java.util.Random;

public abstract class TerrainPlacer {
    protected final TerrainMap map;
    protected final Random random = new Random();

    public TerrainPlacer(TerrainMap map) {
        this.map = map;
    }

    protected Coordinate getRandomCoordinate(List<Coordinate> coordinatesToTry) {
        if (coordinatesToTry.isEmpty()) {
            throw new MapGenerationException("Couldn't place all terrain by the given configuration.");
        }
        int randomIndex = random.nextInt(coordinatesToTry.size());
        Coordinate randomCoordinate = coordinatesToTry.get(randomIndex);
        coordinatesToTry.remove(randomCoordinate);
        return randomCoordinate;
    }

    protected boolean isOutOfBounds(Coordinate coordinate) {
        int width = map.getWidth();
        return coordinate.x() < 0 || coordinate.x() >= width || coordinate.y() < 0 || coordinate.y() >= width;
    }
}
