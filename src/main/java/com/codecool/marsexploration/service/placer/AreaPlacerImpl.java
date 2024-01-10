package com.codecool.marsexploration.service.placer;

import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AreaPlacerImpl extends TerrainPlacer implements AreaPlacer {
    private final List<Coordinate> coordinatesToTry;

    public AreaPlacerImpl(TerrainMap map) {
        super(map);
        this.coordinatesToTry = new ArrayList<>();
    }

    private void createCoordinatesToTry(TerrainMap map) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                coordinatesToTry.add(new Coordinate(x, y));
            }
        }
    }

    @Override
    public void placeArea(Set<Coordinate> area, String type) {
        createCoordinatesToTry(map);
        Coordinate startingPoint = getRandomCoordinate(coordinatesToTry);
        while (!allCoordinatesFitOnMap(area, startingPoint)) {
            startingPoint = getRandomCoordinate(coordinatesToTry);
        }
        for (Coordinate coordinate : area) {
            Coordinate mapCoordinate = new Coordinate(
                    startingPoint.x() + coordinate.x(),
                    startingPoint.y() + coordinate.y());
            map.setCell(mapCoordinate, type);
            coordinatesToTry.removeIf(c -> c.x() == coordinate.x() && c.y() == coordinate.y());
        }
        coordinatesToTry.clear();
    }

    private boolean allCoordinatesFitOnMap(Set<Coordinate> coordinates, Coordinate startingPoint) {
        for (Coordinate coordinate : coordinates) {
            Coordinate mapCoordinate = new Coordinate(startingPoint.x() + coordinate.x(), startingPoint.y() + coordinate.y());
            if (isOutOfBounds(mapCoordinate) || doesOverlapWith(mapCoordinate)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesOverlapWith(Coordinate coordinate) {
        return !map.getCells()[coordinate.x()][coordinate.y()].equals(" ");
    }
}
