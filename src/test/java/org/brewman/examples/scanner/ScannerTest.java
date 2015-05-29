package org.brewman.examples.scanner;

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
    public void test() {
        readLine(new Scanner(System.in));
    }

    public static String readLine(Scanner scanner) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // ExecutorService executor = Executors.newSingleThreadExecutor();

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
