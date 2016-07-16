import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import org.json.*;

//import card.*;
//import player.*;

public class Main {


    /*
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);

        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String ret = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                ret += tempString;
            }
            System.out.println(ret);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ret;
    }*/
/*
    Vector<Card> cards;
    Vector<Integer> card, person;
    Player[] players = new Player[10];



    Card getRandomCard() {
        double tot = 0;
        int sz = cards.size();
        for(int i = 0; i < sz; i++){
            tot += cards.get(i).getProb();
        }
        double tmp = Math.random() * tot;
        for(int i = 0; i < sz; i++) {
            if(tmp < cards.get(i).getProb()) return i;
        }
        return i;
    }

    void showCard() {
        for(int i = 0; i < 4; ++i){
            S
        }
    }

    Boolean AskForDevelop (int index, int need) {
        if(need == -1)
            System.out.println("player" + index + "a develop card, please");
        else
            System.out.println("please " + index + "a needed tyoedevelop card, please" + need);
    }*/

    public static void main(String[] args) {
/*        int turn = 0;
        while(1) {
            {
                int ok = 0;
                for (int i = 0; i < 4; ++i) {
                    if (players[i].getHp() > 0) {
                        ok = 1;
                    }
                }
                if (ok == 0) {
                    int winner = 0;
                    for (int i = 1; i < 4; ++i) {
                        if (players[i].getGp() > players[winner].getGp())
                            winner = i;
                    }
                    printf("palyer" + (i + 1) + "win the game with growth" + players[i].getGp() + ", Good Game !\n");
                    return;
                }
            }
            if (AskForDevelop(turn, -1)) {
                for (int i = 1; i < 4; ++i) {
                    AskFor
                }
            }
            turn = (turn + 1) % 4;
        }*/
        return;
    }

}