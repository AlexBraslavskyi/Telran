package telran.logs.bugs.api;

public interface LogsInfoApi {
String LOGS = "/logs";
String LOGS_TYPE = "/logs/type";
String TYPE = "type";
String LOGS_EXCEPTIONS = "/logs/exceptions";
String LOGS_DISTRIBUTION_TYPE = "/logs/distribution/type";
String LOGS_DISTRIBUTION_ARTIFACT = "/logs/distribution/artifact";
String AMOUNT = "amount";
String LOGS_ARTIFACT_ENCOUNTERED = "/logs/artifacts/encountered";
String LOGS_EXCEPTION_ENCOUNTERED = "/logs/exceptions/encountered";
String ADD_PROGRAMMER = "/bugs/programmers";
String OPEN_BUG = "/bugs/open";
String OPEN_AND_ASSIGN_BUG = "/bugs/open/assign";
String ASSIGN_BUG = "/bugs/assign";
String GET_BUGS_PROGRAMMER = "/bugs/programmers";
String PROGRAMMER_ID = "/{id}";

}
