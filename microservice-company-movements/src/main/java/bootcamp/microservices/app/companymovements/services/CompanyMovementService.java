package bootcamp.microservices.app.companymovements.services;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyMovementService {

	public Flux<CompanyMovement> findAll();

	public Mono<CompanyMovement> findById(String id);

	public Mono<CompanyMovement> save(CompanyMovement companyMovement);

	public Mono<CompanyMovement> update(CompanyMovement companyMovement);

	public Mono<Void> deleteNonLogic(CompanyMovement companyMovement);

	public Mono<CompanyMovement> deleteLogic(CompanyMovement companyMovement);

	public Flux<CompanyMovement> findByMovementTypeOrigin(String idOriginMovement);

	public Flux<CompanyMovement> findByMovementTypeDestiny(String idDestinyMovement);

	public Mono<Double> CalculateBalanceByIdOriginMovement(String idOriginMovement);

	public Flux<CompanyMovement> findAllMovementsByIdProduct(String idOriginMovement);
}
