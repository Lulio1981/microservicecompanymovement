package bootcamp.microservices.app.companymovements.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import bootcamp.microservices.app.companymovements.documents.OperationType;
import reactor.core.publisher.Mono;

public interface OperationTypeRepository extends ReactiveMongoRepository<OperationType, String> {

	public Mono<OperationType> findByShortName(String shortName);
}
