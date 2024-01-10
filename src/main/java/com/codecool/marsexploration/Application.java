package com.codecool.marsexploration;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.Ui.MapUi;
import com.codecool.marsexploration.service.filewriter.FileWriter;
import com.codecool.marsexploration.service.logger.ConsoleLogger;
import com.codecool.marsexploration.service.logger.Logger;
import com.codecool.marsexploration.service.map_creator.GroupedMapCreator;
import com.codecool.marsexploration.service.map_creator.MapCreator;
import com.codecool.marsexploration.service.map_creator.SimpleRandomMapCreator;
import com.codecool.marsexploration.service.validation.exceptions.MapGenerationException;

public class Application {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final String GROUP_GENERATOR_TYPE = "1";
    public static final String RANDOM_GENERATOR_TYPE = "2";

    private static final int MAP_SIZE = 32;

    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        MapUi ui = new MapUi(MAP_SIZE, logger);
        Configuration configuration = ui.promptUserForConfiguration();
        generateMap(configuration, logger);
    }

    private static void generateMap(Configuration configuration, Logger logger) {
        try {
            logger.logInfo("Map generator started"
                    + "\n  - Map dimensions (x and y): " + configuration.getWidth()
                    + "\n  - Map file: " + configuration.getFileName());

            MapCreator mapCreator = getMapCreator(configuration);

            TerrainMap map = mapCreator.createMap();
            FileWriter fileWriter = new FileWriter(configuration, logger);
            fileWriter.write(map);
            logger.logInfo("Map generated successfully");
        } catch (MapGenerationException e) {
            logger.logError("Failed to generate map: " + e.getMessage());
        }
    }

    private static MapCreator getMapCreator(Configuration configuration) {
        MapCreator mapCreator;
        String generatorType = configuration.getGeneratorType();
        if (generatorType.equals(RANDOM_GENERATOR_TYPE)) {
            mapCreator = new SimpleRandomMapCreator(configuration);
        } else if (generatorType.equals(GROUP_GENERATOR_TYPE)) {
            mapCreator = new GroupedMapCreator(configuration);
        } else {
            throw new MapGenerationException("No valid map generator was selected");
        }
        return mapCreator;
    }
}
