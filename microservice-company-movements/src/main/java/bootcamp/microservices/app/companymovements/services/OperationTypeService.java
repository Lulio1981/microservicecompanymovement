package bootcamp.microservices.app.companymovements.services;

import bootcamp.microservices.app.companymovements.documents.OperationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationTypeService {

	public Flux<OperationType> findAll();

	public Mono<OperationType> findById(String id);

	public Mono<OperationType> save(OperationType operationType);

	public Mono<OperationType> update(OperationType operationType);

	public Mono<Void> deleteNonLogic(OperationType operationType);

	public Mono<OperationType> deleteLogic(OperationType operationType);

	public Mono<OperationType> findByShortName(String shortName);

}
