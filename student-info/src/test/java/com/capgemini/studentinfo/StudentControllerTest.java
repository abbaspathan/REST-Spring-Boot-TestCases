package com.capgemini.studentinfo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.studentinfo.entity.Student;
import com.capgemini.studentinfo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
class StudentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StudentService service;

	private Student student;

	/*
	 * JSON Object to String conversion
	 */
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	/*
	 * String to JSON Object Conversion
	 */
	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@BeforeEach
	public void setup() {
		student = new Student();
		student.setRollNumber(101);
		student.setFirstName("Abbas");
		student.setLastName("Pathan");
	}

	@Test
	public void studentAddNewRecoredTest() throws Exception {

		String inputJson = mapToJson(student);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/student/")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void studentInformationGetTest() throws Exception {

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/student/101").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

}
