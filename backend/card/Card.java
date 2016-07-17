package card;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.*;

import player.*;

public class Card {
    protected static int cardNum = 0;

    protected final int ID;
    protected double prob = 0;

    protected String name;
    protected String description;

    protected int hp; // Decrease hp value of the player
    protected int[] deltaAdd = new int[3];  // [0] for hp, [1] for gp, [2] for minus hp add gp
    protected int Add_develop_value;
    protected int Times_develop_value;
    protected int[] deltaTimes = new int[3];

    protected int develop_value = 0; // only for DevelopCard
    protected int develop_type = -1; // only for DevelopCard, -1 for invalid

    protected int cardToPlayer = -2; // -2 for invalid, -1 for all, o.w. for the selected player

    public Card(){
        ID = cardNum++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public void setCardToPlayer(int cardToPlayer) {
        this.cardToPlayer = cardToPlayer;
    }

    public int getID() {
        return ID;
    }

    public double getProb() {
        return prob;
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

    public String getName() {
        return name;
    }

    public int getDevelop_value() {
        return develop_value;
    }

    public void setDevelop_type(int develop_type) {
        this.develop_type = develop_type;
    }

    public int getDevelop_type() {
        return develop_type;
    }

    public String toString(){
        return "Card";
    }

    public String getDiscription(){
        return description;
    }

    public void setDescription(String description){
        this.description = this.description;
    }

    public int getAdd_develop_value() {
        return Add_develop_value;
    }

    public int getTimes_develop_value() {
        return Times_develop_value;
    }

    public void setAdd_develop_value(int add_develop_value) {
        Add_develop_value = add_develop_value;
    }

    public void setDevelop_value(int develop_value) {
        this.develop_value = develop_value;
    }

    public String generateJsonString() {
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("type", toString());
        obj.put("description", getDiscription());
        obj.put("hpchange", 1);
        obj.put("gpchange", 1);

        return obj.toString();
    }

    /*
    public JSONObject generateJson(){
        JSONObject obj = new JSONObject();
        obj.put("ID", new Integer(getID()));
        obj.put("name", getName());
        obj.put("description", getDiscription());

        obj.put("hp", new Integer(getHp()));
        obj.put("develop_value", new Integer(getDevelop_value()));
        obj.put("develop_type", new Integer(getDevelop_type()));
        obj.put("CardToPlayer", new Integer(getCardToPlayer()) );

        obj.put("developAdd", new Integer(getDeltaAdd()[0]));
        obj.put("HPAdd", new Integer(getDeltaAdd()[1]));
        obj.put("GPAdd", new Integer(getDeltaAdd()[2]));

        obj.put("developTimes", new Integer(getDeltaTimes()[0]));
        obj.put("HPTimes", new Integer(getDeltaTimes()[1]));
        obj.put("GPTimes", new Integer(getDeltaTimes()[2]));

        try {

            FileWriter file = new FileWriter("./test.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

        return obj;
    }
    */

}