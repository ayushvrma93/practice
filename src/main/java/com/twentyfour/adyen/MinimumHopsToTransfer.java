package com.twentyfour.adyen;

import java.util.*;

public class MinimumHopsToTransfer {

    interface BankRoute {
        String getSource();
        String getDestination();
        int getCost();

    }

    static class BankRouteImpl implements BankRoute {
        private final String source;
        private final String destination;
        private final int cost;
        private final int hops;

        public BankRouteImpl(String source, String destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
            this.hops = 0;
        }

        public BankRouteImpl(String source, String destination, int cost,int hops) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
            this.hops = hops;
        }

        @Override
        public String getSource() {
            return source;
        }

        @Override
        public String getDestination() {
            return destination;
        }

        @Override
        public int getCost() {
            return cost;
        }


        public int getHops() {
            return hops;
        }
    }

    static class Node {
        String bank;
        int cost;
        int hops;

        Node(String bank, int cost, int hops) {
            this.bank = bank;
            this.cost = cost;
            this.hops = hops;
        }
    }

    public static int getCheapestRouteCost(String destination, List<BankRoute> routes, int maxHops) {
        // Build the graph
        Map<String, List<BankRoute>> graph = new HashMap<>();
        for (BankRoute route : routes) {
            graph.computeIfAbsent(route.getSource(), k -> new ArrayList<>()).add(route);
        }

        // BFS initialization
        Queue<BankRouteImpl> queue = new LinkedList<>();
        queue.offer(new BankRouteImpl("Adyen", "Adyen", 0,-1)); // Start with the source bank

        // Initialize cost map
        Map<String, Integer> costMap = new HashMap<>();
        costMap.put("Adyen", 0);

        int finalMinCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            BankRouteImpl currentRoute = queue.poll();
            String currentBank = currentRoute.getDestination();
            int currentCost = currentRoute.getCost();
            int currentHops = currentRoute.getHops(); //costMap.getOrDefault(currentBank, Integer.MAX_VALUE) ;

            if (currentHops > maxHops) continue; // Skip if exceeds max hops

            List<BankRoute> neighbors = graph.getOrDefault(currentBank, new ArrayList<>());

            for (BankRoute neighbor : neighbors) {
                int nextCost = currentCost + neighbor.getCost();
                int nextHops = currentHops + 1;

                if (nextHops <= maxHops) {
                    if (neighbor.getDestination().equals(destination)) { //changed here, from == to <=
                        finalMinCost = Math.min(finalMinCost, nextCost); // Return if destination is reached with exact hops
                        continue;
                    }

                    if (!costMap.containsKey(neighbor.getDestination()) || costMap.get(neighbor.getDestination()) > nextCost) {
                        costMap.put(neighbor.getDestination(), nextCost);
                        queue.offer(new BankRouteImpl(currentBank, neighbor.getDestination(), nextCost, nextHops));
                    }
                }
            }
        }

        return finalMinCost==Integer.MAX_VALUE ? -1: finalMinCost; // Return -1 if no valid route found
    }

    static class RouteInfo {
        String bank;
        int cost;
        int hops;

        RouteInfo(String bank, int cost, int hops) {
            this.bank = bank;
            this.cost = cost;
            this.hops = hops;
        }
    }

    public static void main(String[] args) {
        List<BankRoute> routes = Arrays.asList(
                new BankRouteImpl("Adyen", "BoA", 10),
                new BankRouteImpl("BoA", "Wells", 10),
                new BankRouteImpl("Wells", "Adyen", 10),
                new BankRouteImpl("BoA", "Chase", 60),
                new BankRouteImpl("Wells", "Chase", 20)
        );



        String destination = "Chase";
        int maxHops = 1;

        int result = getCheapestRouteCost(destination, routes, maxHops);
        System.out.println("Cheapest route cost to " + destination + ": " + result);
    }

}
