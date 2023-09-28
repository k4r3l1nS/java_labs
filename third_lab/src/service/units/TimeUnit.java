package service.units;

import java.time.Duration;
import java.util.Arrays;

public class TimeUnit {

    public static double averageTimeNanos(Duration[] durationArray) {
        return Arrays
                .stream(durationArray)
                .mapToLong(Duration::toNanos)
                .average()
                .orElseThrow();
    }
}
