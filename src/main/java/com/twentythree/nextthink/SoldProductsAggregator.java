//package com.twentythree.nextthink;
//
//import lombok.Value;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class SoldProductsAggregator {
//
//    @Value
//    class SoldProduct {
//        String name;
//        BigDecimal price;
//        String currency;
//    }
//    @Value
//    class SimpleSoldProduct {
//        String name;
//        BigDecimal price;
//    }
//
//    @Value
//    class SoldProductsAggregate {
//        List<SimpleSoldProduct> products;
//        BigDecimal total;
//    }
//
//    SoldProductsAggregate aggregate(Stream<SoldProduct> products) {
//
//        List<SoldProduct> list = products.collect(Collectors.toList());
//
//        for (SoldProduct product : list){
//            BigDecimal bigDecimal = new BigDecimal(0);
//            //bigDecimal.eq
//            if (bigDecimal != null) {
//
//            }
//        }
//    }
//}
