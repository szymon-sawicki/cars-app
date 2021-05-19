package com.app.application;

import com.app.application.converter.CarsServiceConverter;

public class App {

    public static void main(String[] args) {

        var cars = new CarsServiceConverter("C:\\KMPROGRAMS\\PRAKTYKA\\cars-app\\src\\test\\resources\\car-1.json")
                .fromJson()
                .orElseThrow();

        System.out.println(cars);

    }


}
