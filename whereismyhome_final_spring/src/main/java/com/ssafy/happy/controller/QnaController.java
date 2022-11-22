package com.ssafy.happy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.model.dto.BoardParameterDto;
import com.ssafy.happy.model.dto.Qna;
import com.ssafy.happy.service.QnaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	private QnaService qnaService;
	
	public QnaController(QnaService qnaService) {
		this.qnaService = qnaService;
	}

	@ApiOperation(value = "Qna 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Qna>> listQna(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<Qna>>(qnaService.listQna(boardParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Qna 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<?> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) Qna qna) throws Exception {
		logger.info("writeArticle - 호출");
		Map<String, Object> map = new HashMap<>();
		
		if (qnaService.writeQna(qna) == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Qna.class)
	@GetMapping("/{qnano}")
	public ResponseEntity<Qna> getArticle(@PathVariable("qnano") @ApiParam(value = "얻어올 글의 글번호.", required = true) int qnano) throws Exception {
		logger.info("getArticle - 호출 : " + qnano);
		qnaService.updateHit(qnano);
		return new ResponseEntity<Qna>(qnaService.getQna(qnano), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<?> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) Qna qna) throws Exception {
		logger.info("modifyArticle - 호출 {}", qna);
		Map<String, Object> map = new HashMap<>();
		
		if (qnaService.modifyQna(qna) == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnano}")
	public ResponseEntity<?> deleteArticle(@PathVariable("qnano") @ApiParam(value = "살제할 글의 글번호.", required = true) int qnano) throws Exception {
		logger.info("deleteArticle - 호출");
		Map<String, Object> map = new HashMap<>();
		
		if (qnaService.deleteQna(qnano) == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
	}
	
	
}
