package com.fleets.seguros.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String msg;
	private Date date;

}
