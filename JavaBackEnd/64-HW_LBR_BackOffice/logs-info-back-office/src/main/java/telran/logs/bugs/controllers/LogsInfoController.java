package telran.logs.bugs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.interfaces.LogsInfo;

@RestController
public class LogsInfoController {
	static Logger LOG = LoggerFactory.getLogger(LogsInfoController.class);
	@Autowired
	LogsInfo logsInfo;
	
	@GetMapping(value="/logs", produces = "application/stream+json")
Flux<LogDto> getAllLogs(){
		Flux <LogDto> result = logsInfo.getAllLogs();
		LOG.debug("Logs sent to a client");
	return result;
}
	
//	http://localhost:8080/logs/type?type=AUTHENTICATION_EXCEPTION
	@GetMapping(value="/logs/type", produces = "application/stream+json")
	Flux<LogDto> getLogsByTypes(@RequestParam(name="type")LogType type){
			Flux <LogDto> result = logsInfo.getLogsType(type);
			LOG.debug("Logs of type {} sent to a client", type);
		return result;
	}
	
//	http://localhost:8080/logs/excep
	@GetMapping(value="/logs/excep", produces = "application/stream+json")
	Flux<LogDto> getAllException(){
			Flux <LogDto> result = logsInfo.getAllExceptions();
			LOG.debug("Logs this exceptions  sent to a client");
		return result;
	}
}
