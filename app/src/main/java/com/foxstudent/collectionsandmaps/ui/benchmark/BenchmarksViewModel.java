package com.foxstudent.collectionsandmaps.ui.benchmark;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;
import com.foxstudent.collectionsandmaps.models.Cell;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class BenchmarksViewModel extends ViewModel {

    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Integer> toastMessage = new MutableLiveData<>();
    private final Handler handler = new Handler();
    private final Benchmark benchmark;
    private ThreadPoolExecutor service;

    public BenchmarksViewModel(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public void onCreate() {
        cells.setValue(benchmark.createCells(0, false));
        setToastMessage(0);
    }

    public int getSpanCount() {
        return benchmark.getSpanCount();
    }

    public void updateCell(int position, float result, boolean isInProgress) {
        final List<Cell> cellList = cells.getValue();
        cellList.set(position, new Cell(
                cellList.get(position).name,
                String.valueOf(result),
                cellList.get(position).operation,
                isInProgress));
        cells.setValue(cellList);
    }

    public void executeBenchmarks(int operation, int threadPool) {
        service = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPool);

        final List<Cell> cellList = cells.getValue();
        for (Cell cell : cellList) {
            service.submit(() -> {
                final float result = benchmark.measureTime(cell, operation);
                handler.post(() -> updateCell(cellList.indexOf(cell), result, false));
                if (service.getCompletedTaskCount() == cellList.size() - 1) {
                    handler.post(() -> setToastMessage(R.string.calc_complete));
                }
            });
        }
        service.shutdown();
    }

    public void hideProgressBar() {
        final List<Cell> cellList = cells.getValue();
        for (int i = 0; i < cellList.size(); i++) {
            cellList.set(i, new Cell(cellList.get(i).name, cellList.get(i).result, cellList.get(i).operation, false));
        }
        cells.setValue(cellList);
    }

    public void shutDown() {
        service.shutdownNow();
        hideProgressBar();
        setToastMessage(R.string.calc_stop);
    }

    public void onButtonPressed(String operation, String threadPool) {
        if (service != null && service.getActiveCount() > 0) {
            shutDown();
            return;
        }
        if (operation.isEmpty() || threadPool.isEmpty()) {
            setToastMessage(R.string.empty_field);
        } else {
            int operationToInt, threadPoolToInt;
            try {
                operationToInt = Integer.parseInt(operation);
                threadPoolToInt = Integer.parseInt(threadPool);
            } catch (NumberFormatException exception) {
                setToastMessage(R.string.must_be_numeric);
                return;
            }
            if (operationToInt <= 0 || threadPoolToInt <= 0) {
                setToastMessage(R.string.must_be_positive);
            } else {
                executeBenchmarks(operationToInt, threadPoolToInt);
                cells.setValue(benchmark.createCells(0, true));
                setToastMessage(R.string.calc_start);
            }
        }
    }

    public LiveData<List<Cell>> getCells() {
        return cells;
    }

    public LiveData<Integer> getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(int message) {
        toastMessage.setValue(message);
    }
}
