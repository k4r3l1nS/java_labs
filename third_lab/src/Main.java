import service.TimeComparingService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static service.units.RandomIntegerUnit.fillWithRandomInts;

public class Main {

    private static final int DEFAULT_VALUE_COUNT = 1000;

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        fillWithRandomInts(integerList, DEFAULT_VALUE_COUNT);

        TimeComparingService<Integer> timeComparingService =
                new TimeComparingService<>(integerList);


        System.out.println("\n");
        timeComparingService.compareFindOperation(integerList.get(DEFAULT_VALUE_COUNT / 2));
        timeComparingService.compareGetOperation(DEFAULT_VALUE_COUNT / 2);
        timeComparingService.compareAddOperation(ThreadLocalRandom.current().nextInt());
        timeComparingService.compareAddOperation(
                ThreadLocalRandom.current().nextInt(),
                DEFAULT_VALUE_COUNT / 2
        );
        timeComparingService.compareDeleteOperation(integerList.get(DEFAULT_VALUE_COUNT / 2));
    }
}