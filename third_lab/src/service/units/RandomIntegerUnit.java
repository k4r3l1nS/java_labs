package service.units;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerUnit {

    public static void fillWithRandomInts(List<Integer> list, int count) {
        for (int i = 0; i < count; ++i) {
            list.add(ThreadLocalRandom.current().nextInt());
        }
    }
}
