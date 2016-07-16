package player;

import java.util.*;

import card.*;

public class Player{
    protected static final int INIT_HP = 100;
    protected static final int INIT_GP = 0;
    protected static final int MAX_NUM_CARDS = 5;
    protected static int playerNum = 0;

    protected final int ID;
    protected int hp = 0; // health
    protected int gp = 0; // growth
    protected Vector<Card> cards;

    public Player(){
        cards = new Vector<Card>();
        ID = playerNum++;
    }

    public Player(int hp, int gp){
        this.hp = hp;
        this.gp = gp;
        cards = new Vector<Card>();
        ID = playerNum++;
    }

/*
    public static void setInitGp(int initGp) {
        INIT_GP = initGp;
    }

    public static void setInitHp(int initHp) {
        INIT_HP = initHp;
    }

    public static void setMaxNumCards(int maxNumCards) {
        MAX_NUM_CARDS = maxNumCards;
    }
*/

    public int getID() {
        return ID;
    }

    public static int getPlayerNum(){
        return playerNum;
    }

    public int getHp(){
        return hp;
    }

    public int getGp() {
        return gp;
    }

    public Vector<Card> getCards() {
        return cards;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean addCard(Card newcard){
        if (cards.size() > MAX_NUM_CARDS) return false;
        cards.add(newcard);
        return true;
    }
}