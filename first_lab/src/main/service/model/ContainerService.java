package main.service.model;

import main.container.MyContainer;

public class ContainerService<T> {

    public MyContainer<T> createEmptyContainer() {
        return new MyContainer<>();
    }

    public MyContainer<T> createEmptyContainer(int size) {
        try {
            return new MyContainer<>(size);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void print(MyContainer<T> container) {
        if (container.isEmpty()) {
            System.out.println("Контейнер пуст");
        } else {
            System.out.println("\nКонтейнер содержит следующие значения:\n");
            container.forEach(System.out::println);
            System.out.println();
        }
    }
}
