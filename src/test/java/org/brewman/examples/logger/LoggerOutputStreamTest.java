package org.brewman.examples.logger;

import java.io.PrintStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class LoggerOutputStreamTest {
    private static final Logger LOG = LoggerFactory
            .getLogger(LoggerOutputStreamTest.class);

    @Test
    public void test() {
        /*
         * Configure the System.ERR to go to our logger.
         */
        System.setErr(new PrintStream(new LoggerOutputStream(LOG, Level.ERROR),
                true));

        System.err.print("This is a test!");
    }

}
