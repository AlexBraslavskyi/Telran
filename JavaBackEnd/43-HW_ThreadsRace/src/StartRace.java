import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import telran.view.InputOutput;
import telran.view.Item;

public class StartRace implements Item {
	Instant startTime;

	@Override
	public String displayName() {

		return "Start race";
	}

	@Override
	public void perform(InputOutput io) {
		Race.getWinners().clear();
		Racer[] racers = null;
		int nRacers = io.readInteger("Enter number of racers", 3, 100);
		int distance = io.readInteger("Enter distance", 100, 3500);
		racers = new Racer[nRacers];
		Race race = new Race(distance, 2, 5);

		startRacers(racers, race);
		waitRacers(racers);
		List<Racer> res = Race.getWinners();
		System.out.printf("\nCongratulations to #%s - winner \n", res.get(0).getName());

		for (int i = 0; i < res.size(); i++) {
			System.out.printf("%s finished in %s place with time %s \n", res.get(i).getName(), i + 1,
					ChronoUnit.MILLIS.between(startTime, res.get(i).getFinishTime()) + " Msec");
		}

	}

	private void waitRacers(Racer[] racers) {
		for (Racer racer : racers) {
			try {
				racer.join();
			} catch (InterruptedException e) {

			}
		}

	}

	private void startRacers(Racer[] racers, Race race) {
		startTime = Instant.now();
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(i + 1, race);
			racers[i].start();

		}

	}

}
