package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    // Method to take a screenshot
    public static void takeScreenshot(WebDriver driver, String methodName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("./Screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(source, destination);
    }
}
