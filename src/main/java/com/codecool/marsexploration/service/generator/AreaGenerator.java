package com.codecool.marsexploration.service.generator;

import com.codecool.marsexploration.model.Coordinate;

import java.util.Set;

public interface AreaGenerator {
    Set<Coordinate> generateArea(int area);
}
