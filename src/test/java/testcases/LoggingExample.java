package testcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    static Logger logger = LoggerFactory.getLogger(LoggingExample.class);
    Integer t;
    Integer oldT;

    public static void main(String[] args) {
        LoggingExample lExample = new LoggingExample();

        lExample.setTemperature(32);
        lExample.setTemperature(62);
        logger.info("Hello World");

    }

    public void setTemperature(Integer temperature) {
        oldT = t;
        t = temperature;

        logger.debug("Temp set to {}. old value was {}", t, oldT);

        if (temperature.intValue() > 50) {
            logger.info("Temp is above 50");
        }
    }
}
