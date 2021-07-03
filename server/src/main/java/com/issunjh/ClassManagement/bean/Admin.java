package com.issunjh.ClassManagement.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/28 13:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int id;
    private String name;
    private String password;
    private String token;
}
