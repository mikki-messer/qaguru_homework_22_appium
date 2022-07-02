package com.github.mikkimesser.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.github.mikkimesser.configuration.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        // Set your access credentials
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);
        mutableCapabilities.setCapability("browserstack.user", browserstackConfig.login());
        mutableCapabilities.setCapability("browserstack.key", browserstackConfig.password());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", browserstackConfig.applId());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", browserstackConfig.device());
        mutableCapabilities.setCapability("os_version", browserstackConfig.osVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "QA.GURU homework lesson 12/21");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "Mike B selenide android test");
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);
            return new URL(browserstackConfig.browserstackURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
