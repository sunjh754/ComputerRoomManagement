package com.issunjh.ClassManagement.service;

import com.issunjh.ClassManagement.bean.ClassUse;
import com.issunjh.ClassManagement.dao.ClassUseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/30 15:01
 */
@Service
public class ClassUseServiceImp implements ClassUseService {
    @Autowired
    ClassUseMapper classUseMapper;

    @Override
    public List<Integer> selectClassState(int rid, Time beginTime, Time endTime, Date dayTime) {
        return classUseMapper.selectClassState(rid, beginTime, endTime,dayTime);
    }

    @Override
    public int addClassUse(int rid, int tid, Time beginTime, Time endTime, Date dayTime, int state) {
        return classUseMapper.addClassUse(rid,tid,beginTime,endTime,dayTime,state);
    }

    @Override
    public List<ClassUse> displayAll() {
        return classUseMapper.displayAll();
    }

    @Override
    public int updateById(int id) {
        return classUseMapper.updateById(id);
    }

    @Override
    public int refuseById(int id) {
        return classUseMapper.refuseById(id);
    }

    @Override
    public List<ClassUse> displayUse() {
        return classUseMapper.displayUse();
    }

    @Override
    public ClassUse selectStateById(int id) {
        return classUseMapper.selectStateById(id);
    }


}
