package com.example.slorestendpointlogging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndPoints {
	
	@GetMapping("/enable")
	public String enable() {
		return "Enable";
	}
	
	@GetMapping("/disable")
	public String disable() {
		return "Disable";
	}
	
	@GetMapping("/slow")
	public String slow() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "slow";
	}

}
