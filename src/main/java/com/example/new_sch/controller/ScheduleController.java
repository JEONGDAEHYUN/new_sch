package com.example.new_sch.controller;

import com.example.new_sch.dto.ScheduleRequestDto;
import com.example.new_sch.dto.ScheduleResponseDto;
import com.example.new_sch.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedules") // schedules 주소 정의
public class ScheduleController {

    private final ScheduleService scheduleService;

    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping // schedules 주소 정의 했기 때문에 또 적지 않는다.
    public ResponseEntity<List<ScheduleResponseDto>> findAll(@RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "edit_date", required = false) String edit_date) {
        return new ResponseEntity<>(scheduleService.findAll(name, edit_date), HttpStatus.OK);
    } // ResponseEntity 라는걸로 리턴, Entity 를 Response 한다. 이런식으로 기억하자, 전체 조회는 여러게니까 List, <ScheduleResponseDto> 리턴

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody String password) {
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto.getTodo(), scheduleRequestDto.getName(), scheduleRequestDto.getPassword()), HttpStatus.OK);
    }
}
