package com.twentyfour.adyen;

import java.util.*;

public class MinHops2{

    interface BankRoute {
        String getSource();
        String getDestination();
        int getCost();
    }

    static class BankRouteImpl implements BankRoute {
        private final String source;
        private final String destination;
        private final int cost;

        private int hops;

        public BankRouteImpl(String source, String destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }

        public BankRouteImpl(String source, String destination, int cost, int hops) {
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

    public static int getCheapestRouteCost(String destination, List<BankRoute> routes, int maxHops) {
        // Build the graph
        Map<String, List<BankRoute>> graph = new HashMap<>();
        for (BankRoute route : routes) {
            graph.computeIfAbsent(route.getSource(), k -> new ArrayList<>()).add(route);
        }

        // BFS initialization
        Queue<BankRouteImpl> queue = new LinkedList<>();
        queue.offer(new BankRouteImpl("Adyen", routes.get(0).getDestination(), routes.get(0).getCost(), 0)); // Start with the source bank

        // Initialize cost map
        Map<String, Integer> costmap = new HashMap<>();
        costmap.put("Adyen", 0);

        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            BankRouteImpl currentRoute = queue.poll();
            String currentBank = currentRoute.getDestination();
            int currentCost = currentRoute.getCost();
            int currentHops = currentRoute.getHops();

            if (currentHops > maxHops) continue; // Skip if exceeds max hops

            List<BankRoute> neighbors = graph.getOrDefault(currentBank, new ArrayList<>());

            for (BankRoute neighbor : neighbors) {
                int newCost = currentCost + neighbor.getCost();
                int newHops = currentHops + 1;

                if (newHops <= maxHops) {
                    if (neighbor.getDestination().equals(destination)) { //changed here, from == to <=
                        minCost = Math.min(minCost, newCost); // Return if destination is reached with exact hops
                        continue;
                    }

                    if (!costmap.containsKey(neighbor.getDestination()) || costmap.get(neighbor.getDestination()) > newCost) {
                        costmap.put(neighbor.getDestination(), newCost);
                        queue.offer(new BankRouteImpl(currentBank, neighbor.getDestination(), newCost, newHops));
                    }
                }
            }
        }

        return minCost==Integer.MAX_VALUE ? -1: minCost; // Return -1 if no valid route found
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
