package com.codecool.marsexploration.service.validation;

import com.codecool.marsexploration.Application;
import com.codecool.marsexploration.model.GroupTerrainType;
import com.codecool.marsexploration.model.ResourceTerrainType;
import com.codecool.marsexploration.service.validation.exceptions.MapConfigurationException;

import java.util.HashMap;
import java.util.Map;

public class MapConfigurationValidator {
    private final Map<GroupTerrainType, ResourceTerrainType> terrainPairs;
    private final Map<GroupTerrainType, Integer> sizeOfGroups;
    private final Map<ResourceTerrainType, Integer> availableSpaceForResources;
    private int freeSpaceForGroups;

    public MapConfigurationValidator(int mapWidth) {
        int mapArea = mapWidth * mapWidth;
        this.freeSpaceForGroups = getPercentageOf(mapArea, 40);

        this.terrainPairs = new HashMap<>();
        terrainPairs.put(GroupTerrainType.MOUNTAIN, ResourceTerrainType.MINERAL);
        terrainPairs.put(GroupTerrainType.PIT, ResourceTerrainType.WATER);

        this.availableSpaceForResources = new HashMap<>();
        availableSpaceForResources.put(ResourceTerrainType.MINERAL, 0);
        availableSpaceForResources.put(ResourceTerrainType.WATER, 0);

        this.sizeOfGroups = new HashMap<>();
        sizeOfGroups.put(GroupTerrainType.MOUNTAIN, 0);
        sizeOfGroups.put(GroupTerrainType.PIT, 0);
    }

    public int getFreeSpaceForGroups() {
        return freeSpaceForGroups;
    }

    public int getFreeSpaceForResource(ResourceTerrainType type) {
        return availableSpaceForResources.get(type);
    }

    public int getMaxNumberOfGroups() {
        return getPercentageOf(freeSpaceForGroups, 10);
    }

    public int getMaxGroupSize() {
        return getPercentageOf(freeSpaceForGroups, 50);
    }

    public Map<GroupTerrainType, Integer> getGroups() {
        return sizeOfGroups;
    }

    public void validateGivenGroupSize(GroupTerrainType type, int areaOfGroupTerrain) throws MapConfigurationException {
        if (getMaxGroupSize() >= areaOfGroupTerrain) {
            freeSpaceForGroups = freeSpaceForGroups - areaOfGroupTerrain;

            int updatedGroupArea = sizeOfGroups.get(type) + areaOfGroupTerrain;
            sizeOfGroups.put(type, updatedGroupArea);

            int availableAreaForPairedResource = getPercentageOf(updatedGroupArea, 50);
            availableSpaceForResources.put(terrainPairs.get(type), availableAreaForPairedResource);
            return;
        }
        throw new MapConfigurationException("You have exceeded available group size!");
    }

    public void validateResourceSpaceAvailability(ResourceTerrainType type, int areaOfResourceTerrain) throws MapConfigurationException {
        if (availableSpaceForResources.get(type) >= areaOfResourceTerrain) {
            return;
        }
        throw new MapConfigurationException("Given number is bigger than available space for "
                + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                + "!");
    }

    private int getPercentageOf(int number, int percentage) {
        return (int) Math.floor((double) number / 100 * percentage);
    }
}
