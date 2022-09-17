package com.foxstudent.collectionsandmaps.ui.benchmark;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;
import com.foxstudent.collectionsandmaps.models.Cell;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BenchmarksViewModel extends ViewModel {

    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Integer> toastMessage = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final Benchmark benchmark;

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
        final List<Cell> cellList = cells.getValue();
        final ConcurrentHashMap<Integer, Float> result = new ConcurrentHashMap<>();
        final Disposable disposable = Observable.fromIterable(cellList)
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(threadPool)))
                .doOnNext(cell -> result.put(cellList.indexOf(cell), benchmark.measureTime(cell, operation)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> setToastMessage(R.string.calc_complete))
                .subscribe(cell -> updateCell(cellList.indexOf(cell), result.get(cellList.indexOf(cell)), false));
        compositeDisposable.add(disposable);
    }

    public void hideProgressBar() {
        final List<Cell> cellList = cells.getValue();
        for (int i = 0; i < cellList.size(); i++) {
            cellList.set(i, new Cell(cellList.get(i).name, cellList.get(i).result, cellList.get(i).operation, false));
        }
        cells.setValue(cellList);
    }


    public void onButtonPressed(String operation, String threadPool) {
        if (toastMessage.getValue() == R.string.calc_start) {
            compositeDisposable.clear();
            hideProgressBar();
            setToastMessage(R.string.calc_stop);
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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
