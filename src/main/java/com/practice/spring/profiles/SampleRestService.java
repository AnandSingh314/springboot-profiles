/**
 * 
 */
package com.practice.spring.profiles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

/**
 * @author Anand Singh <email: avsmips@gmail.com>
 *
 */
@RestController
public class SampleRestService {

	@Value("${demo.profile.message}")
	private String message;
	@Value("${demo.profile.name}")
	private String profile;

	@GetMapping("/")
	@ResponseBody
	public String getMessage() {
		Map<String, String> info = new HashMap<>();
		info.put("message", this.message);
		info.put("profile", this.profile);

		return new GsonBuilder().create().toJson(info);
	}

}
