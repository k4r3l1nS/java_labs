package test.model;

import main.service.IntegerContainerService;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class MyContainerTests {

    private static final IntegerContainerService SERVICE = new IntegerContainerService();

    public static void testAddingElement() {

        var container = SERVICE.createEmptyContainer();

        int elemToAdd = ThreadLocalRandom.current().nextInt();
        container.add(elemToAdd);

        assert container.size() > 0 && container.get(0) == elemToAdd;
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на добавление элемента пройден успешно");
    }

    public static void testContainmentCheck() {

        var container = SERVICE.createEmptyContainer();

        int testedElement = ThreadLocalRandom.current().nextInt();

        container.add(testedElement);

        assert container.contains(testedElement) && !container.contains(testedElement + 1);
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на проверку вхождения элемента пройден успешно");
    }

    public static void testDeletingElement()
            throws Exception {

        var container = SERVICE.createEmptyContainer();

        int testedElement = ThreadLocalRandom.current().nextInt();

        SERVICE.fillWithRandomInts(container, 10);
        container.add(testedElement);
        container.add(testedElement, 5);

        int containerMaxSize = container.size();
        container.delete(Integer.valueOf(testedElement));

        assert !container.contains(testedElement) && (containerMaxSize - container.size() == 2);
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на удаление элемента пройден успешно");
    }

    public static void testSizeFunc()
            throws Exception {

        var container = SERVICE.createEmptyContainer();
        SERVICE.fillWithRandomInts(container, 1);

        assert container.size() == 1;

        container.delete(0);

        assert container.size() == 0;
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на получение размерности контейнера пройден успешно");
    }

    public static void testClearingContainer()
            throws Exception {

        var container = SERVICE.createEmptyContainer();
        SERVICE.fillWithRandomInts(container, 10);

        container.clear();

        assert container.size() == 0;
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на очистку контейнера пройден успешно");
    }

    public static void testEmptinessCheck()
            throws Exception {

        var container = SERVICE.createEmptyContainer();

        assert container.isEmpty();

        SERVICE.fillWithRandomInts(container, 4);

        assert !container.isEmpty();
        System.out.println(LocalDateTime.now() +
                ": [INFO] Тест на проверку на пустоту пройден успешно");
    }
}
