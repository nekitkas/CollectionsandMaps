package com.foxstudent.collectionsandmaps;

import java.util.Objects;

public class Cell {
    private String name;
    private String result;

    public Cell(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(name, cell.name) &&
                Objects.equals(result, cell.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, result);
    }
}
