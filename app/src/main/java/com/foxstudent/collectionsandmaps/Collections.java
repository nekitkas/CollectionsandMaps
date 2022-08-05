package com.foxstudent.collectionsandmaps;


import java.util.List;
import java.util.Map;

public class Collections {

    private List<Integer> list;
    private Map<Integer,Integer> map;
    private long time;
    private final int value;


    Collections(List<Integer> list, int value){
        this.list = list;
        this.value = value;
    }

    Collections(Map<Integer,Integer> map, int value){
        this.map = map;
        this.value = value;
    }

    public String getTime(){
        return String.valueOf(time);
    }


    public void mapAddingNew(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void mapSearchByKey(){
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < map.size(); i++) {
            map.get(i);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void mapRemoving(){
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < map.size(); i++) {
            map.remove(i);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listAddInTheBeginning(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            list.add(0,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listAddInTheMiddle(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            int index = list.size() / 2;
            list.add(Math.round(index),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listAddInTheEnd(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            list.add(list.size(),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listRemoveInTheBeginning(){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.remove(0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listRemoveInTheMiddle(){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            int index = list.size() / 2;
            list.remove(Math.round(index));
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listRemoveInTheEnd(){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            int index = list.size() - 1;
            list.remove(index);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void listSearchByValue(){
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
}