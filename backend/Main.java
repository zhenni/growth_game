import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
//import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public Main( URI serverUri , Draft draft ) {
        super( serverUri, draft );
    }

    public Main( URI serverURI ) {
        super( serverURI );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "opened connection" );
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "received: " + message );

        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);

            String type = (String) json.get("type");
            String content = (String) json.get("content");

            // Check
            System.out.println( "type: " + type );
            System.out.println( "content: " + content);

            String messageToSend;

            switch (type){
                case "initialize":
                    messageToSend = initialize();
                    break;
                case "get_card":
                    // exception?
                    messageToSend = get_card(Integer.parseInt(content));
                    break;
                case "getHpAndGp":
                    messageToSend = getHpAndGp(Integer.parseInt(content)));
                    break;
                case "postCard":
                    messageToSend = postCard(Integer.parseInt(content));
                    break;
                case "choosePlayer":
                    messageToSend = choosePlayer(Integer.parseInt(content));
                    break;
                case default:
                    messageToSend = errorMessage();
            }
            send(messageToSend);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
    @Override
    public void onFragment( Framedata fragment ) {
        System.out.println( "received fragment: " + new String( fragment.getPayloadData().array() ) );
    }*/

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main( String[] args ) throws URISyntaxException {
        Main c = new Main( new URI( "ws://localhost:8888" ), new Draft_10() ); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();
    }


    public String errorMessage(){
        JSONObject obj = new JSONObject();
        obj.put("type", "error");
        return obj.toJSONString();
    }
}
