package com.foxstudent.collectionsandmaps.models;


import java.util.List;
import java.util.Map;

public class Collections {


    public static String measureTime(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        return String.valueOf((float) (endTime - startTime) / 1000);
    }

    public static String mapAddingNew(Map<Integer, Integer> map, int value) {
        return measureTime(() -> {
            for (int i = 0; i < value; i++) {
                map.put(i, 0);
            }
        });
    }

    public static String mapSearchByKey(Map<Integer, Integer> map, int value) {
        fillMap(map, value);
        return measureTime(() -> {
            for (int i = 0; i < map.size(); i++) {
                map.get(i);
            }
        });
    }

    public static String mapRemoving(Map<Integer, Integer> map, int value) {
        fillMap(map, value);
        return measureTime(() -> {
            for (int i = 0; i < map.size(); i++) {
                map.remove(i);
            }
        });
    }

    public static String listAddInTheBeginning(List<Integer> list, int value) {
        return measureTime(() -> {
            for (int i = 0; i < value; i++) {
                list.add(0, 0);
            }
        });
    }

    public static String listAddInTheMiddle(List<Integer> list, int value) {
        return measureTime(() -> {
            for (int i = 0; i < value; i++) {
                list.add(list.size() / 2, 0);
            }
        });
    }

    public static String listAddInTheEnd(List<Integer> list, int value) {
        return measureTime(() -> {
            for (int i = 0; i < value; i++) {
                list.add(list.size(), 0);
            }
        });
    }

    public static String listRemoveInTheBeginning(List<Integer> list, int value) {
        fillList(list, value);
        return measureTime(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(0);
            }
        });
    }

    public static String listRemoveInTheMiddle(List<Integer> list, int value) {
        fillList(list, value);
        return measureTime(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() / 2);
            }
        });
    }

    public static String listRemoveInTheEnd(List<Integer> list, int value) {
        fillList(list, value);
        return measureTime(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() - 1);
            }
        });
    }

    public static String listSearchByValue(List<Integer> list, int value) {
        fillList(list, value);
        return measureTime(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        });
    }

    private static void fillList(List<Integer> list, int value) {
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
    }

    private static void fillMap(Map<Integer, Integer> map, int value) {
        for (int i = 0; i < value; i++) {
            map.put(i, 0);
        }
    }
}