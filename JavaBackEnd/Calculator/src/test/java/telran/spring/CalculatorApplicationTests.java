package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static telran.spring.api.ApiConstants.CALCULATOR_ADD;
import static telran.spring.api.ApiConstants.CALCULATOR_DIV;
import static telran.spring.api.ApiConstants.CALCULATOR_MULT;
import static telran.spring.api.ApiConstants.CALCULATOR_SUB;
import static telran.spring.api.ApiConstants.FIRST_PARAM;
import static telran.spring.api.ApiConstants.SECOND_PARAM;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telran.spring.controllers.CalculatorController;
import telran.spring.service.interfaces.Calculator;

@SpringBootTest
@AutoConfigureMockMvc

class CalculatorApplicationTests {

	@Autowired
	CalculatorController controller;
	@Autowired
	Calculator calculator;
	@Autowired
	MockMvc mock;

	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(calculator);
	}

	@Test
	void addNormal() throws Exception {
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM, 20)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first param
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_ADD, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct name of second parameter ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());
	}

	@Test
	void subNormal() throws Exception {
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_SUB, FIRST_PARAM, 60, SECOND_PARAM, 20)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first param
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_SUB, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct name of second parameter ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_SUB, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());
	}

	@Test
	void divNormal() throws Exception {
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 80, SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first param
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_DIV, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct name of second parameter ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());
	}

	@Test
	void multNormal() throws Exception {
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_MULT, FIRST_PARAM, 20, SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first param
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_MULT, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct name of second parameter ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_MULT, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());
	}

	// ******************** Error Tests **********************
	@Test
	void addError() throws Exception {
		String expected = "40";
		// not correct name of first parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_ADD, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));

	}

	@Test
	void subError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_SUB, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));

	}

	@Test
	void multError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_MULT, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
	}

	@Test
	void divError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_DIV, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
		// Dividing on zero
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 10, SECOND_PARAM, 0)))
				.andReturn().getResponse();
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response1.getStatus()));

	}
}
