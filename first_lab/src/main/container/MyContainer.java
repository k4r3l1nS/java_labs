package main.container;

import java.util.Objects;
import java.util.function.Consumer;

public class MyContainer<T> {

    private Object[] array;
    private int lastObjectIndex = -1;

    /**
     * Первоначальный размер контейнера
     */
    private final int DEFAULT_CAPACITY = 10;

    public MyContainer() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public MyContainer(int capacity)
            throws Exception {
        if (capacity <= 0) {
            throw new Exception(
                    "Вместительность контейнера получила на вход некорректное значение: " +
                            capacity + ". Она должна принимать положительные значения"
            );
        }
        this.array = new Object[capacity];
    }

    public T get(int index) {
        return (T) array[index];
    }

    /**
     * Добавить элемент в конец контейнера
     *
     * @param elem Добавляемый элемент
     */
    public void add(T elem) {
        add(elem, lastObjectIndex);
    }

    /**
     * Добавление элемента на некоторую позицию в контейнере вместо некоторого элемента, который
     * помещается в конец контейнера. В случае, если позиция равна последней + 1, элемент просто
     * добваляется в конец
     *
     * @param elem Добавляемый элемент
     * @param index Индекс
     * @throws IndexOutOfBoundsException Выбрасывается в случае, если передаваемый индекс
     * находится за концом контейнера
     */
    public void add(T elem, int index)
            throws IndexOutOfBoundsException {

        if (index > lastObjectIndex + 1) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index +
                            " находится за границами контейнера текущей длины " + (lastObjectIndex + 1)
            );
        }

        ++lastObjectIndex;
        array[lastObjectIndex] = elem;

        if (index != -1 && index != lastObjectIndex) {
            swapByIndex(index, lastObjectIndex);
        }

        if (isFilled())
            expand();
    }

    /**
     * Удалить все вхождения элемента по его значению
     *
     * @param elem Удаляемый элемент
     * @return Статус операции
     */
    public boolean delete(T elem) {
        if (!contains(elem)) {
            return false;
        }
        for (int index = 0; index <= lastObjectIndex; ++index) {
            if (array[index].equals(elem)) {
                delete(index);
                if (index <= lastObjectIndex && array[index].equals(elem)) {
                    --index;
                }
            }
        }
        return true;
    }

    /**
     * Проверка на содержание элемента в контейнере
     *
     * @param elem Значение элемента
     * @return Есть ли элемент в контейнере
     */
    public boolean contains(T elem) {

        for (Object thisElem : array) {
            if (thisElem.equals(elem))
                return true;
        }
        return false;
    }

    public void forEach(Consumer<? super T> action) {

        Objects.requireNonNull(action);

        int size = this.lastObjectIndex + 1;
        for(int i = 0; i < size; ++i) {
            action.accept(get(i));
        }
    }

    public boolean isEmpty() { return lastObjectIndex == -1;}

    public void clear() {
        for (int index = 0; index < lastObjectIndex; ++index) {
            array[index] = null;
        }
        lastObjectIndex = -1;
    }

    /**
     * Получить размерность контейнера
     *
     * @return Число элементов в контейнере
     */
    public int size() { return lastObjectIndex + 1;}

    /**
     * Расширить контейнер
     */
    private void expand() {
        Object[] expandedArray = new Object[2 * array.length];
        for (int i = 0; i < array.length; ++i) {
            expandedArray[i] = array[i];
        }
        array = expandedArray;
    }

    /**
     * Удалить элемент по его индексу
     *
     * @param index Индекс
     */
    public void delete(int index) {
        if (index < lastObjectIndex) {
            swapByIndex(index, lastObjectIndex);
        }
        array[lastObjectIndex] = null;
        --lastObjectIndex;
    }

    /**
     * Проверка на заполненность контейнера
     *
     * @return Заполнен ли контейнер
     */
    private boolean isFilled() {
        return lastObjectIndex == array.length - 1;
    }

    /**
     * Поменять значения 2-х элементов контейнера по индексам
     *
     * @param ind1 Первый индекс
     * @param ind2 Второй индекс
     */
    private void swapByIndex(int ind1, int ind2) {
        final var tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }
}
