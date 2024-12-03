package com.kh.mini_project.Controller;

import com.kh.mini_project.Dao.DiaryDao;
import com.kh.mini_project.Vo.DiaryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Slf4j
public class HomeController {
    private final DiaryDao diaryDao;

    // 해당 회원이 일기 전체 조회
    @GetMapping("/{memberNum}")
    public ResponseEntity<List<DiaryVo>> diaryListByMember(@PathVariable String memberNum) {
        List<DiaryVo> list = diaryDao.diaryListByMember(memberNum);
        return ResponseEntity.ok(list);
    }
}
