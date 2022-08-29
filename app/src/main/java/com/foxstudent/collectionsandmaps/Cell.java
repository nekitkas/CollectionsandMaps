package com.foxstudent.collectionsandmaps;

import java.util.Objects;

public class Cell {
    public final String name;

    public Cell(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(name, cell.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
