package com.codecool.marsexploration.service.generator;

import com.codecool.marsexploration.model.Coordinate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AreaGeneratorImpl implements AreaGenerator {
    private final Random random;


    public AreaGeneratorImpl() {
        this.random = new Random();
    }

    @Override
    public Set<Coordinate> generateArea(int shapeCount) {
        if (shapeCount == 0) {
            return new HashSet<>();
        }

        Set<Coordinate> area = new HashSet<>();
        Coordinate startingCoordinate = new Coordinate(0, 0);
        area.add(startingCoordinate);

        growArea(area, shapeCount);

        return area;
    }

    private void growArea(Set<Coordinate> area, int targetArea) {
        while (area.size() < targetArea) {
            Coordinate coordinate = area.stream()
                    .skip(random.nextInt(area.size()))
                    .findFirst()
                    .orElse(null);

            if (coordinate == null) {
                return;
            }

            Coordinate neighbor = new Coordinate(
                    coordinate.x() + random.nextInt(3) - 1,
                    coordinate.y() + random.nextInt(3) - 1);
            area.add(neighbor);
        }
    }
}
