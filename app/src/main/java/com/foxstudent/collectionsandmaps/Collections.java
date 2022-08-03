package com.foxstudent.collectionsandmaps;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collections {

    private List<Integer> list;
    private Map<Integer,Integer> map;
    private long time;
    private final int value;


    Collections(ArrayList<Integer> arrayList, int value){
        this.list = arrayList;
        this.value = value;

    }

    Collections(LinkedList<Integer> linkedList, int value){
        this.list = linkedList;
        this.value = value;


    }
    Collections(CopyOnWriteArrayList<Integer> copyOnWriteArrayList , int value){
        this.list = copyOnWriteArrayList;
        this.value = value;

    }

    Collections(TreeMap<Integer,Integer> treeMap, int value){
        this.map = treeMap;
        this.value = value;
    }

    Collections(HashMap<Integer,Integer> hashMap, int value){
        this.map = hashMap;
        this.value = value;
    }


    public String getTime(){
        return String.valueOf(time);
    }

    public void treeMapAddingNew(){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void treeMapSearchByKey(){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < map.size(); i++) {
            map.get(i);
        }
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        map.get(index);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void treeMapRemoving(){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long startTime = System.currentTimeMillis();
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        map.remove(index);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }

    public void hashMapAddingNew(){
        HashMap<Integer,Integer> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void hashMapSearchByKey(){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        long startTime = System.currentTimeMillis();
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        map.get(index);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void hashMapRemoving(){
        HashMap<Integer,Integer> map = new HashMap<>();
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


    public void arrayListAddInTheBeginning(){
        long startTime = System.currentTimeMillis();
        list = new ArrayList<>(value);
        for (int i = 0; i < value; i++) {
            list.add(0,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void arrayListAddInTheMiddle(){
        long startTime = System.currentTimeMillis();
        list = new ArrayList<>(value);
        for (int i = 0; i < value; i++) {
            int index = list.size() / 2;
            list.add(Math.round(index),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void arrayListAddInTheEnd(){
        long startTime = System.currentTimeMillis();
        list = new ArrayList<>(value);
        for (int i = 0; i < value; i++) {
            list.add(list.size(),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void arrayListRemoveInTheBeginning(){
        list = new ArrayList<>(value);
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
    public void arrayListRemoveInTheMiddle(){
        list = new ArrayList<>(value);
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
    public void arrayListRemoveInTheEnd(){
        list = new ArrayList<>(value);
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
    public void arrayListSearchByValue(){
        list = new ArrayList<>(value-1);
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        list.add(index,1);
        long startTime = System.currentTimeMillis();
        list.indexOf(1);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListAddInTheBeginning(){
        long startTime = System.currentTimeMillis();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            list.addFirst(0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListAddInTheMiddle(){
        long startTime = System.currentTimeMillis();
        list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            int index = list.size() / 2;
            list.add(Math.round(index),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListAddInTheEnd(){
        long startTime = System.currentTimeMillis();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            list.addLast(0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListRemoveInTheBeginning(){
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.removeFirst();
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListRemoveInTheMiddle(){
        list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            int index = list.size() / 2;
            list.remove(index);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListRemoveInTheEnd() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.removeLast();
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void linkedListSearchByValue(){
        list = new LinkedList<>();
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        list.add(index,1);
        long startTime = System.currentTimeMillis();
        list.indexOf(1);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void copyOnWriteListAddInTheBeginning(){
        long startTime = System.currentTimeMillis();
        list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < value; i++) {
            list.add(0,0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void copyOnWriteListAddInTheMiddle(){
        long startTime = System.currentTimeMillis();
        list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < value; i++) {
            int index = list.size() / 2;
            list.add(Math.round(index),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void copyOnWriteListAddInTheEnd(){
        long startTime = System.currentTimeMillis();
        list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < value; i++) {
            list.add(list.size(),0);
        }
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
    public void copyOnWriteListRemoveInTheBeginning(){
        list = new CopyOnWriteArrayList<>();
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
    public void copyOnWriteListRemoveInTheMiddle(){
        list = new CopyOnWriteArrayList<>();
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
    public void copyOnWriteListRemoveInTheEnd(){
        list = new CopyOnWriteArrayList<>();
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
    public void copyOnWriteListSearchByValue(){
        list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
        int min = 0;
        int max = value;
        int index = (int)(Math.random()*(max - min + 1) + min);
        list.add(index,1);
        long startTime = System.currentTimeMillis();
        list.indexOf(1);
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
    }
}