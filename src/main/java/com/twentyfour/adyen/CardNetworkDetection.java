package com.twentyfour.adyen;


import java.util.*;

class NetworkEntry{
    Long start;
    Long end;
    String network;

    public NetworkEntry(String start, String end, String network) {
        this.start = Long.parseLong(start);
        this.end = Long.parseLong(end);
        this.network = network;
    }
}
public class CardNetworkDetection {

    List<NetworkEntry> networks = new ArrayList<>();

    public void sort(){
        networks.sort(Comparator.comparing(networkEntry -> networkEntry.start));
    }

    public String findNetwork(Long cardNumber, int low, int high){
        while(low <= high){
            int mid = low + (high - low)/2;

            NetworkEntry midEntry = networks.get(mid);

            if(cardNumber >= midEntry.start  && cardNumber <= midEntry.end){
                return midEntry.network;
            }

            else if(cardNumber > midEntry.end){
                low = mid + 1;
            }

            else{
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CardNetworkDetection cardNetworkDetection = new CardNetworkDetection();

        cardNetworkDetection.networks = new ArrayList<>(List.of(
                new NetworkEntry("400000", "499999", "Visa"),
                new NetworkEntry("510000", "559999", "MasterCard"),
                new NetworkEntry("340000", "349999", "American Express"),
                new NetworkEntry("370000", "379999", "Dinners Club")
        ));

//        Collections.sort(cardNetworkDetection.networks, Comparator.comparing(networkEntry -> networkEntry.start));
//        cardNetworkDetection.networks.sort(Comparator.comparing(networkEntry -> networkEntry.start));
        cardNetworkDetection.sort();
        System.out.println(cardNetworkDetection.findNetwork(349699L, 0, cardNetworkDetection.networks.size()-1));
        System.out.println(cardNetworkDetection.findNetwork(510200L, 0, cardNetworkDetection.networks.size()-1));
        System.out.println(cardNetworkDetection.findNetwork(4111111111111111L, 0, cardNetworkDetection.networks.size()-1));
    }
}