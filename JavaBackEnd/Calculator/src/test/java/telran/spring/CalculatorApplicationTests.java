package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.valueOf;
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
		// two correct parameters
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM, 20)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first parameter
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_ADD, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct second parameter name ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());

	}

	@Test
	void subNormal() throws Exception {
		// two correct parameters
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_SUB, FIRST_PARAM, 60, SECOND_PARAM, 20)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first parameter
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_SUB, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct second parameter name ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_SUB, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());

	}

	@Test
	void divNormal() throws Exception {
		// two correct parameters
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 80, SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first parameter
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_DIV, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct second parameter name ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());

	}

	@Test
	void multNormal() throws Exception {
		// two correct parameters
		String expected = "40";
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_MULT, FIRST_PARAM, 20, SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response.getStatus()));
		assertEquals(expected, response.getContentAsString());
		// only first parameter
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_MULT, FIRST_PARAM, 40)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response1.getStatus()));
		assertEquals(expected, response1.getContentAsString());
		// not correct second parameter name ignored and used default value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_MULT, FIRST_PARAM, 40, "abc", 5)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response2.getStatus()));
		assertEquals(expected, response2.getContentAsString());

	}
	

	// ******************** Error Tests **********************
	@Test
	void commonErrors() throws Exception {
		String expected = "40";
		// not correct parameter operation 
		assertThrows(IllegalArgumentException.class, () -> {
			mock.perform(MockMvcRequestBuilders
					.get(String.format("%s?%s=%d&%s=%d", "ADD", FIRST_PARAM, 20, SECOND_PARAM, 20))).andReturn()
					.getResponse();});
		//not correct operation parameter 
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", "/abc", FIRST_PARAM, 40, SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(NOT_FOUND, valueOf(response.getStatus()));
		// 3rd parameter ignored
		MockHttpServletResponse response2 = mock.perform(MockMvcRequestBuilders.get(String
				.format("%s?%s=%d&%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM, 10, FIRST_PARAM, 10)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response2.getStatus()));
		assertNotEquals(expected, response2.getContentAsString());

		// error int max value
		MockHttpServletResponse response3 = mock.perform(MockMvcRequestBuilders
				.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_ADD, FIRST_PARAM, 1, SECOND_PARAM, Integer.MAX_VALUE)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response3.getStatus()));
		assertEquals(""+(Integer.MAX_VALUE + 1), response3.getContentAsString());
		// error int min value
		MockHttpServletResponse response4 = mock.perform(MockMvcRequestBuilders
				.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_SUB, FIRST_PARAM, Integer.MIN_VALUE, SECOND_PARAM, 1)))
				.andReturn().getResponse();
		assertEquals(OK, valueOf(response3.getStatus()));
		assertEquals(""+(Integer.MIN_VALUE-1), response4.getContentAsString());
	}

	@Test
	void addError() throws Exception {
		// not correct name of first parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_ADD, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(BAD_REQUEST, valueOf(response.getStatus()));
		// not correct second parameter value
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%s", CALCULATOR_ADD, FIRST_PARAM, 40, SECOND_PARAM, "abc")))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response1.getStatus()));
		// not correct first parameter value
		MockHttpServletResponse response3 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%s&%s=%d", CALCULATOR_ADD, FIRST_PARAM, "abc", SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response3.getStatus()));
		// only second parameter
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_ADD, SECOND_PARAM, 10)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response2.getStatus()));

	}

	@Test
	void subError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_SUB, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(BAD_REQUEST, valueOf(response.getStatus()));
		// not correct second parameter value
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%s", CALCULATOR_SUB, FIRST_PARAM, 40, SECOND_PARAM, "abc")))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response1.getStatus()));
		// not correct first parameter value
		MockHttpServletResponse response3 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%s&%s=%d", CALCULATOR_SUB, FIRST_PARAM, "abc", SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response3.getStatus()));
		// only second parameter
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_SUB, SECOND_PARAM, 10)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response2.getStatus()));
	}

	@Test
	void multError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_MULT, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(BAD_REQUEST, valueOf(response.getStatus()));
		// not correct second parameter value
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%s", CALCULATOR_MULT, FIRST_PARAM, 40, SECOND_PARAM, "abc")))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response1.getStatus()));
		// only second parameter
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_MULT, SECOND_PARAM, 10)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response2.getStatus()));
		// not correct first parameter value
		MockHttpServletResponse response3 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%s&%s=%d", CALCULATOR_MULT, FIRST_PARAM, "abc", SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response3.getStatus()));

	}

	@Test
	void divError() throws Exception {
		// not correct name of parameter
		MockHttpServletResponse response = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_DIV, "abc", 10))).andReturn()
				.getResponse();
		assertEquals(BAD_REQUEST, valueOf(response.getStatus()));
		// Dividing on zero
		MockHttpServletResponse response1 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%d", CALCULATOR_DIV, FIRST_PARAM, 10, SECOND_PARAM, 0)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response1.getStatus()));
		// not correct second parameter value
		MockHttpServletResponse response2 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%d&%s=%s", CALCULATOR_DIV, FIRST_PARAM, 40, SECOND_PARAM, "abc")))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response2.getStatus()));
		// not correct first parameter value
		MockHttpServletResponse response4 = mock
				.perform(MockMvcRequestBuilders
						.get(String.format("%s?%s=%s&%s=%d", CALCULATOR_DIV, FIRST_PARAM, "abc", SECOND_PARAM, 2)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response4.getStatus()));
		// only second parameter
		MockHttpServletResponse response3 = mock
				.perform(MockMvcRequestBuilders.get(String.format("%s?%s=%d", CALCULATOR_DIV, SECOND_PARAM, 10)))
				.andReturn().getResponse();
		assertEquals(BAD_REQUEST, valueOf(response3.getStatus()));

	}
}
