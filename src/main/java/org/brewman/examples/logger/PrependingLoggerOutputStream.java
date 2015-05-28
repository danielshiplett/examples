package org.brewman.examples.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class PrependingLoggerOutputStream extends LoggerOutputStream {
    private static final Logger LOG = LoggerFactory
            .getLogger(PrependingLoggerOutputStream.class);

    private String prepend;

    public PrependingLoggerOutputStream(Logger log, Level level)
            throws IllegalArgumentException {
        super(log, level);
    }

    public PrependingLoggerOutputStream(Logger log, Level level, String prepend)
            throws IllegalArgumentException {
        super(log, level);
        this.prepend = prepend;
    }

    @Override
    public void flush() {
        if (getCount() == 0) {
            return;
        }

        if (prepend == null || prepend.isEmpty()) {
            return;
        }

        final byte[] bytes = new byte[getCount()];
        System.arraycopy(getBuf(), 0, bytes, 0, getCount());
        String str = new String(bytes);
        log(getLevel(), prepend + str);
        resetCount();
    }
}
