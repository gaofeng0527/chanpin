package com.eduedu.chanpin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Chapter {

    private Long id;
    private String title;
    private Long subjectId;
    private String ccode;
    private String cctitle;
    private String orderL;
    private String video;
    private Subject subject;
    private List<Know> knows;
}
