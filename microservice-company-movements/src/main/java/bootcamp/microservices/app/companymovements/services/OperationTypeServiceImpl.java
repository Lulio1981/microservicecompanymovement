package bootcamp.microservices.app.companymovements.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.documents.OperationType;
import bootcamp.microservices.app.companymovements.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.companymovements.repository.OperationTypeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperationTypeServiceImpl implements OperationTypeService {

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Override
	public Flux<OperationType> findAll() {
		return operationTypeRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not exist")));
	}

	@Override
	public Mono<OperationType> findById(String id) {
		return operationTypeRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not found")));
	}

	@Override
	public Mono<OperationType> update(OperationType operationType) {
		return operationTypeRepository.findById(operationType.getId()).flatMap(c -> {
			operationType.setModifyUser(operationType.getModifyUser());
			operationType.setModifyDate(new Date());
			return operationTypeRepository.save(operationType);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not found")));
	}

	@Override
	public Mono<OperationType> save(OperationType operationType) {
		return operationTypeRepository.save(operationType);
	}

	@Override
	public Mono<Void> deleteNonLogic(OperationType operationType) {
		return operationTypeRepository.findById(operationType.getId()).flatMap(c -> {
			return operationTypeRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not found")));
	}

	@Override
	public Mono<OperationType> deleteLogic(OperationType operationType) {
		return operationTypeRepository.findById(operationType.getId()).flatMap(c -> {
			c.setModifyUser(operationType.getModifyUser());
			c.setModifyDate(new Date());
			return operationTypeRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not found")));
	}

	@Override
	public Mono<OperationType> findByShortName(String shortName) {
		return operationTypeRepository.findByShortName(shortName)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("OperationType not found")));
	}

}
