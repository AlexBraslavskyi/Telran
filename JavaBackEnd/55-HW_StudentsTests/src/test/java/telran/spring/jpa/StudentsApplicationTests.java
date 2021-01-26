package telran.spring.jpa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.dto.StudentMarksCount;
import telran.spring.jpa.service.interfaces.Students;

@SpringBootTest
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase
class StudentsApplicationTests {
	@Autowired
	Students students;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;
	void assertGet(String path) throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
						MockMvcRequestBuilders.get(path)).andReturn().getResponse();
		assertEquals(200, response.getStatus());
	}
	void assertPut(String path) throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
						MockMvcRequestBuilders.put(path)).andReturn().getResponse();
		assertEquals(200, response.getStatus());
	}
	void assertDel(String path) throws Exception {
		MockHttpServletResponse response = mockMvc.perform(
						MockMvcRequestBuilders.delete(path)).andReturn().getResponse();
		assertEquals(200, response.getStatus());
	}

	private void withoutMoshe() {
		List<String> expected = students.bestStudents(2);
		assertEquals(1, expected.size());
		assertEquals("Sara", expected.get(0));
	}
	@DisplayName("GET tests")
	@Nested
	class GetTests {
		@Test
		@Sql("fillTables.sql")
		void bestStudents() throws Exception {
			assertGet("/students/best?n_students=5&subject=Java");
			List<String> expected = students.bestStudents();
			assertEquals(1, expected.size());
			assertEquals("Moshe", expected.get(0));
			expected = students.bestStudents(2);
			assertEquals(2, expected.size());
			assertEquals("Moshe", expected.get(0));
			assertEquals("Sara", expected.get(1));
		}

		@Test
		@Sql("fillTables.sql")
		void testFindStudentsMarksCount() throws Exception {
			assertGet("/students/marks/count");
			List<StudentMarksCount> expected = students.getStudentsMarksCount();
			assertEquals(2, expected.size());
			assertEquals("Moshe", expected.get(0).getName());
			assertEquals(4, expected.get(0).getMarksCount());
		}

		@Test
		@Sql("fillTables.sql")
		void testFindTopSubjectsHighestMarks() throws Exception {
			assertGet("/subjects/marks/highest");
			List<String> expected = students.getTopSubjectsHighestMarks(2);
			assertEquals(2, expected.size());
			assertEquals("Java", expected.get(0));
		}

		@Test
		@Sql("fillTables.sql")
		void testMarksDistributionInterval() throws Exception {
			assertGet("/marks/distribution");
			List<IntervalMarks> expected = students.getIntervalsMarks(10);
			assertEquals(3, expected.size());
			assertEquals(100,expected.get(2).getMin());
			assertEquals(109,expected.get(2).getMax(), 109);
			assertEquals(3,expected.get(2).getOccurrences());
			

		}

		@Test
		@Sql("fillTablesMarksEncountered.sql") 
		void marksEncountered() throws Exception {
			assertGet("/marks/widespread/Java?n_marks=2");
			Integer []expected = students.getTopMarksEncountered(2, "Java").toArray(new Integer[0]);
			Integer [] marksExpected = {100, 70};
			
			assertArrayEquals(expected, marksExpected);
		}
	}

	@DisplayName("Delete tests")
	@Nested
	class DeleteTests {

		@Test
		@Sql("fillTables.sql")
		void deleteMarks() throws Exception {
			assertDel("/marks/Java?name=Moshe");
			assertDel("/marks/React?name=Moshe");
			withoutMoshe();

		}
		@Test
		@Sql("fillTables.sql")
		void deleteSubject() throws Exception {
			assertDel("/subject/del/Java");
			List<Integer> expected = students.getTopMarksEncountered(2, "Java");
			assertEquals(0, expected.size());

		}

		@Test
		@Sql("fillTables.sql")
		void deleteStudent() throws Exception {
			assertDel("/student/del/Moshe");
			withoutMoshe();
		}

	}
	
		@DisplayName("Put tests")
		@Nested
		class PutTests {
		@Test
		@Sql("fillTablesAveragingMarks.sql")
		void averagingMarks() throws  Exception {
			assertPut("/subjects/marks/averaging");
			Integer  [] expected = students.getTopMarksEncountered(2, "Java").toArray(new Integer[0]);
			Integer [] marksExpected = {75};
			assertArrayEquals(expected, marksExpected);
			expected = students.getTopMarksEncountered(2, "React").toArray(new Integer[0]);
			marksExpected[0] = 80;
			assertArrayEquals(expected, marksExpected);
			
		}
	}	
}
	
