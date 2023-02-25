package com.twentythree.gameskraft;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class LowestUniqueBid {

    @Data
    @AllArgsConstructor
    static class Input{
        String playerName;
        int amount;
    }

    @Data
    static class AmountAndPlayers{
        int amount;
        List<String> players;
    }

    @Data
    @AllArgsConstructor
    static class OccAndInput{
        int occ;
        int amount;

        List<String> playersList;
    }

    PriorityQueue<OccAndInput> occVsInputPq = new PriorityQueue(Comparator.comparingInt(OccAndInput::getOcc));
    PriorityQueue<AmountAndPlayers> amountVsPlayers = new PriorityQueue(Comparator.comparingInt(AmountAndPlayers::getAmount));

    public String addBid(Input input){

        Iterator itr = occVsInputPq.iterator();
        boolean isAlreadyAvailable = false;

        OccAndInput newOcc = null;

        while(itr.hasNext()){

            OccAndInput curr = (OccAndInput) itr.next();

                if(curr.getAmount() == input.getAmount()){

                    List<String> players = curr.getPlayersList();
                    players.add(input.getPlayerName());
                    newOcc = curr;
                    int o = curr.getOcc();
                    o--;
                    if(o==0){
                        occVsInputPq.remove(curr);
                    } else {
                        curr.setOcc(o);
                    }
                    isAlreadyAvailable = true;
                    break;
                }
            }
        if(isAlreadyAvailable) {
            int occurence = newOcc.getOcc() + 1;
            occVsInputPq.add(new OccAndInput(occurence, newOcc.getAmount(), newOcc.getPlayersList()));
        } else {
            List<String> players = new ArrayList<>();
            players.add(input.getPlayerName());
            occVsInputPq.add(new OccAndInput( 1, input.getAmount(), players));
        }
        //System.out.println(occVsInputPq);
        return input.getPlayerName();
    }

    public List<String> getWinner(){
        return occVsInputPq.peek().getPlayersList();
    }

    public static void main(String[] args) {
        LowestUniqueBid lowestUniqueBid = new LowestUniqueBid();

        Input input1 = new Input("U1", 6);
        Input input2 = new Input("U2", 7);
        Input input3 = new Input("U3", 6);
        Input input4 = new Input("U4", 7);
        Input input5 = new Input("U5", 10);
        lowestUniqueBid.addBid(input1);
        lowestUniqueBid.addBid(input2);
        lowestUniqueBid.addBid(input3);
        lowestUniqueBid.addBid(input4);
        lowestUniqueBid.addBid(input5);


        System.out.println(lowestUniqueBid.getWinner());
    }
}
