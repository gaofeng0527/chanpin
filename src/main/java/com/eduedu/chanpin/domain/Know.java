package com.eduedu.chanpin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Know {

    private Long id;
    private String kcode;
    private String title;
    private Long times;
    private Long chapterId;
    private Long subjectId;
    private Chapter chapter;
}
