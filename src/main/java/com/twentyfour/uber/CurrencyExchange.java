package com.twentyfour.uber;

/*
We are a currency exchange that maintains the current exchange rates between currencies. A user can come to us with some amount in one currency and request the equivalent amount in a different currency. Given a list of exchange rates between currencies, write a function that calculates currency rate between any 2 currencies.
Example 1 -
(GBP, EUR, 10)     - read as "1 GBP equals 10 EUR"
(EUR, USD, 1.1)    - "1 EUR equals 1.1 USD"
(10 GBP, USD) => ? - "10 GBP equals how many USD?"

Answer: 110
Explanation: 10 GBP = 10 x (10 EUR) = 10 x (10 x (1.1 USD)) = 110 USD

Example 2 -
(GBP, EUR, 10)     - read as "1 GBP equals 10 EUR"
(EUR, USD, 1.1)    - "1 EUR equals 1.1 USD"
(USD, JPY, 108.3)
(10 GBP, JPY) => ? - "10 GBP equals how much JPY?"

Example 3 -
(GBP, CAD, 1.671)
(INR, CAD, 0.0185)
(CAD, EUR, 0.685)
(1 EUR, INR) => ?
*/

import java.util.*;

class Node{
    String srcCurrency;
    String destCurrency;
    Double rate;

    Node(String srcCurrency, String destCurr, Double rate){

        this.srcCurrency = srcCurrency;
        this.destCurrency = destCurr;
        this.rate = rate;
    }

    Node(String srcCurrency, Double rate){
        this.srcCurrency = srcCurrency;
        this.rate = rate;
    }
}
public class CurrencyExchange {

    public Double getExchangeRate(List<Node> list, String source, String dest){
        Map<String, List<Node>> graph = new HashMap<>();

        for(Node curr : list){
            graph.computeIfAbsent(curr.srcCurrency, k -> new ArrayList<>()).add(curr);
            Node revCurr = new Node(curr.destCurrency, curr.srcCurrency, 1/ curr.rate);
            graph.computeIfAbsent(curr.destCurrency, k -> new ArrayList<>()).add(revCurr);
        }

        if(source.equals(dest)){
            return 1.0;
        }

        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(new Node(source, 1.0));
        visited.add(source);

        while(!q.isEmpty()){
            Node cn = q.poll();

            if(cn.srcCurrency.equals(dest)){
                return cn.rate;
            }

            List<Node> neighbours = graph.getOrDefault(cn.srcCurrency, new ArrayList<>());

            for(Node neighbour : neighbours){
                if(!visited.contains(neighbour.destCurrency)){
                    visited.add(neighbour.destCurrency);
                    q.add(new Node(neighbour.destCurrency, cn.rate * neighbour.rate));
                }
            }
        }

        return -1.0;

    }

    public static void main(String[] args) {

        /*
        (GBP, EUR, 10)     - read as "1 GBP equals 10 EUR"
        (EUR, USD, 1.1)    - "1 EUR equals 1.1 USD"
        (USD, JPY, 108.3)
        (10 GBP, JPY) => ? - "10 GBP equals how much JPY?"

        (GBP, CAD, 1.671)
        (INR, CAD, 0.0185)
        (CAD, EUR, 0.685)
        (1 EUR, INR) => ?
        */

        CurrencyExchange solution = new CurrencyExchange();
        Node node1 = new Node("GBP", "EUR", 10.0);
        Node node2 = new Node("EUR", "USD", 1.1);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);

        Node node3 = new Node("GBP", "CAD", 1.671);
        Node node4 = new Node("INR", "CAD", 0.0185);
        Node node5 = new Node("CAD", "EUR", 0.685);

//        Node node6 = new Node("CAD", "GBP", 1/1.671);
//        Node node7 = new Node("CAD", "INR", 1/0.0185);
//        Node node8 = new Node("EUR", "CAD", 1/0.685);

        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(node3);
        nodes2.add(node4);
        nodes2.add(node5);
//        nodes2.add(node6);
//        nodes2.add(node7);
//        nodes2.add(node8);


        Double amount = solution.getExchangeRate(nodes2, "EUR", "INR");
        Double quantity = 1.0;

        if(amount == -1.0){
            System.out.println("Invalid source and destination input");
        }

        System.out.print(quantity * amount);
    }

}
