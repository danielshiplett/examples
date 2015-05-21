package org.brewman.examples.logger;

import java.io.PrintStream;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class MultipleOutputStreamTest {
    private static final Logger LOG = LoggerFactory
            .getLogger(MultipleOutputStreamTest.class);

    @Test
    public void test() {
        /*
         * Create the LoggerOutputStream.
         */
        LoggerOutputStream los = new LoggerOutputStream(LOG, Level.ERROR);

        /*
         * Tee the output to the main System.ERR and to our Logger.
         */
        TeeOutputStream tos = new TeeOutputStream(System.err, los);

        /*
         * Set the new System.ERR to be our Tee.
         */
        System.setErr(new PrintStream(tos, true));

        System.err.print("Multiple OutputStreams!!!!");
    }

}
