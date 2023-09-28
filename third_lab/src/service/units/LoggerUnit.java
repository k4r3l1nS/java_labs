package service.units;

import java.time.Duration;

import static service.units.TimeUnit.averageTimeNanos;

public class LoggerUnit {

    public static void log(
            Duration[] arrayListTime,
            Duration[] linkedListTime,
            String operationName
    ) {
        double arrayListTimeMillis = averageTimeNanos(arrayListTime);
        double linkedListTimeMillis = averageTimeNanos(linkedListTime);

        boolean arrayListIsFaster = arrayListTimeMillis < linkedListTimeMillis;

        System.out.println("Тест опервции \"" + operationName + "\" имеет следующие результаты:");
        System.out.println("Среднее время выполнения для пополняемого массива: " + arrayListTimeMillis + " нс   " +
                (arrayListIsFaster ? "✓✓✓" : "×××"));
        System.out.println("Среднее время выполнения для связного списка: " + linkedListTimeMillis + " нс   " +
                (arrayListIsFaster ? "×××" : "✓✓✓"));
        System.out.println("Разница [%]: " +
                ((double) (arrayListIsFaster ? linkedListTimeMillis : arrayListTimeMillis)
                        / (arrayListIsFaster ? arrayListTimeMillis : linkedListTimeMillis) - 1) * 100
                + "\n"
        );
    }
}
