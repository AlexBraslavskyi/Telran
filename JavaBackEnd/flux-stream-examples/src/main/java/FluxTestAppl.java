import javax.management.RuntimeErrorException;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxTestAppl {

	public static void main(String[] args) throws InterruptedException {
//	Flux<Integer> fluxNumbers = Flux.just(1,2,3,4); 
//		Flux<Integer> fluxNumbers = Flux.range(1,4); 
//		
//		Flux<String> fluxNumbers = Flux.range(1,4).map(n -> Integer.toString(n));
//		Disposable dis = fluxNumbers.subscribe(n -> System.out.println(n));
//		System.out.println("after flux number");
		
		
		
//		
//		Flux<Long> fluxInterval = Flux.interval(Duration.ofSeconds(1));
//		fluxInterval.subscribe(System.out::println);	
//		System.out.println("after flux number");
//	Thread.sleep(10000);
		
		
//		
		Scheduler scheduler = Schedulers.newParallel("parallel");
		Flux<Integer> fluxNumbers = Flux.range(1,4);
		Flux<String>publishedNumbers =  fluxNumbers.publishOn(scheduler).map(n -> Thread.currentThread().getName() + " " + n);

	
				publishedNumbers.subscribe(n->{System.out.println(n);
					if(n.contains("4")) {
						throw new RuntimeErrorException(null);
					}
					},System.out::println,scheduler::dispose);
		
			
	
		System.out.println("after flux number");
	
	
	}
}
