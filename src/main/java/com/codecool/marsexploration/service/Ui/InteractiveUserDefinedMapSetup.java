package com.codecool.marsexploration.service.Ui;

import com.codecool.marsexploration.Application;
import com.codecool.marsexploration.configuration.Configuration;
import com.codecool.marsexploration.model.GroupTerrainType;
import com.codecool.marsexploration.model.ResourceTerrainType;
import com.codecool.marsexploration.service.logger.Logger;
import com.codecool.marsexploration.service.validation.MapConfigurationValidator;
import com.codecool.marsexploration.service.validation.exceptions.MapConfigurationException;

import java.util.*;

import static java.lang.System.in;

public class InteractiveUserDefinedMapSetup {
    private final MapConfigurationValidator mapConfigurationValidator;
    private final int MAP_SIZE;
    private final Logger logger;

    public InteractiveUserDefinedMapSetup(int mapSize, Logger logger) {
        MAP_SIZE = mapSize;
        this.mapConfigurationValidator = new MapConfigurationValidator(mapSize);
        this.logger = logger;
    }

    public Configuration promptUserForConfiguration() {
        displayWelcomeMessage();
        String fileName = promptFileName();
        String generatorType = promptGeneratorType();
        Map<GroupTerrainType, List<Integer>> groupsMap = new HashMap<>();
        Map<ResourceTerrainType, Integer> resourcesMap = new HashMap<>();

        getGroupSizes(groupsMap, GroupTerrainType.MOUNTAIN);
        if (mapConfigurationValidator.getMaxNumberOfGroups() > 0) {
            getGroupSizes(groupsMap, GroupTerrainType.PIT);
        }

        if (mapConfigurationValidator.getGroups().get(GroupTerrainType.MOUNTAIN) != 0) {
            getResourceNumbers(resourcesMap, ResourceTerrainType.MINERAL);
        }
        if (mapConfigurationValidator.getGroups().get(GroupTerrainType.PIT) != 0) {
            getResourceNumbers(resourcesMap, ResourceTerrainType.WATER);
        }

        return new Configuration(fileName, MAP_SIZE, generatorType, groupsMap, resourcesMap);
    }

    private void displayWelcomeMessage() {
        System.out.println("""
                Welcome to Mars Exploration!
                                                            
                 This is a map generator application.
                 In a moment, we will ask you to input some information about the map we will generate for you.
                 These infos include the file's name, and number of terrain features, resources, etc.
                 
                 First you need to enter the number of terrain element groups (mountain and pits) and how many in that group you want.
                 After that we will ask you to input the number of resources to be placed. (minerals and pockets of water)
                 Keep in mind that the map has limited space for the features, but we will continue to remind you of this.
                 If all goes well, you will receive the generated map in the resources directory as .map and .txt file as well.
                 Have fun!
                 """);
    }

    private String promptFileName() {
        System.out.println("Enter the name for the generated map file. Default: generated-map (.map): ");
        Scanner scanner = new Scanner(in);
        String fileName = scanner.nextLine().trim();

        return fileName.isEmpty() ? "generated-map.map" : fileName + ".map";
    }

    private String promptGeneratorType() {
        while (true) {
            System.out.println("Enter\n" + Application.GROUP_GENERATOR_TYPE + " for terrain group based map generation (mountains and separately pits are grouped together),\n" + Application.RANDOM_GENERATOR_TYPE + " for legacy random map generation (mountains and pits are randomly placed - no grouping)");
            Scanner scanner = new Scanner(in);
            String generatorType = scanner.nextLine().trim();
            if (generatorType.equals(Application.GROUP_GENERATOR_TYPE) || generatorType.equals(Application.RANDOM_GENERATOR_TYPE)) {
                return generatorType;
            }
        }
    }

    private int getInt() {
        while (true) {
            try {
                Scanner scanner = new Scanner(in);

                return Math.abs(scanner.nextInt());
            } catch (Exception e) {
                System.out.println(Application.ANSI_RED + "Enter a valid integer!" + Application.ANSI_RESET);
            }
        }
    }

    private void getGroupSizes(Map<GroupTerrainType, List<Integer>> group, GroupTerrainType type) {
        int limitOfNumberOfGroups = mapConfigurationValidator.getMaxNumberOfGroups();
        List<Integer> groupSizes = new ArrayList<>();

        int numberOfAreas = limitOfNumberOfGroups + 1;

        numberOfAreas = getNumberOfAreasFromUser(type, limitOfNumberOfGroups, numberOfAreas);

        for (int i = 0; i < numberOfAreas; i++) {
            if (mapConfigurationValidator.getMaxGroupSize() == 0) {
                System.out.println(Application.ANSI_RED + "No space left for groups" + Application.ANSI_RESET);
                break;
            }

            System.out.println("Available free space for all types of groups: " + Application.ANSI_CYAN + mapConfigurationValidator.getFreeSpaceForGroups() + Application.ANSI_RESET);
            int maxGroupSize = mapConfigurationValidator.getMaxGroupSize();
            boolean isSizeOfGroupFits = false;

            getUserGivenGroupSize(type, groupSizes, i, maxGroupSize, isSizeOfGroupFits);
        }
        System.out.println("All groups of " + Application.ANSI_YELLOW + type + Application.ANSI_RESET + " have been set.\n");
        group.put(type, groupSizes);
    }

    private void getUserGivenGroupSize(GroupTerrainType type, List<Integer> groupSizes, int i, int maxGroupSize, boolean isSizeOfGroupFits) {
        while (!isSizeOfGroupFits) {
            System.out.println("Enter the size of "
                    + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                    + " group number "
                    + Application.ANSI_CYAN + (i + 1) + Application.ANSI_RESET
                    + " (max size is "
                    + Application.ANSI_CYAN + maxGroupSize + Application.ANSI_RESET
                    + "):");

            try {
                isSizeOfGroupFits = validateGivenGroupSize(type, groupSizes);
            } catch (MapConfigurationException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private boolean validateGivenGroupSize(GroupTerrainType type, List<Integer> groupSizes) {
        int areaSize = getInt();
        mapConfigurationValidator.validateGivenGroupSize(type, areaSize);
        groupSizes.add(areaSize);
        return true;
    }

    private int getNumberOfAreasFromUser(GroupTerrainType type, int limitOfNumberOfGroups, int numberOfAreas) {
        if (mapConfigurationValidator.getFreeSpaceForGroups() > 0) {
            while (numberOfAreas > limitOfNumberOfGroups) {
                System.out.println("Enter the number of "
                        + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                        + " groups (max: "
                        + Application.ANSI_CYAN + limitOfNumberOfGroups + Application.ANSI_RESET
                        + "):");
                numberOfAreas = getInt();

                if (numberOfAreas > limitOfNumberOfGroups) {
                    System.out.println("Enter an integer smaller or equal to " + limitOfNumberOfGroups);
                }
            }
            return numberOfAreas;
        }
        return 0;
    }

    private void getResourceNumbers(Map<ResourceTerrainType, Integer> resources, ResourceTerrainType type) {
        System.out.println("Available space for "
                + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                + "s: "
                + Application.ANSI_CYAN + mapConfigurationValidator.getFreeSpaceForResource(type) + Application.ANSI_RESET);
        System.out.println("Enter the number of "
                + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                + " resource:");
        int numberOfResource = 0;

        try {
            numberOfResource = getInt();
            mapConfigurationValidator.validateResourceSpaceAvailability(type, numberOfResource);
            logger.logInfo(numberOfResource + " type of " + type + "added successfully\n");
        } catch (MapConfigurationException e) {
            logger.logError(e.getMessage());
        }
        System.out.println("All "
                + Application.ANSI_YELLOW + type + Application.ANSI_RESET
                + " resources have been set.\n");
        resources.put(type, numberOfResource);
    }
}
