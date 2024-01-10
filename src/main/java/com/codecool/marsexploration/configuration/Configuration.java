package com.codecool.marsexploration.configuration;

import com.codecool.marsexploration.model.GroupTerrainType;
import com.codecool.marsexploration.model.ResourceTerrainType;

import java.util.List;
import java.util.Map;

public class Configuration {
    private final String fileName;
    private final int mapWidth;
    private final String generatorType;
    private final Map<GroupTerrainType, List<Integer>> groupTerrainConfig;
    private final Map<ResourceTerrainType, Integer> resourceTerrainConfig;

    public Configuration(String fileName, int mapWidth, String generatorType, Map<GroupTerrainType, List<Integer>> groupTerrainConfig, Map<ResourceTerrainType, Integer> resourceTerrainConfig) {
        this.fileName = fileName;
        this.mapWidth = mapWidth;
        this.generatorType = generatorType;
        this.groupTerrainConfig = groupTerrainConfig;
        this.resourceTerrainConfig = resourceTerrainConfig;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWidth() {
        return mapWidth;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public Map<GroupTerrainType, List<Integer>> getGroupTerrainConfig() {
        return Map.copyOf(groupTerrainConfig);
    }

    public Map<ResourceTerrainType, Integer> getResourceTerrainConfig() {
        return Map.copyOf(resourceTerrainConfig);
    }
}
