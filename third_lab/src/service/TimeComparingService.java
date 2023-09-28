package service;

import model.TestList;
import model.lists.TestArrayList;
import model.lists.TestLinkedList;

import java.time.Duration;
import java.util.List;

import static service.units.LoggerUnit.log;

public class TimeComparingService<T> {

    private final TestList<T> TEST_ARRAY_LIST;
    private final TestList<T> TEST_LINKED_LIST;

    private static final int TIMES_TO_TEST = 50;

    public TimeComparingService(List<T> list) {
        TEST_ARRAY_LIST = new TestArrayList<>(list);
        TEST_LINKED_LIST = new TestLinkedList<>(list);
    }

    public void compareGetOperation(int index) {
        Duration[] arrayListTime = new Duration[TIMES_TO_TEST];
        Duration[] linkedListTime = new Duration[TIMES_TO_TEST];
        for (int i = 0; i < TIMES_TO_TEST; ++i) {
            arrayListTime[i] = TEST_ARRAY_LIST.testGetOperationTime(index);
            linkedListTime[i] = TEST_LINKED_LIST.testGetOperationTime(index);
        }
        log(arrayListTime, linkedListTime, "Получение элемента по индексу");
    }

    public void compareAddOperation(T elem) {
        Duration[] arrayListTime = new Duration[TIMES_TO_TEST];
        Duration[] linkedListTime = new Duration[TIMES_TO_TEST];
        for (int i = 0; i < TIMES_TO_TEST; ++i) {
            arrayListTime[i] = TEST_ARRAY_LIST.testAddOperationTime(elem);
            linkedListTime[i] = TEST_LINKED_LIST.testAddOperationTime(elem);
        }
        log(arrayListTime, linkedListTime, "Добавление элемента в конец списка");
    }

    public void compareAddOperation(T elem, int index) {
        Duration[] arrayListTime = new Duration[TIMES_TO_TEST];
        Duration[] linkedListTime = new Duration[TIMES_TO_TEST];
        for (int i = 0; i < TIMES_TO_TEST; ++i) {
            arrayListTime[i] = TEST_ARRAY_LIST.testAddOperationTime(elem, index);
            linkedListTime[i] = TEST_LINKED_LIST.testAddOperationTime(elem, index);
        }
        log(arrayListTime, linkedListTime, "Добавление элемента по индексу");
    }

    public void compareDeleteOperation(T elem) {
        Duration[] arrayListTime = new Duration[TIMES_TO_TEST];
        Duration[] linkedListTime = new Duration[TIMES_TO_TEST];
        for (int i = 0; i < TIMES_TO_TEST; ++i) {
            arrayListTime[i] = TEST_ARRAY_LIST.testDeleteOperationTime(elem);
            linkedListTime[i] = TEST_LINKED_LIST.testDeleteOperationTime(elem);
        }
        log(arrayListTime, linkedListTime, "Удаление элемента по значению");
    }

    public void compareFindOperation(T elem) {
        Duration[] arrayListTime = new Duration[TIMES_TO_TEST];
        Duration[] linkedListTime = new Duration[TIMES_TO_TEST];
        for (int i = 0; i < TIMES_TO_TEST; ++i) {
            arrayListTime[i] = TEST_ARRAY_LIST.testFindOperationTime(elem);
            linkedListTime[i] = TEST_LINKED_LIST.testFindOperationTime(elem);
        }
        log(arrayListTime, linkedListTime, "Поиск элемента по значению");
    }
}
