-- 앞서 만든 ERD 기반 테이블 생성
CREATE TABLE schedule(
    id INT NOT NUll AUTO_INCREMENT, -- id 컬럼은 PRIMARY KEY 이므로 자동으로 NOT NULL 적용, 새로운 행이 추가될때 자동으로 숫자 증가
    todo VARCHAR(100) NULL, -- 해당 colum 에 값이 없으면 NULL 값으로 저장한다.
    name VARCHAR(100) NULL,
    password VARCHAR(100) NULL,
    create_date DATETIME NULL,
    edit_date DATETIME NULL,
    PRIMARY KEY (id) -- 테이블 내에서 각각의 행을 고유하게 식별하는 id
    );

-- 일정 생성 (SQL 명령어 + 테이블 명 + (컬럼 명))
INSERT INTO schedule (todo, name, password, create_date, edit_date)
-- VALUES 는 데이터베이스 테이블에 새로운 행을 삽입할때 사용하는 키워드 이다, () 안의 값은 파라미터(매개변수)
VALUES ('강의 듣기', 'jdh', '1', '2024-11-04', NOW());

-- 전체 일정 조회 (*: 모든 열을 선택한다.)
SELECT * FROM schedule s -- schedule을 s 로 생략한다.
WHERE s.edit_date = '2024-11-04'
OR s.name = 'jdh'
ORDER BY s.edit_date DESC; -- 수정일 기준 내림차순으로 조회

-- 선택 일정 조회
SELECT * FROM schedule s
WHERE s.id = 1; -- Lv 1. 설명에서 단건정보 조회, ID 활용하여 조회 로 작성 요구

-- 선택 일정 수정
UPDATE schedule s
SET s.todo = '과제', s.name = 'jump', s.edit_date = NOW() -- UPDATE 문에서 SET 을 사용하면 기존 값이 새로운 값으로 대체된다.
WHERE s.id = 1;

-- 선택 일정 삭제
DELETE FROM schedule s
WHERE s.id = 1;
