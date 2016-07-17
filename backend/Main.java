import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
//import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.NumberFormatException;

import card.*;
import player.*;


import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

public class Main extends WebSocketClient {

    int start = 0;
    int waitP, waitOp;
    int turnBeginner = 0;
    Card[] cards = new Card[40];
    Integer[] involve = new Integer[10];
    Integer[][] add = new Integer[10][40];
    Integer develop;
    Player[] players = new Player[10];
    Integer[] que = new Integer[100]; // -1 or 0-38
    Integer f;

    public Main(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public Main(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient

        // initialize
        //String card_messages;// = errorMessage();
        //card_messages = get_card_message();

        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");

        send(list.toString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);

        String op =  null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);

            op = (String) json.get("operator");

            // Check
            System.out.println( "op: " + op );

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            int res = Integer.parseInt(op);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        // Take


        // get 51 integers

        // Vector<int> info = get_info();
        Vector<Integer> info = new Vector<Integer>();
        for(int i = 0; i < 51; ++i){
            info.add(i+100);
        }

        // make Json Package
        JSONObject obj = new JSONObject();
        obj.put("type", "message");

        JSONObject content_obj = new JSONObject();

        Vector<String> cardInHand = new Vector<String>();
        Vector<Integer> hp = new Vector<Integer>();
        Vector<Integer> gp = new Vector<Integer>();

        int num_player = 4;

        for(int i = 0; i < num_player; ++i){
            Vector<String> cards = new Vector<String>();
            for(int j = 0; j < 5; ++j){
                JSONObject card_obj = new JSONObject();
                card_obj.put("cardId", info.elementAt(i*10+j*2));
                card_obj.put("status", info.elementAt(i*10+j*2+1));
                cards.add(card_obj.toString());
            }
            cardInHand.add(cards.toString());
            hp.add(info.elementAt(40+i));
            gp.add(info.elementAt(44+i));
        }
        content_obj.put("cardInHand", cardInHand.toString());
        content_obj.put("hp", hp.toString());
        content_obj.put("gp", gp.toString());

        obj.put("content", content_obj.toString());
        obj.put("nextPlayer", info.elementAt(48));
        obj.put("nextOperator", info.elementAt(49));
        obj.put("clearDesk", info.elementAt(50));

        String messageToSend = obj.toString();
        send(messageToSend);


        /*
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);

            String type = (String) json.get("type");
            String content = (String) json.get("content");

            // Check
            System.out.println( "type: " + type );
            System.out.println( "content: " + content);

            String messageToSend;
            send(messageToSend);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
    }

    /*
    @Override
    public void onFragment( Framedata fragment ) {
        System.out.println( "received fragment: " + new String( fragment.getPayloadData().array() ) );
    }*/

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        Main c = new Main(new URI("ws://localhost:8888"), new Draft_10()); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }


    public String errorMessage() {
        JSONObject obj = new JSONObject();
        obj.put("type", "error");
        return obj.toJSONString();
    }
/*
    public ArrayList<Card> get_all_cards(){
        ArrayList<Card>cards = new ArrayList<Card>();

        ArrayList<String> dev_names = new ArrayList<String>();
        for(int i = 0; i < 5; ++i){

            for (int score = 1; score < 5; ++score){

            }
        }
    }
*/

    int getCard() {
        double tmp = Math.random();
        double tot  = 0;
        for(int i = 0; i < 39; ++i){
            tot += cards[i].getProb();
        }
        tmp *= tot;
        for(int i = 0;i  < 39; ++i){
            if(tmp < cards[i].getProb()) return i;
            tmp -= cards[i].getProb();
        }
        return 0;
    }
    void addCard(int i){
        if(players[i].getCards().size() >= 5) return;
        players[i].getCards().add(getCard());
    }

    int canPost(int people, int cardNumber) {
        if(people != waitP) return 0;
        if(f == 0 && cardNumber < 20) return 1;
        if(f == 0) return 0;
        if(que[0] != -1 && f < 4) {
            if(cardNumber < 20 && cardNumber / 4 == que[0] / 4) return 1;
            return 0;
        }
        if (cardNumber < 20) return 0;
        return 1;
    }

    int getClear() {
        if(f == 0) return 1;
        if(f < 5) return 0;
        if(que[0] == -1) {
            if(f % 4 == 1) {
                if(que[f - 1] == -1 && que[f - 2] == -1 && que[f - 3] == -1 && que[f - 4] == -1)
                    return 1;
            }
        } else {
            if(f % 4 == 0) {
                if(que[f - 1] == -1 && que[f - 2] == -1 && que[f - 3] == -1 && que[f - 4] == -1)
                    return 1;
            }
        }
        return 0;
    }

    Vector<Integer> playgame(int choose) {
        Vector<Integer> ret = new Vector<Integer>();
        if(start == 0){
            start = 1;
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 4; ++j){
                    addCard(i);
                }
            }
            waitP = 0;
            waitOp = 0;
            turnBeginner = 0;
            f = 0;
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 5; j++){
                    if (j < players[i].getCards().size()) {
                        int x = players[i].getCards().get(j);
                        ret.add(x);
                        ret.add(canPost(i, x));
                    } else {
                        ret.add(-1);
                        ret.add(-1);
                    }
                }
            }
            for(int i = 0; i < 4; ++i){
                ret.add(players[i].getHp());
            }
            for(int i = 0; i < 4; ++i) {
                ret.add(players[i].getGp());
            }
            ret.add(waitP);
            ret.add(waitOp);
            ret.add(getClear());
            return ret;
        }
        if (waitOp == 0) {
            int nowNumber = -1;
            if (choose != -1) {
                nowNumber = players[waitP].getCards().elementAt(choose);
                players[waitP].setHp(players[waitP].getHp() + cards[nowNumber].getHp());
                {
                    Vector<Integer> tmp = new Vector<Integer>();
                    tmp.clear();
                    for(int i = 0; i < players[waitP].getCards().size(); i++) {
                        if (i != choose) tmp.add(players[waitP].getCards().get(i));
                    }
                    players[waitP].getCards().clear();
                    for(int i = 0; i < tmp.size(); i++) {
                        players[waitP].getCards().add(tmp.get(i));
                    }
                }
            }
            que[f++] = nowNumber;
            if (f == 1 && choose != -1) {
                develop = nowNumber % 4 + 1;
                involve[waitP] = 1;
            }
            if (que[0] != -1 && f <= 4 && choose != -1) {
                involve[waitP] = 1;
            }
            if (waitOp == 0 && que[f - 1] >= 20 && que[f - 1] <= 26) {
                if (que[f - 1] == 20) {
                    for (int i = 0; i < 4; ++i) {
                        for (int j = 0; j < 3; ++j) add[i][j] *= -1;
                    }
                    develop *= -1;
                } else {
                    develop += cards[que[f - 1]].getAdd_develop_value();
                    develop *= cards[que[f - 1]].getTimes_develop_value();
                }
            }
        }
        if (waitOp == 1 && que[f - 1] >= 27) {
            for(int i = 0; i < 3; ++i){
                add[waitP][i] += cards[que[f - 1]].getDeltaAdd()[i];
                add[waitP][i] *= cards[que[f - 1]].getDeltaTimes()[i];
            }
        }

        if (waitP == turnBeginner && que[0] == -1 && f == 1) {
            ;
        } else {
            if (waitOp == 0 && que[f - 1] >= 27) waitOp = 1;
            else {
                waitOp = 0;
                waitP = (waitP + 1) % 4;
            }
        }
        if(getClear() > 0) {
            f = 0;
            for (int i = 0; i < 4; ++i){
                if(involve[i] != 0)
                    players[i].setGp(players[i].getGp() + develop);
                players[i].setHp(players[i].getHp() + add[i][0] - add[i][2]);
                players[i].setGp(players[i].getGp() + add[i][1] + add[i][2]);
                add[i][0] = 0;
                add[i][1] = 0;
                add[i][2] = 0;
                involve[i] = 0;
            }
            develop = 0;
            turnBeginner = (turnBeginner + 1) % 4;
            waitP = turnBeginner;
            waitOp = 0;
            for(int i = 0; i < 4; ++i) {
                addCard(i);
            }
        }
        {
            for(int i = 0; i < 4; ++i){
                for(int j = 0; j < 5; j++){
                    if (j < players[i].getCards().size()) {
                        int x = players[i].getCards().get(j);
                        ret.add(x);
                        ret.add(canPost(i, x));
                    } else {
                        ret.add(-1);
                        ret.add(-1);
                    }
                }
            }
            for(int i = 0; i < 4; ++i){
                ret.add(players[i].getHp());
            }
            for(int i = 0; i < 4; ++i) {
                ret.add(players[i].getGp());
            }
            ret.add(waitP);
            ret.add(waitOp);
            ret.add(getClear());
        }
        return ret;
    }

}