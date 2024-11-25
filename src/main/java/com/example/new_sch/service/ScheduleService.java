package com.example.new_sch.service;

import com.example.new_sch.dto.ScheduleRequestDto;
import com.example.new_sch.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> findAll(String name, String edit_date);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long id, String password);

    ScheduleResponseDto updateSchedule(Long id, String todo, String name, String password);
}
