package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static telran.spring.api.ApiConstants.ADD;
import static telran.spring.api.ApiConstants.CALCULATOR;
import static telran.spring.api.ApiConstants.DIV;
import static telran.spring.api.ApiConstants.MULT;
import static telran.spring.api.ApiConstants.PER;
import static telran.spring.api.ApiConstants.POW;
import static telran.spring.api.ApiConstants.SUB;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.api.dto.CalculateData;
import telran.spring.services.interfaces.Calculator;



@SpringBootTest
@AutoConfigureMockMvc
class FlexibleCalculatorApplicationTests {
	private static final String FIRST_PARAM = "20";
	private static final String SECOND_PARAM = "5";
	
	@Autowired
	List<Calculator> calculators;
	@Autowired
	MockMvc mock;
	
	@Test
	void contextLoads() {
		assertNotNull(calculators);
	}
	public void assertHttpStatusAndValue(String expected, String operation, String first_param, String second_param, HttpStatus httpStatus) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CalculateData calculateData = new CalculateData(Integer.parseInt(first_param), Integer.parseInt(second_param), operation);
		String json = mapper.writeValueAsString(calculateData);
		MockHttpServletResponse response = mock.perform(post(CALCULATOR)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andReturn().getResponse();
		HttpStatus status = HttpStatus.valueOf(response.getStatus());
		assertEquals(httpStatus, status);
		assertEquals(expected, response.getContentAsString());
	}
	
	@DisplayName("Normal result tests")
	@Nested
	class RightTests {
		@Test
		void sum() throws Exception {
			String expected = "25";
			assertHttpStatusAndValue(expected, ADD, FIRST_PARAM, SECOND_PARAM, HttpStatus.OK);
		}
		@Test
		void sub() throws Exception {
			String expected = "15";
			assertHttpStatusAndValue(expected, SUB, FIRST_PARAM, SECOND_PARAM, HttpStatus.OK);
		}
		@Test
		void div() throws Exception {
			String expected = "4";
			assertHttpStatusAndValue(expected, DIV, FIRST_PARAM, SECOND_PARAM, HttpStatus.OK);
		}
		@Test
		void mult() throws Exception {
			String expected = "100";
			assertHttpStatusAndValue(expected, MULT, FIRST_PARAM, SECOND_PARAM, HttpStatus.OK);
		}
		@Test
		void pow() throws Exception {
			String expected = "3200000";
			assertHttpStatusAndValue(expected, POW, FIRST_PARAM, SECOND_PARAM, HttpStatus.OK);
		}
		@Test
		void per() throws Exception {
			String expected = "10";
			assertHttpStatusAndValue(expected, PER, "1", "10", HttpStatus.OK);
		}
		
	}
	
	@DisplayName("Error result tests")
	@Nested
	class ErrorTests {
		@Test
		void divByZero() throws Exception{
			String expected = "/ by zero";
			assertHttpStatusAndValue(expected, DIV, FIRST_PARAM, "0", HttpStatus.BAD_REQUEST);
		}
		@Test
		void wrongFirstParam() throws Exception {
			String expected = "";
			assertThrows(NumberFormatException.class, () -> {
			assertHttpStatusAndValue(expected, ADD, "abc", SECOND_PARAM, HttpStatus.BAD_REQUEST);});
		}
		@Test
		void wrongSecondParam() throws Exception {
			String expected = "";
			assertThrows(NumberFormatException.class, () -> {
			assertHttpStatusAndValue(expected, DIV, FIRST_PARAM, "abc", HttpStatus.BAD_REQUEST);});
		}
	}
	
}
