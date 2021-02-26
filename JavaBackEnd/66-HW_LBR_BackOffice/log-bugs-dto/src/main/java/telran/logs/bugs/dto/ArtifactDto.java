package telran.logs.bugs.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ArtifactDto {
	
	@NotEmpty
	public String artifactId;
	@Min(1)
	public long programmerId;
	
	public ArtifactDto(@NotEmpty String artifactId, @Min(1) long programmerId) {
		this.artifactId = artifactId;
		this.programmerId = programmerId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
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
		ArtifactDto other = (ArtifactDto) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		if (programmerId != other.programmerId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ArtifactDto [artifactId=" + artifactId + ", programmerId=" + programmerId + "]";
	}

}
