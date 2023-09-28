package main.service;

import main.container.MyContainer;
import main.service.model.ContainerService;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerContainerService extends ContainerService<Integer> {

    public void fillWithRandomInts(MyContainer<Integer> container, int count)
            throws Exception {

        if (!container.isEmpty()) {
            throw new Exception("Контейнер должен быть пуст");
        }

        for (int index = 0; index < count; ++index) {
            container.add(
                    ThreadLocalRandom.current().nextInt(),
                    index
            );
        }
    }

    public void deleteAllOdds(MyContainer<Integer> container) {

        MyContainer<Integer> allOdds = new MyContainer<>();

        container.forEach(elem -> {
            if (elem != null && elem % 2 != 0) {
                allOdds.add(elem);
            }
        });

        allOdds.forEach(elem -> container.delete(elem));
    }
}
