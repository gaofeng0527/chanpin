package com.eduedu.chanpin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 科目
 */

@Getter
@Setter
@ToString
public class Subject {

    private Long id;
    private String title;
    private String module;
    private String code;
    private String definition;
    private boolean requireRTE;
    private boolean requireAQ;
    private Date addTime;

}
