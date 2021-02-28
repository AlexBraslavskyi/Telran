package telran.logs.bugs.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;

public class CloseBugData {
	@Min(1)
public long bugId;
public LocalDate dateClose;
public String description;
public CloseBugData(@Min(1) long bugId, LocalDate dateClose, String description) {
	super();
	this.bugId = bugId;
	this.dateClose = dateClose;
	this.description = description;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (bugId ^ (bugId >>> 32));
	result = prime * result + ((dateClose == null) ? 0 : dateClose.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
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
	CloseBugData other = (CloseBugData) obj;
	if (bugId != other.bugId)
		return false;
	if (dateClose == null) {
		if (other.dateClose != null)
			return false;
	} else if (!dateClose.equals(other.dateClose))
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	return true;
}
@Override
public String toString() {
	return "CloseBugData [bugId=" + bugId + ", dateClose=" + dateClose + ", description=" + description + "]";
}


}

