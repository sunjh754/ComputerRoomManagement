package com.issunjh.ClassManagement.dao;

import com.issunjh.ClassManagement.bean.ClassUse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/30 15:01
 */
@Mapper
public interface ClassUseMapper {

    List<Integer> selectClassState(@Param("rid") int rid,
                                   @Param("beginTime") Time beginTime,
                                   @Param("endTime")Time endTime,
                                   @Param("dayTime")Date dayTime);

//    int addClassUse(ClassUse classUse);


    int addClassUse(@Param("rid") int rid,
                    @Param("tid") int tid,
                    @Param("beginTime") Time beginTime,
                    @Param("endTime")Time endTime,
                    @Param("dayTime")Date dayTime,
                    @Param("state")int state);

    List<ClassUse> displayAll();

    int updateById(@Param("id") int id);

    ClassUse selectStateById(@Param("id") int id);

    int refuseById(@Param("id") int id);

    List<ClassUse> displayUse();
}
