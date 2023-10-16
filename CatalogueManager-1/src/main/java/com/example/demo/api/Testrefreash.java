package com.example.demo.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Testrefreash {

	@Value("${rma.user}")
	String param1;
	
	@GetMapping("value")
	public String getvalue()
	{
		return param1;
	}
}
