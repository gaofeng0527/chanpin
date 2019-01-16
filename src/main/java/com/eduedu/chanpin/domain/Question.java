package com.eduedu.chanpin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Question {

    private Long id;
    private String qcode;
    private String title;
    private String desc;
    private String answer;
    private String type;
    private double score;
    private String analysis;
    private Long knowId;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String optione;
    private String optionf;
    private String optiong;
    private Long subjectId;
    private Know know;
    private String upAnswer;
    private String lowerAnswer;
    private String knowTitle;

    public String getUpAnswer(){
        return this.answer.toUpperCase();
    }

    public String getLowerAnswer(){
        return this.answer.toLowerCase();
    }
}
