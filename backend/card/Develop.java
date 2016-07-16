package card;

import java.util.*;

import player.*;

public class Develop extends Card {

    public int getDevelop_value() {
        return develop_value;
    }

    public void setDevelop_value(int develop_value) {
        this.develop_value = develop_value;
    }

    @Override
    public String toString(){
        return "Develop";
    }
}