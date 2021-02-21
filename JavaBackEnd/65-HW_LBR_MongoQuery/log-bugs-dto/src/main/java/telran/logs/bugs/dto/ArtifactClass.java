package telran.logs.bugs.dto;

public class ArtifactClass {
	public ArtifactClass(String artifact) {
		this.artifact = artifact;
	}
	public static final String LOG_ARTIFACT = "artifact";
public String artifact;


public String getArtifact() {
	return artifact;
}
@Override
public String toString() {
	return "ArtifactClass [artifact=" + artifact + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((artifact == null) ? 0 : artifact.hashCode());
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
	ArtifactClass other = (ArtifactClass) obj;
	if (artifact == null) {
		if (other.artifact != null)
			return false;
	} else if (!artifact.equals(other.artifact))
		return false;
	return true;
}


}
