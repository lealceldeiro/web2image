package org.web2image.core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

final class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    private static final String WEBDRIVER_BINARY_LOCATION_PROP = "webdriver.gecko.driver";
    private static final int WAIT_TIME = 5;

    private static final String GECKO_DRIVER = "geckodriver";

    private DriverFactory() {
    }

    static RemoteWebDriver get(String binaryPath) {
        return get(binaryPath, "--start-maximized", "--start-fullscreen", "--headless");
    }

    static RemoteWebDriver get(String binaryPath, String... arguments) {
        log.info("Creating driver");
        System.setProperty(WEBDRIVER_BINARY_LOCATION_PROP, binaryPath);


        RemoteWebDriver driver = driver(binaryPath, arguments);

        driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    private static RemoteWebDriver driver(String binaryPath, String... arguments) {
        String path = Objects.requireNonNull(binaryPath, "Driver cannot be null");

        if (path.contains(GECKO_DRIVER)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments(arguments);
            return new FirefoxDriver(options);
        }

        throw new IllegalArgumentException("Driver not supported");
    }
}
