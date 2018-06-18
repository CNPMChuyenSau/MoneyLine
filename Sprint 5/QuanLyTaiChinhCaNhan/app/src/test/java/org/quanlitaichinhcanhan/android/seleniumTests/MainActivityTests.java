
package org.quanlitaichinhcanhan.android.seleniumTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.client.SelendroidKeys;
import io.selendroid.common.SelendroidCapabilities;

/**
 * First Selendroid test, proof of concept.
 * This class works with a manually-run test server. That might be a preferable option when
 * running a set of tests instead of instantiating the server for each test class.
 * Run the server from the scripts directory.
 */
public class MainActivityTests {
    private static WebDriver driver = null;

    @BeforeClass
    public static void setup() throws Exception {
        SelendroidCapabilities caps = new SelendroidCapabilities("com.money.manager.ex");
        // use emulator only.
        caps.setEmulator(true);

        driver = new SelendroidDriver(caps);
    }

    @AfterClass
    public static void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void runActivity() {
        driver.findElement(By.id("skipTextView")).click();
        new Actions(driver).sendKeys(SelendroidKeys.MENU).perform();

    }
}
