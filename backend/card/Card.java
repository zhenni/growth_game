package card;

import java.util.*;

import player.*;

public class Card {
    protected static int cardNum = 0;

    protected final int ID;
    protected double prob = 0;

    protected int hp; // Decrease hp value of the player
    protected int[] deltaAdd = new int[3];  // [0] for develop, [1] for hp, [2] for gp
    protected int[] deltaTimes = new int[3];

    protected int cardToPlayer; // -1 for all, o.w. for the selected player

    public Card(){
        ID = cardNum++;
    }

    public int getID() {
        return ID;
    }

    public double getProb() {
        return prob;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public int getHp() {
        return hp;
    }

    public int getCardToPlayer() {
        return cardToPlayer;
    }

    public int[] getDeltaAdd() {
        return deltaAdd;
    }

    public int[] getDeltaTimes() {
        return deltaTimes;
    }

    public String toString(){
        return "Card";
    }
}