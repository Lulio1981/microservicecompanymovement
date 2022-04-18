package bootcamp.microservices.app.companymovements.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.companymovements.repository.CompanyMovementRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CompanyMovementServiceImpl implements CompanyMovementService {

	private static final Logger log = LoggerFactory.getLogger(CompanyMovementServiceImpl.class);

	@Autowired
	private CompanyMovementRepository companyMovementRepository;

	@Override
	public Flux<CompanyMovement> findAll() {
		return companyMovementRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Clients not exist")));
	}

	@Override
	public Mono<CompanyMovement> findById(String id) {
		return companyMovementRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyMovement not found")));
	}

	@Override
	public Mono<CompanyMovement> update(CompanyMovement companyMovement) {
		return companyMovementRepository.findById(companyMovement.getId()).flatMap(c -> {
			companyMovement.setModifyUser(companyMovement.getModifyUser());
			companyMovement.setModifyDate(new Date());
			return companyMovementRepository.save(companyMovement);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyMovement not found")));
	}

	@Override
	public Mono<CompanyMovement> save(CompanyMovement companyMovement) {
		return companyMovementRepository.save(companyMovement).flatMap(cm -> {
			if (cm.getOperationType().getShortName().equalsIgnoreCase("TRANS")
					|| cm.getOperationType().getShortName().equalsIgnoreCase("CREPAY")) {
				cm.setMovementType(1);
				return companyMovementRepository.save(cm);
			}
			return Mono.just(companyMovement);
		});
	}

	@Override
	public Mono<Void> deleteNonLogic(CompanyMovement companyMovement) {
		return companyMovementRepository.findById(companyMovement.getId()).flatMap(c -> {
			return companyMovementRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyMovement not found")));
	}

	@Override
	public Mono<CompanyMovement> deleteLogic(CompanyMovement companyMovement) {
		return companyMovementRepository.findById(companyMovement.getId()).flatMap(c -> {
			c.setModifyUser(companyMovement.getModifyUser());
			c.setModifyDate(new Date());
			return companyMovementRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyMovement not found")));
	}

	@Override
	public Flux<CompanyMovement> findByMovementTypeOrigin(Integer movementType, String idDestinyMovement) {
		return companyMovementRepository.findByMovementTypeAndIdDestinyMovement(movementType, idDestinyMovement);
	}

	@Override
	public Flux<CompanyMovement> findByMovementTypeDestiny(Integer movementType, String idOriginMovement) {
		return companyMovementRepository.findByMovementTypeAndIdOriginMovement(movementType, idOriginMovement);
	}
}
