package org.brewman.examples.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggerTest {
    private static final Logger LOG = LoggerFactory
            .getLogger(SimpleLoggerTest.class);

    @Test
    public void testLoggerOutput() {
        LOG.debug("test logger output");
    }

}
