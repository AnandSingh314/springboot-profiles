/**
 * 
 */
package com.practice.spring.profiles;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Anand Singh <email: avsmips@gmail.com>
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { 
		"spring.profiles.active=test",
		"demo.profile.name=testProfile",
		"demo.profile.message=This is test message" })
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	Environment environment;

	@Test
	public void activeProfileTest() throws Exception {

		assertTrue(this.environment.getActiveProfiles()[0].equalsIgnoreCase("test"));
		
	}

	@Test
	public void sampleRestServiceTest() throws Exception {
		this.mockMvc.perform(get("/"))
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"profile\":\"testProfile\",\"message\":\"This is test message\"}"));
	}

}
