package com.app;

import com.app.application.service.CarsService;
import com.app.infrastructure.Routing;
import spark.Spark;

public class App {

    public static void main(String[] args) {

        Spark.initExceptionHandler(e-> System.out.println(e.getMessage()));

        Spark.port(8000);

        var carsService = new CarsService("C:\\KMPROGRAMS\\PRAKTYKA\\cars-app-2\\src\\test\\resources\\car-1.json");

        var routing = new Routing(carsService);

        routing.routes();



    }

}


