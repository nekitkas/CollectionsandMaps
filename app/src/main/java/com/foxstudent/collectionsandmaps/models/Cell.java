package com.foxstudent.collectionsandmaps.models;

import java.util.Objects;

public class Cell {
    public final String name;
    public final String result;

    public Cell(String name, String result) {
        this.name = name;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(name, cell.name) && Objects.equals(result, cell.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
