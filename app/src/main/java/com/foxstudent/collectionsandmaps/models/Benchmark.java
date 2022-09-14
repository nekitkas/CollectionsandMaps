package com.foxstudent.collectionsandmaps.models;

import java.util.List;

public interface Benchmark {

    int getSpanCount();

    List<Cell> createCells(float result, boolean isInProgress);

    float measureTime(Cell cell, int operations);
}
