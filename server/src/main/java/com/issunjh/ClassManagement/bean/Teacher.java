package com.issunjh.ClassManagement.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 14:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private int id;
    private String teacherName;
    private String password;
    private String token;
}
