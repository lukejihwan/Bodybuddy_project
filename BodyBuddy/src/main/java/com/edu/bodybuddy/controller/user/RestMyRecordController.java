package com.edu.bodybuddy.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/myrecord")
public class RestMyRecordController {

	
	@GetMapping("/geo")
	public List getLocation(){
		
		
		
		return null;
	}

}
