package bootcamp.microservices.app.companymovements.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import reactor.core.publisher.Flux;

public interface CompanyMovementRepository extends ReactiveMongoRepository<CompanyMovement, String> {

	public Flux<CompanyMovement> findByMovementTypeAndIdOriginMovement(Integer movementType, String idOriginMovement);
}
