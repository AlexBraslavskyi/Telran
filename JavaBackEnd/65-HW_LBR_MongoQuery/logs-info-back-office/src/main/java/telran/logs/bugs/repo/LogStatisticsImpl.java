package telran.logs.bugs.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import reactor.core.publisher.Flux;
import telran.logs.bugs.dto.ArtifactClass;
import telran.logs.bugs.dto.ArtifactCount;
import telran.logs.bugs.dto.LogTypeClass;
import telran.logs.bugs.dto.LogTypeCount;
import telran.logs.bugs.mongo.doc.LogDoc;

public class LogStatisticsImpl implements LogStatistics {
private static final String COUNT = "count";
@Autowired
	ReactiveMongoTemplate mongoTemplate;
	@Override
	public Flux<LogTypeCount> getLogTypeCounts() {
		GroupOperation groupOperation = Aggregation.group(LogDoc.LOG_TYPE).count().as(COUNT);
		ProjectionOperation projOperation = Aggregation.project(COUNT).and("_id")
				.as(LogTypeCount.LOG_TYPE);
		SortOperation sortOperation = Aggregation.sort(Direction.DESC, COUNT);
		TypedAggregation<LogDoc> pipeline =
				Aggregation.newAggregation(LogDoc.class, groupOperation, sortOperation, projOperation);
		return mongoTemplate.aggregate(pipeline, LogTypeCount.class);
	}
	@Override
	public Flux<LogTypeClass> getMostEncountedEcxeptionTypes(int nExceptions) {
		GroupOperation groupOperation = Aggregation.group(LogDoc.LOG_TYPE).count().as(COUNT);
		ProjectionOperation projOperation = Aggregation.project().andExclude("_id").and("_id")
				.as(LogTypeCount.LOG_TYPE);
		SortOperation sortOperation = Aggregation.sort(Direction.DESC, COUNT);
		LimitOperation limitOperation = Aggregation.limit(nExceptions);
		TypedAggregation<LogDoc> pipeline =
				Aggregation.newAggregation(LogDoc.class, groupOperation, sortOperation, limitOperation, projOperation);
		return mongoTemplate.aggregate(pipeline, LogTypeClass.class);
	}
	@Override
	public Flux<ArtifactCount> getArtifactOccurrences() {
		GroupOperation groupOperation = Aggregation.group(LogDoc.LOG_ARTIFACT).count().as(COUNT);
		ProjectionOperation projOperation = Aggregation.project(COUNT).and("_id")
				.as(ArtifactCount.LOG_ARTIFACT);
		SortOperation sortOperation = Aggregation.sort(Direction.DESC, COUNT);
		TypedAggregation<LogDoc> pipeline =
				Aggregation.newAggregation(LogDoc.class, groupOperation, sortOperation, projOperation);
		return mongoTemplate.aggregate(pipeline, ArtifactCount.class);
	}
	@Override
	public Flux<ArtifactClass> getMostEncounteredArtifacts(int nArtifacts) {
		GroupOperation groupOperation = Aggregation.group(LogDoc.LOG_ARTIFACT).count().as(COUNT);
		ProjectionOperation projOperation = Aggregation.project().andExclude("_id").and("_id")
				.as(ArtifactCount.LOG_ARTIFACT);
		SortOperation sortOperation = Aggregation.sort(Direction.DESC, COUNT);
		LimitOperation limitOperation = Aggregation.limit(nArtifacts);
		TypedAggregation<LogDoc> pipeline =
				Aggregation.newAggregation(LogDoc.class, groupOperation, sortOperation, limitOperation, projOperation);
		return mongoTemplate.aggregate(pipeline, ArtifactClass.class);
	}

}
