//package com.js.route;
//
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
///***
// * @Description: 可以通过这种方式对网关的路由进行持久化
// * @Param
// * @Date: 2022/1/19 9:18 PM
// * @return
// */
//@Component
//public class InDatabaseRouteDefinitionRepository implements RouteDefinitionRepository {
//
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> save(Mono<RouteDefinition> route) {
//        return null;
//    }
//
//    @Override
//    public Mono<Void> delete(Mono<String> routeId) {
//        return null;
//    }
//}
