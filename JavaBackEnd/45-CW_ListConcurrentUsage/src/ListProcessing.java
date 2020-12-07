import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListProcessing extends Thread {
	
List<Integer> list;
int nRuns;
int probUpdate;

static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
static Lock readLock = lock.readLock();
static Lock writeLock = lock.writeLock();
static AtomicInteger countLockIterations = new AtomicInteger(0);
public ListProcessing(List<Integer> list, int nRuns, int probUpdate) {
	super();
	this.list = list;
	this.nRuns = nRuns;
	this.probUpdate = probUpdate;
}

@Override
public void run() {
	for(int i = 0; i<=nRuns; i++) {
		if(Math.random() * 100 < probUpdate) {	
			update();
			
		}else {
			read();
		}
	}
	}

private void read() {

		try {
			lock(readLock); //			readLock.lock();
			int lastIndex = list.size() - 1;
			for (int i = 0; i < 10; i++) {
				list.get(lastIndex);
			} 
		} finally {
			readLock.unlock();
		}
	
}

private void lock(Lock lockObj) {
	while(!lockObj.tryLock()) {
		countLockIterations.incrementAndGet();
	}
	
}

private void update() {
		try {
			lock(writeLock);
			int lastIndex = list.size() - 1;
			list.remove(lastIndex);
			list.add(lastIndex -1, 100);
		} finally {
			writeLock.unlock();
		}
	
}

}
