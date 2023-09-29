package service.units;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerUnit {

    /**
     * Заполнить список целых чисел рандомными значениями
     * @param list Заполняемый список
     * @param count Количество добавляемых значений
     */
    public static void fillWithRandomInts(List<Integer> list, int count) {
        for (int i = 0; i < count; ++i) {
            list.add(ThreadLocalRandom.current().nextInt());
        }
    }
}
