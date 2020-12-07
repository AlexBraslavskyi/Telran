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
		
		startRacers(racers, race);
		waitRacers(racers);
		System.out.printf("\nCongratulations to thread #%d - wunner\n", race.photoFinish);

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
