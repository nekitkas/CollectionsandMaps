package com.foxstudent.collectionsandmaps;


import android.util.Log;

import java.util.List;
import java.util.Map;

public class Collections {


    public static String measureTime(Runnable runnable){
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        return String.valueOf(endTime - startTime);
    }

    public static String mapAddingNew(Map<Integer,Integer> map,int value){
        return measureTime(()->{
            for (int i = 0; i < value; i++) {
                map.put(i,0);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String mapSearchByKey(Map<Integer,Integer> map,int value){
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        return measureTime(()->{
            for (int i = 0; i < map.size(); i++) {
                map.get(i);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String mapRemoving(Map<Integer,Integer> map,int value){
        for (int i = 0; i < value; i++) {
            map.put(i,0);
        }
        return measureTime(()->{
            for (int i = 0; i < map.size(); i++) {
                map.remove(i);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listAddInTheBeginning(List<Integer> list,int value){
        return measureTime(()->{
            for (int i = 0; i < value; i++) {
                list.add(0,0);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listAddInTheMiddle(List<Integer> list,int value){
        return measureTime(()->{
            for (int i = 0; i < value; i++) {
                list.add(list.size() / 2,0);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listAddInTheEnd(List<Integer> list,int value){
        return measureTime(()->{
            for (int i = 0; i < value; i++) {
                list.add(list.size(),0);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listRemoveInTheBeginning(List<Integer> list,int value){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        return measureTime(()->{
            for (int i = 0; i < list.size(); i++) {
                list.remove(0);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listRemoveInTheMiddle(List<Integer> list,int value){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        return measureTime(()->{
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() / 2);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listRemoveInTheEnd(List<Integer> list,int value){
        for (int i = 0; i < value; i++) {
            list.add(0);
        }
        return measureTime(()->{
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() - 1);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }

    public static String listSearchByValue(List<Integer> list,int value){
        for (int i = 0; i < value - 1; i++) {
            list.add(0);
        }
        return measureTime(()->{
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
                if(Thread.interrupted()){
                    break;
                }
            }
        });
    }
}