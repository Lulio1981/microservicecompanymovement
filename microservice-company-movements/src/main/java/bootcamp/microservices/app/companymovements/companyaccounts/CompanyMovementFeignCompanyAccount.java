package bootcamp.microservices.app.companymovements.companyaccounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bootcamp.microservices.app.companymovements.documents.CompanyAccount;
import reactor.core.publisher.Mono;

@FeignClient(name = "microservice-company-accounts")
public interface CompanyMovementFeignCompanyAccount {

	@GetMapping("/id/{id}")
	public Mono<CompanyAccount> searchById(@PathVariable String id);
}
