package com.kh.mini_project.Dao;

import com.kh.mini_project.Vo.DiaryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.kh.mini_project.Common.DiaryQuery.SELECT_ALL_DIARY;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DiaryDao {
    private final JdbcTemplate jdbcTemplate;

    public static final String SELECT_ALL_DIARY = "SELECT * FROM diary WHERE member_num = ?";

    // 해당 회원이 일기 전체 조회
    public List<DiaryVo> diaryListByMember(int memberNum) {
        try {
            return jdbcTemplate.query((SELECT_ALL_DIARY), new Object[]{memberNum}, new DiaryRowMapper());
        } catch (DataAccessException e) {
            log.error("다이어리 조회 중 에러 발생. ", e);
            throw e;
        }
    }






    // Row Mapper
    private static class DiaryRowMapper implements RowMapper<DiaryVo> {

        @Override
        public DiaryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DiaryVo(
                    rs.getInt("diary_num"),
                    rs.getInt("member_num"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("published_at")
            );
        }
    }
}
