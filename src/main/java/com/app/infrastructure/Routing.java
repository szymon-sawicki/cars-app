package com.app.infrastructure;

import com.app.application.exception.CarsServiceException;
import com.app.application.service.CarsService;
import com.app.application.type.SortItem;
import com.app.domain.car_body.type.CarBodyType;
import com.app.domain.engine.type.EngineType;
import com.app.infrastructure.config.JsonTransformer;
import com.app.infrastructure.dto.ResponseDto;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import spark.Spark;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;

import static spark.Spark.*;

@RequiredArgsConstructor

public class Routing {

    private final CarsService carsService;


    public void routes() {

        // TODO jak udokumentować API

        path("/cars/", () -> {

                    /**
                     *  param - sorting item
                     *  queryParam - direction of sorting - boolean(descending when true)
                     *  return sorted list of cars
                     */

                    get("/sort/:item",
                            (request, response) -> {
                                var sortItem = request.params(":item");
                                var itemEnum = SortItem.valueOf(sortItem.toUpperCase(Locale.ROOT));
                                var reversed = request.queryParams("reversed");
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.sort(itemEnum, Boolean.parseBoolean(reversed));
                            }, new JsonTransformer());

                    /**
                     *  param - car body type
                     *  query param - price from
                     *  query param - price to
                     *  return list of cars with given body type and price in range
                     */

                    get("/body_type/:type",
                            (request, response) -> {
                                var bodyType = CarBodyType.valueOf(request.params(":type").toUpperCase(Locale.ROOT));
                                var priceFrom = new BigDecimal(request.queryParams("price_from"));
                                var priceTo = new BigDecimal(request.queryParams("price_to"));
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.findCarsWithBodyTypeAndPriceInRange(bodyType, priceFrom, priceTo);
                            },
                            new JsonTransformer());

                    /**
                     * param - type of engine
                     * return list of cars with engine oof given type
                     */

                    get("/engine_type/:engine",
                            (request, response) -> {
                                var engineType = EngineType.valueOf(request.params("engine").toUpperCase(Locale.ROOT));
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.findAndSortCarsWithEngineType(engineType);

                            }, new JsonTransformer()
                    );

                    /**
                     * return map with cars as keys and mileage as value
                     */

                    get("/cars_with_mileage/",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.carsWithMileage();
                            }, new JsonTransformer());

                    /**
                     *  return map with type of tyre as key and number of cars having that type as value
                     */

                    get("/tyre_type_statistics/",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.tyreTypesWithCount();
                            }, new JsonTransformer()

                    );

                    /**
                     * param - list of components (String in format "COMPONENT1, COMPONENT2)
                     * return list of cars containing that components
                     */

                    get("/cars_with_given_components/:components",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                var components = Arrays.stream(request.params(":components").split(", ")).toList();
                                return carsService.findCarsWithGivenComponents(components);
                            }, new JsonTransformer()

                    );

                }
        );


        exception(CarsServiceException.class, (exception, request, response) -> {
            response.redirect("/error/" + exception.getMessage(), 301);
        });

        path("/error/", () -> {
            get("/:message",
                    (request, response) -> {
                        var message = request.params(":message");
                        response.header("Content-type", "application/json;charset=utf-8");
                        response.status(500);
                        var responseBody = ResponseDto.toError(message);
                        return toJson(responseBody);
                    }, new JsonTransformer()
            );
        });

        internalServerError((request, response) -> {
            response.header("Content-Type", "application/json;charset=utf-8");
            var responseBody = ResponseDto.toError("Unknown internal server error");
            return toJson(responseBody);
        });

        notFound((request, response) -> {
            response.header("Content-type", "application/json;charset=utf-8");
            response.status(404);
            var responseBody = ResponseDto.toError("Not found");
            return toJson(responseBody);
        });

    }


    private static <T> String toJson(T data) {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);

    }


}