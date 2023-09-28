package model;

import java.time.Duration;

public interface TestList<T> {

    /**
     * Тест на получение элемента по индексу
     *
     * @param index Индекс элемента
     * @return Время выполнения теста
     */
    Duration testGetOperationTime(int index);

    /**
     * Тест на добавление элемента по индексу
     *
     * @param elem Добавляемый элемент
     * @param index Индекс элемента
     * @return Время выполнения теста
     */
    Duration testAddOperationTime(T elem, int index);

    /**
     * Тест на добавление элемента в конец списка
     *
     * @param elem Добавляемый элемент
     * @return Время выполнения теста
     */
    Duration testAddOperationTime(T elem);

    /**
     * Тест на удаление элемента по значению
     *
     * @param elem Удаляемый элемент
     * @return Время выполнения теста
     */
    Duration testDeleteOperationTime(T elem);

    /**
     * Тест на поиск элемента по значению
     *
     * @param elem Искомый элемент
     * @return Время выполнения теста
     */
    Duration testFindOperationTime(T elem);
}
