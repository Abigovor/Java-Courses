package main.ru.abigovor;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdentifier {
    private static AtomicInteger counter = new AtomicInteger();

    public static int getNextInt() {
        return counter.incrementAndGet();
    }
}
