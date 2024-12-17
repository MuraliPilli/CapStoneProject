package com.capstone.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.util.SuccessResponse;

@RestControllerAdvice
public class CapstoneExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlICVE(SQLIntegrityConstraintViolationException e)
	{
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.msg("you can't perform this operation")
				.data(e.getMessage())
				.dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<SuccessResponse> NotFound(NotFoundException e)
	{
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.msg("you can't perform this operation")
				.data(e.getMsg())
				.dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.NOT_FOUND);
	}

}
