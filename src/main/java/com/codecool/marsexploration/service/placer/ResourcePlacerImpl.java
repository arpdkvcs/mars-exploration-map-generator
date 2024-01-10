package com.codecool.marsexploration.service.placer;


import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;

import java.util.ArrayList;
import java.util.List;

public class ResourcePlacerImpl extends TerrainPlacer implements ResourcePlacer {
    private final List<Coordinate> coordinatesToTry;

    public ResourcePlacerImpl(TerrainMap map) {
        super(map);
        this.coordinatesToTry = new ArrayList<>();
    }

    public void placeResources(String groupSymbol, String resourceSymbol, int numberOfResources) {
        for (int i = 0; i < numberOfResources; i++) {
            placeResource(groupSymbol, resourceSymbol);
        }
    }

    private void placeResource(String groupSymbol, String resourceSymbol) {
        coordinatesToTry.addAll(findCoordinatesOf(groupSymbol));
        Coordinate randomCoordinate = getRandomCoordinate(coordinatesToTry);

        while (!tryPlaceNextTo(randomCoordinate, resourceSymbol)) {
            randomCoordinate = getRandomCoordinate(coordinatesToTry);
        }
        coordinatesToTry.clear();
    }

    public List<Coordinate> findCoordinatesOf(String type) {
        List<Coordinate> coordinates = new ArrayList<>();
        String[][] cells = map.getCells();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                if (cells[x][y].equals(type)) {
                    coordinates.add(new Coordinate(x, y));
                }
            }
        }
        return coordinates;
    }

    private boolean tryPlaceNextTo(Coordinate coordinate, String type) {
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        List<int[]> availableDirections = new ArrayList<>();

        for (int[] direction : directions) {
            Coordinate newCoordinate = new Coordinate(coordinate.x() + direction[0], coordinate.y() + direction[1]);
            if (isCellEmpty(newCoordinate)) {
                availableDirections.add(direction);
            }
        }

        if (availableDirections.isEmpty()) {
            return false;
        }

        int[] randomDirection = availableDirections.get(random.nextInt(availableDirections.size()));
        Coordinate randomAvailableCoordinate = new Coordinate(coordinate.x() + randomDirection[0], coordinate.y() + randomDirection[1]);
        map.setCell(randomAvailableCoordinate, type);
        return true;
    }

    public boolean isCellEmpty(Coordinate coordinate) {
        if (isOutOfBounds(coordinate)) {
            return false;
        }
        return map.getCells()[coordinate.x()][coordinate.y()].equals(" ");
    }
}
