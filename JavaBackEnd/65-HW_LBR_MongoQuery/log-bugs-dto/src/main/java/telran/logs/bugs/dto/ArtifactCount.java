package telran.logs.bugs.dto;

public class ArtifactCount {
	
	public static final String LOG_ARTIFACT = "artifact";
public String artifact;
public long count;
public ArtifactCount(String artifact, long count) {
	this.artifact=artifact;
	this.count=count;
}

@Override
public String toString() {
	return "ArtifactCount [artifact=" + artifact + ", count=" + count + "]";
}

public static String getLogArtifact() {
	return LOG_ARTIFACT;
}

public String getArtifact() {
	return artifact;
}

public long getCount() {
	return count;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((artifact == null) ? 0 : artifact.hashCode());
	result = prime * result + (int) (count ^ (count >>> 32));
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
	ArtifactCount other = (ArtifactCount) obj;
	if (artifact == null) {
		if (other.artifact != null)
			return false;
	} else if (!artifact.equals(other.artifact))
		return false;
	if (count != other.count)
		return false;
	return true;
}

}
