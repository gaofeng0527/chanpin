package com.eduedu.chanpin.dao.mybatis;

import com.eduedu.chanpin.dao.KnowDao;
import com.eduedu.chanpin.dao.mybatis.mapper.KnowMapper;
import com.eduedu.chanpin.domain.Know;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KnowDaoImpl implements KnowDao {

    @Autowired
    private KnowMapper knowMapper;

    @Override
    public int addKnow(Know know) {
        return knowMapper.addKnow(know);
    }

    @Override
    public void editKnow(Know know) {
        knowMapper.editKnow(know);
    }

    @Override
    public List<Know> findKnowBySubjectId(Long subjectId) {
        return knowMapper.findKnowBySubjectId(subjectId);
    }

    @Override
    public Know findKnowById(Long id) {
        return knowMapper.findKnowById(id);
    }

}
