package com.codecool.marsexploration.service.filewriter;

import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.TerrainMap;
import com.codecool.marsexploration.service.logger.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    private final Configuration configuration;
    private final Logger logger;

    public FileWriter(Configuration configuration, Logger logger) {
        this.configuration = configuration;
        this.logger = logger;
    }

    private void saveToFile(String path, String content) {
        try (PrintWriter write = new PrintWriter(path)) {
            write.println(content);
        } catch (IOException e) {
            logger.logError(e.getMessage());
        }
    }

    public void write(TerrainMap map) {
        String WORK_DIR = "src/main/resources/";
        String filePath = WORK_DIR + configuration.getFileName();

        String txtDir = "src/main/resources/save_txt/";
        String txtFilePath = txtDir + configuration.getFileName().replace(".map", ".txt");

        String stringMap = convertMapToString(map);

        saveToFile(filePath, stringMap);
        saveToFile(txtFilePath, stringMap);
    }

    private String convertMapToString(TerrainMap map) {
        String stringMap = "";

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                String tileType = map.getCells()[x][y];
                stringMap = stringMap.concat(tileType);
            }
            stringMap = stringMap.concat("\n");
        }

        return stringMap;
    }
}
