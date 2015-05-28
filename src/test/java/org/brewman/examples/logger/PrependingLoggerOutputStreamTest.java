package org.brewman.examples.logger;

import java.io.PrintStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class PrependingLoggerOutputStreamTest {
    private static final Logger LOG = LoggerFactory
            .getLogger(PrependingLoggerOutputStreamTest.class);

    @Test
    public void test() {
        /*
         * Configure the System.ERR to go to our logger.
         */
        System.setErr(new PrintStream(new PrependingLoggerOutputStream(LOG,
                Level.ERROR, "WHEEE: "), true));

        System.err.print("This is a test!");
        System.err.print("This is also a test!");
        System.err.print("One more line for good measure!");
    }
}
