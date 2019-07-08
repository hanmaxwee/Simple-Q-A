package com.example.none;

import java.util.ArrayList;

public class Question {
    public int id;
    public String title;
    public ArrayList<Option> options = new ArrayList<Option>();
    public int score;

    public Question() {

    }
    public void selectOption(int index){
        for(Option option : options) {
            option.selected = false;
        }
        Option option = options.get(index);
        option.selected = true;
    }

    public int countScore(){
        score = 0;
        for (Option opt: options) {
            if (opt.selected)
                score += opt.score;
        }
        return  score;
    }
}
