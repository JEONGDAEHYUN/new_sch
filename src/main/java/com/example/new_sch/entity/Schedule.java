package com.example.new_sch.entity;

import java.time.LocalDateTime;

public class Schedule {
    // id, 할일, 작성자명, 비밀번호, 작성일, 수정일
    private Long id;
    private String todo;
    private String name;
    private String password;
    private LocalDateTime creat_date;
    private LocalDateTime edit_date;
}
