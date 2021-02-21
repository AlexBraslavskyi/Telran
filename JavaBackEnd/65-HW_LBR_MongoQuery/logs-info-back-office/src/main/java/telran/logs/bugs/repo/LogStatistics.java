package telran.logs.bugs.repo;

import reactor.core.publisher.Flux;
import telran.logs.bugs.dto.ArtifactClass;
import telran.logs.bugs.dto.ArtifactCount;
import telran.logs.bugs.dto.LogTypeClass;
import telran.logs.bugs.dto.LogTypeCount;

public interface LogStatistics {
	Flux<LogTypeCount>getLogTypeCounts();
	Flux<LogTypeClass> getMostEncountedEcxeptionTypes(int nExceptions);
	Flux<ArtifactCount> getArtifactOccurrences();
	Flux<ArtifactClass> getMostEncounteredArtifacts(int nArtifacts);
}
