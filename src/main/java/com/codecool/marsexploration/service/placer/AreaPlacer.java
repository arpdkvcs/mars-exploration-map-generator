package com.codecool.marsexploration.service.placer;

import com.codecool.marsexploration.model.Coordinate;

import java.util.Set;

public interface AreaPlacer {
    void placeArea(Set<Coordinate> area, String type);
}
