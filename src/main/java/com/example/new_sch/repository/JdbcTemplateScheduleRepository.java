package com.example.new_sch.repository;

import com.example.new_sch.dto.ScheduleResponseDto;
import com.example.new_sch.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String name, String edit_date) {
        // 수정일(YYYY-MM-DD), 작성자명 조건으로 전부 조회
        // 한 가지만을 충족 혹은 둘 다 충족하지 않거나 혹은 두 가지를 모두 충족
        // 수정일 기준 내림차순

        StringBuilder query = new StringBuilder("SELECT * FROM schedule WHERE 1=1"); // 쿼리가 복잡해지면 헷깔릴수 있어 대문자로 적는다.
        List<Object> params = new ArrayList<>();

        if (name != null) {
            query.append(" AND name = ?");
            params.add(name);
        }

        if (edit_date != null) {
            query.append(" AND DATE_FORMAT(edit_date, '%Y-%m-%d') = ?");
            params.add(edit_date);
        }

        query.append(" ORDER BY edit_date DESC;"); //  ORDER BY ~ DESC 수정일 기준 최신 순으로 정렬

        return jdbcTemplate.query(query.toString(), params.toArray(), scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없습니다."));
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", schedule.getTodo());
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());
        parameters.put("create_date", schedule.getCreate_date());
        parameters.put("edit_date", schedule.getEdit_date());

        Number Key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(Key.longValue(), schedule.getTodo(), schedule.getName(), schedule.getCreate_date(), schedule.getEdit_date());
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        return jdbcTemplate.update("DELETE FROM schedule WHERE id = ? AND password = ?", id, password);
    }

    @Override
    public int updateSchedule(Long id, String todo, String name, String password) {
        return jdbcTemplate.update("UPDATE schedule SET todo = ?, name = ?, edit_date = ? WHERE id = ? AND PASSWORD = ?", todo, name, LocalDateTime.now(), id, password);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getObject("create_date", LocalDateTime.class),
                        rs.getObject("edit_date", LocalDateTime.class)
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getObject("create_date", LocalDateTime.class),
                        rs.getObject("edit_date", LocalDateTime.class)
                );
            }
        };
    }
}

