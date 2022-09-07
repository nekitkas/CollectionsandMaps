package com.foxstudent.collectionsandmaps.models;

import androidx.annotation.StringRes;

import java.util.Objects;

public class Cell {
    public final int name;
    public final String result;
    public final boolean isInProgress;
    public final int operation;

    public Cell(@StringRes int name, String result,@StringRes int operation, boolean isInProgress) {
        this.name = name;
        this.result = result;
        this.operation = operation;
        this.isInProgress = isInProgress;
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
