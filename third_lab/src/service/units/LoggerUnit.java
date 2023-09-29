package service.units;

import java.time.Duration;

import static service.units.TimeUnit.averageTimeNanos;

public class LoggerUnit {

    /**
     * Вычислить и вывести в консоль средние значения тестов
     * и разницу между этими значениями
     *
     * @param arrayListTime Тесты пополняемого массива
     * @param linkedListTime Тесты связного списка
     * @param operationName Название тестируемой операции
     */
    public static void log(
            Duration[] arrayListTime,
            Duration[] linkedListTime,
            String operationName,
            int numberOfTests
    ) {
        double arrayListTimeMillis = averageTimeNanos(arrayListTime);
        double linkedListTimeMillis = averageTimeNanos(linkedListTime);

        boolean arrayListIsFaster = arrayListTimeMillis < linkedListTimeMillis;

        System.out.println("Тест опервции \"" + operationName + "\" имеет следующие результаты:");
        System.out.println("Количество выполнений операции: " + numberOfTests);
        System.out.println("Среднее время выполнения для пополняемого массива: " + arrayListTimeMillis + " нс   " +
                (arrayListIsFaster ? "✓✓✓" : "×××"));
        System.out.println("Среднее время выполнения для связного списка: " + linkedListTimeMillis + " нс   " +
                (arrayListIsFaster ? "×××" : "✓✓✓"));
        System.out.println("Разница [%]: " +
                ((arrayListIsFaster ? linkedListTimeMillis : arrayListTimeMillis)
                        / (arrayListIsFaster ? arrayListTimeMillis : linkedListTimeMillis) - 1) * 100
                + "\n"
        );
    }
}
