package org.web2image.core;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

final class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    private static final String EXTENSION = ".png";
    private static int counter = 0;

    private ImageUtils() {}

    static void render(File srcScreenShotFile, String destination) {
        log.info("Saving image to {}", destination);

        String name = "Screenshot-" + imageNumber() + EXTENSION;
        String url = destination + File.separator + name;
        File destinationScreenShotFile = new File(url).getAbsoluteFile();
        try {
            FileUtils.copyFile(srcScreenShotFile, destinationScreenShotFile);
        } catch (IOException e) {
            log.error("Error while copying file", e);
        }
        log.info("Image {} saved", name);
    }

    private static int imageNumber() {
        return ++counter;
    }
}
