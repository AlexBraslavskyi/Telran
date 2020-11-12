package telran.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	
	private static final String DEFAULT_FORMAT = "HH:mm:ss";
	private long timeout;
	private String timeFormat;
	
	public Timer(long timeout, String timeFormat) {
	setDaemon(true);
		this.timeout = timeout;
		this.timeFormat = timeFormat;
	}
public Timer() {
	this(1000, DEFAULT_FORMAT);
}

@Override
public void run() {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
	while (true) {
		System.out.println(LocalTime.now().format(formatter));
		if(interrupted()) {
			break;
		}
		try {
			sleep(timeout);
		} catch (InterruptedException e) {
			break;
		}
		
	}
}
}
