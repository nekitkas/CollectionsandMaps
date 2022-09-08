package com.foxstudent.collectionsandmaps.models;

import androidx.annotation.StringRes;

import java.util.Objects;

public class Cell {
    public final int name;
    public final String result;
    public final int operation;
    public final boolean isInProgress;

    public Cell(@StringRes int name, String result, @StringRes int operation, boolean isInProgress) {
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
        return Objects.equals(name, cell.name) &&
                Objects.equals(result, cell.result) &&
                Objects.equals(operation, cell.operation) &&
                Objects.equals(isInProgress, cell.isInProgress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name) +
                Objects.hash(result) +
                Objects.hash(operation) +
                Objects.hash(isInProgress);
    }
}
