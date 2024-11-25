package com.example.new_sch.dto;

import com.example.new_sch.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String name;
    private LocalDateTime creat_date;
    private LocalDateTime edit_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.name = schedule.getName();
        this.creat_date = schedule.getCreate_date();
        this.edit_date = schedule.getEdit_date();
    }

    public ScheduleResponseDto(Long id, String todo, String name, LocalDateTime creat_date, LocalDateTime edit_date) {
        this.id = id;
        this.todo = todo;
        this.name = name;
        this.creat_date = creat_date;
        this.edit_date = edit_date;
    }
}