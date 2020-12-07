import java.time.Instant;
import java.util.ArrayList;

import telran.view.InputOutput;
import telran.view.Item;

public class StartRace implements Item {


	@Override
	public String displayName() {
		
		return "Start race";
	}

	@Override
	public void perform(InputOutput io) {
		int nRacers = io.readInteger("Enter number of racers", 3, 10);
		int distance = io.readInteger("Enter distance", 100, 3500);
		Racer [] racers = new Racer[nRacers];
		Race race = new Race(2, 5, distance);
		race.results = new ArrayList<>();
		race.start = Instant.now();
		startRacers(racers, race);
		waitRacers(racers);
		displayResults(race);

	}
	 private void displayResults(Race race) {
		System.out.println("           Results Table");
		System.out.println("Place\tRacer number\tTime");
		int size = race.results.size();
		for (int i = 0; i < size; i++) {
			Racer racer = race.results.get(i);
			System.out.printf("  %d\t\t%d\t%d\n",
					i + 1, racer.getThreadId(), racer.getRunTime());
		}
		
	}

	private void waitRacers(Racer[] racers) {
		for(Racer racer: racers) {
			try {
				racer.join();
			} catch (InterruptedException e) {
				
			}
		}
		
	}

	private void startRacers(Racer[] racers, Race race) {
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(i + 1, race);
			racers[i].start();
		}
		
	}

}
