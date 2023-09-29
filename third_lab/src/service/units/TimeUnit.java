package service.units;

import java.time.Duration;
import java.util.Arrays;

public class TimeUnit {

    /**
     * Вычисляет средний временной интервал по массиву и возвращает
     * его в нанасекундах
     *
     * @param durationArray Массив временных интервалов
     * @return Средний интервал в нс
     */
    public static double averageTimeNanos(Duration[] durationArray) {
        return Arrays
                .stream(durationArray)
                .mapToLong(Duration::toNanos)
                .average()
                .orElseThrow();
    }
}
