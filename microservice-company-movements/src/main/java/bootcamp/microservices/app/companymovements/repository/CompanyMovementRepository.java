package bootcamp.microservices.app.companymovements.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.documents.OperationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyMovementRepository extends ReactiveMongoRepository<CompanyMovement, String> {

	public Flux<CompanyMovement> findByMovementTypeAndIdOriginMovement(Integer movementType, String idOriginMovement);

	public Flux<CompanyMovement> findByMovementTypeAndIdDestinyMovement(Integer movementType, String idDestinyMovement);

	public Mono<CompanyMovement> FindByOperationType(OperationType operationType);

}
