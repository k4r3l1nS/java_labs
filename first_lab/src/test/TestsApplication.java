package test;

import test.model.MyContainerTests;

public class TestsApplication {

    public static void main(String[] args) {

        System.out.println("\nЗапуск тестов\n");

        try {
            MyContainerTests.testEmptinessCheck();
            MyContainerTests.testAddingElement();
            MyContainerTests.testClearingContainer();
            MyContainerTests.testContainmentCheck();
            MyContainerTests.testDeletingElement();
            MyContainerTests.testSizeFunc();

            System.out.println("\nВсе тесты пройдены успешно\n");
        } catch (Exception ex) {
            System.out.println("Провален тест с ошибкой: ");
            ex.printStackTrace();
        }
    }
}
