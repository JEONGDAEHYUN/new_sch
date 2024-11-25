package com.example.new_sch.service;

import com.example.new_sch.dto.ScheduleRequestDto;
import com.example.new_sch.dto.ScheduleResponseDto;
import com.example.new_sch.entity.Schedule;
import com.example.new_sch.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServicelmpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServicelmpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleResponseDto> findAll(String name, String edit_date) {
        return scheduleRepository.findAll(name, edit_date);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getTodo(), scheduleRequestDto.getName(), scheduleRequestDto.getPassword());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        int deletedRow = scheduleRepository.deleteSchedule(id, password);
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다.");
        }
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String todo, String name, String password) {
        if (todo == null || name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "할일과 작업자명은 필수입니다.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, todo, name, password);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다.");
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }
}
