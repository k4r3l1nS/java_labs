package model.lists;

import model.TestList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestArrayList<T> extends ArrayList<T> implements TestList<T> {

    public TestArrayList(List<T> sourceList) {
        this.addAll(sourceList);
    }
    @Override
    public Duration testGetOperationTime(int index) {
        var startTime = LocalDateTime.now();
        get(index);
        return Duration.between(startTime, LocalDateTime.now());
    }

    @Override
    public Duration testAddOperationTime(T elem, int index) {
        var startTime = LocalDateTime.now();
        add(index, elem);
        return Duration.between(startTime, LocalDateTime.now());
    }

    @Override
    public Duration testAddOperationTime(T elem) {
        var startTime = LocalDateTime.now();
        add(elem);
        return Duration.between(startTime, LocalDateTime.now());
    }

    @Override
    public Duration testDeleteOperationTime(T elem) {
        var startTime = LocalDateTime.now();
        remove(elem);
        return Duration.between(startTime, LocalDateTime.now());
    }

    @Override
    public Duration testFindOperationTime(T elem) {
        var startTime = LocalDateTime.now();
        indexOf(elem);
        return Duration.between(startTime, LocalDateTime.now());
    }
}
