package com.eduedu.chanpin.dao;

import com.eduedu.chanpin.domain.Know;

import java.util.List;

public interface KnowDao {


    int addKnow(Know know);

    void editKnow(Know know);

    List<Know> findKnowBySubjectId(Long subjectId);

    Know findKnowById(Long id);
}
