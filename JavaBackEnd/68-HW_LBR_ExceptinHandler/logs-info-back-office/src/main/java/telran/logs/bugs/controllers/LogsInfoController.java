package telran.logs.bugs.controllers;

import static telran.logs.bugs.api.LogsInfoApi.AMOUNT;
import static telran.logs.bugs.api.LogsInfoApi.LOGS;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_ARTIFACT_ENCOUNTERED;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_DISTRIBUTION_ARTIFACT;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_DISTRIBUTION_TYPE;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_EXCEPTIONS;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_EXCEPTION_ENCOUNTERED;
import static telran.logs.bugs.api.LogsInfoApi.LOGS_TYPE;
import static telran.logs.bugs.api.LogsInfoApi.TYPE;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class LogsInfoController {
	static Logger LOG = LoggerFactory.getLogger(LogsInfoController.class);
	@Autowired
	LogsInfo logsInfo;
	@GetMapping(value=LOGS,produces="application/stream+json")
Flux<LogDto> getAllLogs() {
		Flux<LogDto> result = logsInfo.getAllLogs();
		LOG.debug("Logs sent to a client");
	return result;
}
	@GetMapping(value=LOGS_TYPE,produces="application/stream+json")
	Flux<LogDto> getLogsByTypes(@RequestParam(name=TYPE) @NotNull LogType logType) {
			Flux<LogDto> result = logsInfo.getLogsType(logType);
			LOG.debug("Logs of type {} sent to a client", logType);
		return result;
	}
	@GetMapping(value = LOGS_EXCEPTIONS)
	Flux<LogDto> getExceptions() {
			Flux<LogDto> result = logsInfo.getAllExceptions();
			LOG.debug("Logs Exceptions sent to a client");
		return result;
	}
	@GetMapping(value = LOGS_DISTRIBUTION_TYPE)
	Flux<LogTypeCount> getLogTypeOccurrences() {
		return logsInfo.getLogTypeOccurrences();
	}
	@GetMapping(value = LOGS_DISTRIBUTION_ARTIFACT)
	Flux<ArtifactCount> getArtifactOccurrences() {
		return logsInfo.getArtifactOccurrences();
	}
	@GetMapping(value = LOGS_ARTIFACT_ENCOUNTERED)
	Mono<List<String>> getMostEncounteredArtifacts( @RequestParam(name = AMOUNT, defaultValue = "2") @Min(1)  int nArtifacts) {
		
		return logsInfo.getMostEncounteredArtifacts(nArtifacts).collectList();
	}
	@GetMapping(value = LOGS_EXCEPTION_ENCOUNTERED)
	Flux<LogType> getMostEncounteredExceptionTypes( @RequestParam(name = AMOUNT, defaultValue = "2") @Min(1)  int nExceptions) {
		
		return logsInfo.getMostEncounteredExceptionTypes(nExceptions);
	}
	
}
