package com.capgemini.studentinfo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capgemini.studentinfo.entity.Student;
import com.capgemini.studentinfo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	 *
	 * protected <T> T mapFromJson(String json, Class<T> clazz) throws
	 * JsonParseException, JsonMappingException, IOException {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.readValue(json, clazz); }
	 */

	@BeforeEach
	public void setup() {
		student = new Student();
		student.setRollNumber(101);
		student.setFirstName("Abbas");
		student.setLastName("Pathan");
	}

	@Test
	public void addNewStudentTest() throws Exception {

		String inputJson = mapToJson(student);

		MvcResult mvcResult = mvc
				.perform(post("/student/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
	}

	@Test
	public void getStudentInformationTest() throws Exception {

		MvcResult mvcResult = mvc.perform(get("/student/101").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

	}

	@Test
	public void updateStudentInformationTest() throws JsonProcessingException, Exception {
		mvc.perform(put("/student/").content(mapToJson(new Student(101, "abbas", "pathan")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteStudentInformationTest() throws Exception {

		mvc.perform(
				delete("/student/101").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
