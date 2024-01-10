package com.codecool.marsexploration.service.map_creator;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.Coordinate;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.generator.AreaGenerator;
import com.codecool.marsexploration.service.generator.AreaGeneratorImpl;
import com.codecool.marsexploration.service.placer.AreaPlacer;
import com.codecool.marsexploration.service.placer.AreaPlacerImpl;
import com.codecool.marsexploration.service.placer.ResourcePlacer;
import com.codecool.marsexploration.service.placer.ResourcePlacerImpl;

import java.util.List;
import java.util.Set;

public class GroupedMapCreator implements MapCreator {
    private final Configuration configuration;
    private final AreaPlacer areaPlacer;
    private final AreaGenerator areaGenerator;
    private final TerrainMap map;
    private final ResourcePlacer resourcePlacer;

    public GroupedMapCreator(Configuration configuration) {
        this.configuration = configuration;
        this.map = new TerrainMap(configuration.getWidth());
        this.areaPlacer = new AreaPlacerImpl(map);
        this.areaGenerator = new AreaGeneratorImpl();
        this.resourcePlacer = new ResourcePlacerImpl(map);
    }

    @Override
    public TerrainMap createMap() {
        configuration.getGroupTerrainConfig().forEach((terrainType, requiredAmounts) -> {
            createAndPlaceGroups(terrainType.getSymbol(), requiredAmounts);
        });

        configuration.getResourceTerrainConfig().forEach((terrainType, requiredAmount) -> {
            if (!configuration.getGroupTerrainConfig().get(terrainType.getGroup()).isEmpty()) {
                resourcePlacer.placeResources(terrainType.getGroup().getSymbol(), terrainType.getSymbol(),
                        requiredAmount);
            }
        });
        return map;
    }

    public void createAndPlaceGroups(String symbol, List<Integer> requiredAmounts) {
        Set<Coordinate> coordinates;
        for (Integer areaSize : requiredAmounts) {
            coordinates = areaGenerator.generateArea(areaSize);
            areaPlacer.placeArea(coordinates, symbol);
        }
    }


}
