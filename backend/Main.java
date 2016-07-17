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

        try {
            int res = Integer.parseInt(message);
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

        Vector<Vector<Integer>> cardInHand = new Vector<Vector<Integer>>();
        Vector<Integer> hp = new Vector<Integer>();
        Vector<Integer> gp = new Vector<Integer>();

        int num_player = 4;

        for(int i = 0; i < num_player; ++i){
            Vector<Integer> cards = new Vector<Integer>();
            for(int j = 0; j < 10; ++j){
                cards.add(info.elementAt(i*10+j));
            }
            cardInHand.add(cards);
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
}