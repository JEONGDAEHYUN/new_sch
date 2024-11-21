package com.example.new_sch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewSchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewSchApplication.class, args);
    }

    // Controller - API
    // Service - 로직
    // Repository - DB
    // Entity - Schedule
    // Dto - Request, Response

    // Lv.1 일정 생성 및 조회
    // 일정 생성
        // 할일, 작성자명, 비밀번호, 작성일, 수정일
        // 작성/수정일 날짜, 시간을 모두 포함
        // ID 자동 생성 관리
        // 최초 입력 시 수정, 작성일 동일
    // 전체 일정 조회
        // 수정일(YYYY-MM-DD), 작성자명 조건으로 전부 조회
        // 한 가지만을 충족 혹은 둘 다 충족하지 않거나 혹은 두 가지를 모두 충족
    // 선택 일정 조회
        // ID를 사용하여 조회
}
