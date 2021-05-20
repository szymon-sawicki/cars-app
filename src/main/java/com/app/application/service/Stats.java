package com.app.application.service;

import java.math.BigDecimal;
import java.util.List;

public class Stats <T>{

    private int min;
    private int max;
    private float avg;

/*
    public Stats(List<Integer> list) {

        if(list.get(0) instanceof BigDecimal) {
            max = list.stream().max(Integer::compareTo).get();
            avg = list.stream().reduce(BigDecimal.ZERO,(a,b) -> a.add) / listOfInts.size();
            min = list.stream().min(BigDecimal::compareTo).map();
        }


        max = list.stream().max(Integer::compareTo).get();
        avg = list.stream().reduce(0,Integer::sum) / listOfInts.size();
        min = list.stream().min(Integer::compareTo).get();

    }*

 */

}
