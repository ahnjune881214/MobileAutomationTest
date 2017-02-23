package com.wemakeprice.driver;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;

public class CapabilitiesFactory {

    protected static DesiredCapabilities capabilities;

    public static DesiredCapabilities getCapabilities() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("deviceName", "we의 iPhone");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("automationName", "Appium");
		
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
        return capabilities;
    }

}
