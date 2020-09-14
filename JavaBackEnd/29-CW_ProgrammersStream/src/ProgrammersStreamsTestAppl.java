import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class ProgrammersStreamsTestAppl {

	
	private static final String JAVA = "Java";
	private static final String CPP = "C++";
	private static final String WEB = "Web";
	private static final String SQL = "SQL";
	private static final String JS = "JS";
	private static final String MONGO = "Mongo";

	public static void main(String[] args) {

List <Programmer> programmers = Arrays.asList(new Programmer(123, "Moshe", 10000, new String[] {
		JAVA, CPP, WEB}), 
		new Programmer(124, "Sara", 15000, new String[] {
		SQL, JS, WEB}), 
		new Programmer(125, "Yosef", 12000, new String[] {
		 CPP}), 
		new Programmer(126, "Revecca", 11000, new String[] {
		 CPP, MONGO}),
		new Programmer(127, "Yakob", 9000, new String[] {
				SQL, CPP
		}));
displayAvgMaxMinSalary(programmers);
System.out.println();
displayMostPopularTech(programmers);
System.out.println();
displayMostSkilledProgrammer(programmers);

}

	private static void displayMostSkilledProgrammer(List<Programmer> programmers) {
		
		 System.out.print("Most skilled programmers technologis");
		 
		 Map<String, Integer> mapProgAmountTech = programmers.stream()
				 .collect(Collectors.toMap(Programmer::getName, p->p.getTechnologies().length));
		 Integer maxTech = Collections.max(mapProgAmountTech.values());
		 mapProgAmountTech.forEach((k,v)->{
			 if(v == maxTech) {
				 System.out.println(k+"");
			 }
		 });
	}

	private static void displayMostPopularTech(List<Programmer> programmers) {
	Map<String,Long> mapTechCount = programmers.stream().flatMap(p-> Arrays.stream(p.getTechnologies()))	
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
 Long maxCount = Collections.max(mapTechCount.values());
 mapTechCount.forEach((k,v) -> {
	 if(v == maxCount) {
		 System.out.print("Most popular technologis " + k + "");
	 }
	});
	}
	
	private static void displayAvgMaxMinSalary(List<Programmer> programmers) {
		IntSummaryStatistics stats = programmers.stream().mapToInt(Programmer::getSalary).summaryStatistics();
		System.out.printf("avg salary = %.2f; min Salary = %d; max salary = %d", stats.getAverage(),stats.getMin(),stats.getMax());
	}
	
	

}
