package com.example.new_sch.repository;

import com.example.new_sch.dto.ScheduleResponseDto;
import com.example.new_sch.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<ScheduleResponseDto> findAll(String name, String edit_date);

    Schedule findScheduleByIdOrElseThrow(Long id);

    ScheduleResponseDto saveSchedule(Schedule schedule);

    int deleteSchedule(Long id, String password);

    int updateSchedule(Long id, String todo, String name, String password);
}
