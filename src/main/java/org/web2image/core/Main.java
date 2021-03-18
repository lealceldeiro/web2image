package org.web2image.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public final class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final int WORKING_DIR_ARGUMENT_POSITION_FROM_LAST_TO_FIRST = 2;
    private static final int DRIVER_BINARY_ARGUMENT_POSITION_FROM_LAST_TO_FIRST = 1;
    private static final int NUMBER_OF_NON_URL_ELEMENTS = 2;

    private Main() {
    }

    /**
     * Starts the program using the provided arguments.
     *
     * @param arguments Arguments to use in the program. There must be at least three arguments. One or more urls, a
     *                  directory location where the screenshots will be saved, and the firefox binary location.
     */
    public static void main(String[] arguments) {
        checkArgumentsAreValid();

        String[] links = linkFromProgramArguments(arguments);
        String workDirectory = workDirectoryFromArguments(arguments);
        String driverBinaryLocation = driverBinaryLocationFromArguments(arguments);
        RemoteWebDriver driver = DriverFactory.get(driverBinaryLocation);

        for (String link : links) {
            renderUrlAsImage(link, workDirectory, driver);
        }
    }

    private static String[] linkFromProgramArguments(String[] urls) {
        String[] links = new String[urls.length - NUMBER_OF_NON_URL_ELEMENTS];
        System.arraycopy(urls, 0, links, 0, urls.length - NUMBER_OF_NON_URL_ELEMENTS);

        return links;
    }

    private static String workDirectoryFromArguments(String[] arguments) {
        return arguments[arguments.length - WORKING_DIR_ARGUMENT_POSITION_FROM_LAST_TO_FIRST];
    }

    private static String driverBinaryLocationFromArguments(String[] arguments) {
        return arguments[arguments.length - DRIVER_BINARY_ARGUMENT_POSITION_FROM_LAST_TO_FIRST];
    }

    private static void renderUrlAsImage(String url, String location, RemoteWebDriver driver) {
        log.info("Opening '{}'", url);
        driver.get(url);

        log.info("Taking screenshot");
        File srcScreenShotFile = driver.getScreenshotAs(OutputType.FILE);

        ImageUtils.render(srcScreenShotFile, location);

        log.info("Done");
    }

    private static void checkArgumentsAreValid() {
        // TODO
    }
}
