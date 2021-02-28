package telran.logs.bugs;

import telran.logs.bugs.dto.MostProgrammerBugs;

public class RatingProgrammersBugsTest implements MostProgrammerBugs {
	
	String programmerName;
	
	public RatingProgrammersBugsTest() {

}



	
	public RatingProgrammersBugsTest(String programmerName) {
		this.programmerName = programmerName;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programmerName == null) ? 0 : programmerName.hashCode());
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
		RatingProgrammersBugsTest other = (RatingProgrammersBugsTest) obj;
		if (programmerName == null) {
			if (other.programmerName != null)
				return false;
		} else if (!programmerName.equals(other.programmerName))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "RatingProgrammersBugsTest [programmerName=" + programmerName + "]";
	}




	@Override
	public String getProgrammerName() {
		// TODO Auto-generated method stub
		return null;
	}

}
