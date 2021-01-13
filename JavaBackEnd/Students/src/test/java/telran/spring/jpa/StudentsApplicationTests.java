package telran.spring.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telran.spring.jpa.dto.IntervalMarks;
import telran.spring.jpa.service.interfaces.Students;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class StudentsApplicationTests {
	@Autowired
	Students students;
	@Autowired
	MockMvc mockMvc;

	@DisplayName("GET tests")
	@Nested
	class GetTests {
		@Test
		@Sql("fillTables.sql")
		void bestStudents() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.get("/students/best?n_students=5&subject=Java"))
					.andReturn().getResponse().getStatus();
			assertEquals(200, status);
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
		void testFindSubjectsHighestMarks() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.get("/subjects/marks/highest")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			List<String> expected = students.getSubjectsHighestMarks();
			assertNotEquals(0, expected.size());
			assertEquals("Java", expected.get(0));
		}

		@Test
		@Sql("fillTables.sql")
		void testFindTopSubjectsHighestMarks() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.get("/marks/widespread/Java?n_marks=2")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			List<Integer> expected = students.getTopMarksEncountered(2, "Java");
			assertEquals(2, expected.size());
			assertEquals(100, expected.get(0));
		}

		@Test
		@Sql("fillTables.sql")
		void testMarksDistributionInterval() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.get("/marks/distribution")).andReturn().getResponse()
					.getStatus();
			assertEquals(200, status);
			List<IntervalMarks> expected = students.getIntervalsMarks(10);
			assertEquals(3, expected.size());

		}

		@Test
		@Sql("fillTables.sql")
		void testMostEncounteredMarks() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.get("/subjects/marks/highest?n_subjects=2")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			List<String> expected = students.getSubjectsHighestMarks();
			assertNotEquals(2, expected.size());
			assertEquals("Java", expected.get(0));
		}
	}

	@DisplayName("Delete tests")
	@Nested
	class DeleteTests {

		@Test
		@Sql("fillTables.sql")
		void deleteMarks() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.delete("/marks/Java?name=Moshe")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			status = mockMvc.perform(MockMvcRequestBuilders.delete("/marks/React?name=Moshe")).andReturn().getResponse()
					.getStatus();
			assertEquals(200, status);

			withoutMoshe();

		}

		@Test
		@Sql("fillTables.sql")
		void deleteDuplicates() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.put("/subjects/marks/averaging")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			List<String> expected = students.bestStudentsSubject("Java");
			assertEquals(1, expected.size());
			assertEquals("Sara", expected.get(0));

		}

		@Test
		@Sql("fillTables.sql")
		void deleteSubject() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.delete("/subject?subject=Java")).andReturn()
					.getResponse().getStatus();
			assertEquals(200, status);
			List<Integer> expected = students.getTopMarksEncountered(2, "Java");
			assertEquals(0, expected.size());

		}

		@Test
		@Sql("fillTables.sql")
		void deleteStudent() throws Exception {
			int status = mockMvc.perform(MockMvcRequestBuilders.delete("/student?name=Moshe")).andReturn().getResponse()
					.getStatus();
			assertEquals(200, status);
			withoutMoshe();
		}
	}

	private void withoutMoshe() {
		List<String> expected = students.bestStudents(2);
		assertEquals(1, expected.size());
		assertEquals("Sara", expected.get(0));
	}

}
