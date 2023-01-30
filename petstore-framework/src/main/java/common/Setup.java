package common;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Setup {

    @BeforeTest
    @Parameters({"hostname"})
    public void setup(String hostName) {
        System.setProperty("hostName", hostName);
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j.resources");
    }
}
