package telran.logs.bugs.dto;

import javax.validation.constraints.Min;

public class AssignBugData {

	@Min(1)
public long bugId;
	@Min(1)
public long programmerId;
public 	String description;
public AssignBugData(@Min(1) long bugId, @Min(1) long programmerId, String description) {
	super();
	this.bugId = bugId;
	this.programmerId = programmerId;
	this.description = description;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (bugId ^ (bugId >>> 32));
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + (int) (programmerId ^ (programmerId >>> 32));
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
	AssignBugData other = (AssignBugData) obj;
	if (bugId != other.bugId)
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (programmerId != other.programmerId)
		return false;
	return true;
}

}
