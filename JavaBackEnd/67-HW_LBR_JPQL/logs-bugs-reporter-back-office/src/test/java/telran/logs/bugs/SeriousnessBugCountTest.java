package telran.logs.bugs;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.dto.SeriousnessBugCount;

public class SeriousnessBugCountTest implements SeriousnessBugCount {
	Seriousness seriousness;
	long count;
			public SeriousnessBugCountTest() {
		
	}
			
			public SeriousnessBugCountTest(Seriousness seriousness, long count) {
				super();
				this.seriousness = seriousness;
				this.count = count;
			}

			@Override
			public Seriousness getSeriousness() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public long getCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + (int) (count ^ (count >>> 32));
				result = prime * result + ((seriousness == null) ? 0 : seriousness.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				SeriousnessBugCountTest other = (SeriousnessBugCountTest) obj;
				if (count != other.count)
					return false;
				if (seriousness != other.seriousness)
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "SeriousnessBugCountTest [seriousness=" + seriousness + ", count=" + count + "]";
			}

			

			
		}
