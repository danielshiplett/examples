package org.brewman.examples.scanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScannerTest {
    private static final Logger LOG = LoggerFactory
            .getLogger(ScannerTest.class);

    private static final int STEP_TIMEOUT = 10;

    @Test
    public void test() throws IOException {
        String data = "Users Input";

        System.setIn(new InputStream() {
            ByteArrayInputStream bos = new ByteArrayInputStream(data.getBytes());
            boolean first = true;

            @Override
            public int read() throws IOException {
                if (first) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        LOG.error(e.getMessage());
                    }
                    first = false;
                }

                int rtn = bos.read();
                LOG.debug("char: {}", rtn);
                return rtn;
            }
        });

        Scanner scanner = new Scanner(System.in);

        readLine(scanner);
    }

    public static String readLine(Scanner scanner) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        String res = null;

        LOG.debug("submitting");

        final Future<String> handler = executor.submit(() -> {
            LOG.debug("gonna scanner.nextLine()");
            String nextLine = scanner.nextLine();
            LOG.debug("nextLine: {}", nextLine);
            return nextLine.trim();
        });

        try {
            LOG.debug("waiting on result");
            res = handler.get(STEP_TIMEOUT, TimeUnit.SECONDS);
            LOG.debug("got result");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            handler.cancel(true);
        }

        return res;
    }
}
