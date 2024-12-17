package com.capstone.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder//BuildDesignPattern it is used to reintailze the data members without creating the object or bean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
	
	private int status;
	private String msg;
	private LocalDateTime dateTime;
	private Object data;
	

}
