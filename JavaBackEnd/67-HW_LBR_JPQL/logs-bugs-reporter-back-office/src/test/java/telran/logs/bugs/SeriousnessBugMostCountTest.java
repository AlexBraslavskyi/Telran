package telran.logs.bugs;
import telran.logs.bugs.dto.Seriousness;
import telran.logs.bugs.dto.SeriousnessBugCount;

public class SeriousnessBugMostCountTest implements SeriousnessBugCount {
	String seriousness;
			public SeriousnessBugMostCountTest() {
		
	}
			
			public SeriousnessBugMostCountTest(String seriousness) {
				super();
				this.seriousness = seriousness;
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
				SeriousnessBugMostCountTest other = (SeriousnessBugMostCountTest) obj;
				if (seriousness == null) {
					if (other.seriousness != null)
						return false;
				} else if (!seriousness.equals(other.seriousness))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "SeriousnessBugMostCountTest [seriousness=" + seriousness + "]";
			}

			


			
		}
