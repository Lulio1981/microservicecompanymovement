package bootcamp.microservices.app.companymovements.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.services.CompanyMovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CompanyMovementController {

	@Autowired
	private CompanyMovementService companyMovementService;

	@PostMapping
	public Mono<CompanyMovement> createClient(@Valid @RequestBody CompanyMovement companyCredit) {
		return companyMovementService.save(companyCredit);
	}

	@GetMapping("/all")
	public Flux<CompanyMovement> searchAll() {
		return companyMovementService.findAll();
	}

	@GetMapping("/id/{id}")
	public Mono<CompanyMovement> searchById(@PathVariable String id) {
		return companyMovementService.findById(id);
	}

	@PutMapping
	public Mono<CompanyMovement> updateClientCredit(@RequestBody CompanyMovement companyCredit) {
		return companyMovementService.update(companyCredit);
	}

	@DeleteMapping
	public Mono<CompanyMovement> deleteClientCredit(@Valid @RequestBody CompanyMovement companyCredit) {
		return companyMovementService.deleteLogic(companyCredit);
	}

	@GetMapping("idOriginMovement/{idOriginMovement}")
	public Flux<CompanyMovement> searchByMovementAndIdOriginCompany(@PathVariable Integer movementType,
			@PathVariable String idOriginMovement) {
		return companyMovementService.findByMovementTypeOrigin(idOriginMovement);
	}

	@GetMapping("idDestinyMovement/{idDestinyMovement}")
	public Flux<CompanyMovement> searchByMovementAndIdDestinyCompany(@PathVariable Integer movementType,
			@PathVariable String idDestinyMovement) {
		return companyMovementService.findByMovementTypeDestiny(idDestinyMovement);
	}

	@GetMapping("/idProduct/{idProduct}")
	public Mono<Double> balanceProductCalculate(@PathVariable String idProduct) {
		return companyMovementService.CalculateBalanceByIdOriginMovement(idProduct);
	}

	@GetMapping("idOriginMovementComplete/{idOriginMovementComplete")
	public Flux<CompanyMovement> searchAllMovementByProduct(@PathVariable String idOriginMovementComplete) {
		return companyMovementService.findAllMovementsByIdProduct(idOriginMovementComplete);
	}
}
