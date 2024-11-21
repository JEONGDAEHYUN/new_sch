package com.example.new_sch.controller;

import com.example.new_sch.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("schedules") // schedules 주소 정의
public class ScheduleController {

    @GetMapping // schedules 주소 정의 했기 때문에 또 적지 않는다.
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        scheduleService.findAll();
    }// ResponseEntity 라는걸로 리턴, Entity 를 Response 한다. 이런식으로 기억하자, 전체 조회는 여러게니까 List, <ScheduleResponseDto> 리턴

}
