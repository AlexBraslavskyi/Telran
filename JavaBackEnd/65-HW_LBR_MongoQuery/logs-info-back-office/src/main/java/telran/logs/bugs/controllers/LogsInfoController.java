package telran.logs.bugs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import telran.logs.bugs.dto.ArtifactCount;
import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.dto.LogTypeCount;
import telran.logs.bugs.interfaces.LogsInfo;

@RestController
public class LogsInfoController {
	static Logger LOG = LoggerFactory.getLogger(LogsInfoController.class);
	@Autowired
	LogsInfo logsInfo;
	@GetMapping(value="/logs",produces="application/stream+json")
Flux<LogDto> getAllLogs() {
		Flux<LogDto> result = logsInfo.getAllLogs();
		LOG.debug("Logs sent to a client");
	return result;
}
	@GetMapping(value="/logs/type",produces="application/stream+json")
	Flux<LogDto> getLogsByTypes(@RequestParam(name="type") LogType logType) {
			Flux<LogDto> result = logsInfo.getLogsType(logType);
			LOG.debug("Logs of type {} sent to a client", logType);
		return result;
	}
	@GetMapping(value="/logs/exceptions")
	Flux<LogDto> getExceptions() {
			Flux<LogDto> result = logsInfo.getAllExceptions();
			LOG.debug("Logs Exceptions sent to a client");
		return result;
	}
	@GetMapping(value="/logs/distribution/type")
	Flux<LogTypeCount> getLogTypeOccurrences() {
		return logsInfo.getLogTypeOccurrences();
	}
	@GetMapping(value="/logs/types/encountered")
	Flux<LogType> getMostEncountedEcxeptionTypes(@RequestParam int amount) {
		return logsInfo.getMostEncounteredExceptionTypes(amount);
	}
	@GetMapping(value="/logs/distribution/artifact")
	Flux<ArtifactCount> getLogArtifactOccurrences() {
		return logsInfo.getArtifactOccurrences();
	}
	@GetMapping(value="/logs/artifacts/encountered")
	Mono<List<String>> getMostEncountedArtifact(@RequestParam int amount) {
		return logsInfo.getMostEncounteredArtifacts(amount);
	}
}
