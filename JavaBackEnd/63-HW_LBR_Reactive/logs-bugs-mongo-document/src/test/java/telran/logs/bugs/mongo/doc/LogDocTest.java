package telran.logs.bugs.mongo.doc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LogsRepo.class)
@EnableAutoConfiguration
@AutoConfigureDataMongo
public class LogDocTest {
	@Autowired
	LogsRepo logs;

	@Test
	void docStoreTest() throws InterruptedException {
		List<LogType> logTypes = Arrays.stream(LogType.values()).collect(Collectors.toList());
		List<LogDoc> list = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			list.add(new LogDoc(new LogDto(new Date(), logTypes.get(i), "artifact" + i, i, "result" + i)));
		}
		logs.saveAll(list).subscribe();
		Thread.sleep(10000);
		Long size=logs.count().block();
			assertEquals(list.size(), size);
				
		List<LogDoc>logsList = logs.findAll().collectList().block();

			for (int i = 0; i < 7; i++) {
				assertEquals(list.get(i).getLogDto(), logsList.get(i).getLogDto());
			}
		
	}

}
