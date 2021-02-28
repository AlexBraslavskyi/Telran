package telran.logs.bugs.api;

public interface BugsReporterApi {
	String BUGS_PROGRAMMERS =  "/bugs/programmers";
	String BUGS_OPEN = "/bugs/open"; 
	String BUGS_OPEN_ASSIGN = "/bugs/open/assign" ;
	String BUGS_ASSIGN = "/bugs/assign" ;
	String PROGRAMMER_ID = "programmer_id";
	String BUGS_PROGRAMMERS_COUNT = "/bugs/programmers/count";
	String PROGRAMMERS_MOST_BUGS = "/bugs/programmers/most/bugs";
	String N_PROGRAMMERS = "n_programmers";
	String PROGRAMMERS_LEAST_BUGS = "/bugs/programmers/least/bugs";
	String BUGS_ARTIFACT =  "/bugs/artifact";
	String BUGS_CLOSE = "/bugs/close";
	String BUGS_SERIOUSNESS_COUNT = "/bugs/seriousness/count";
	String BUGS_SERIOUSNESS_MOST_COUNT = "/bugs/seriousness/most/count";
	String N_TYPE = "n_type";
}
