package main;

import main.service.IntegerContainerService;

public class FirstLabApplication {

    private final static int RANDOM_INTS_COUNT = 5;

    public static void main(String[] args) {

        IntegerContainerService containerService = new IntegerContainerService();

        var container = containerService.createEmptyContainer();
        try {
            containerService.fillWithRandomInts(container, RANDOM_INTS_COUNT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        containerService.print(container);

        containerService.deleteAllOdds(container);
        containerService.print(container);
    }
}