package com.example.new_sch.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    // id, 할일, 작성자명, 비밀번호, 작성일, 수정일
    private Long id;
    private String todo;
    private String name;
    private String password;
    private LocalDateTime create_date;
    private LocalDateTime edit_date;

    public Schedule(Long id, String todo, String name, LocalDateTime create_date, LocalDateTime edit_date) {
        this.id = id;
        this.todo = todo;
        this.name = name;
        this.create_date = create_date;
        this.edit_date = edit_date;
    }

    public Schedule(String todo, String name, String password){
        this.todo = todo;
        this.name = name;
        this.password = password;
        this.create_date = LocalDateTime.now();
        this.edit_date = LocalDateTime.now();
    }
}
