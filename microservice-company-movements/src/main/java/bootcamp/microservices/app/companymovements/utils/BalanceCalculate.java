package bootcamp.microservices.app.companymovements.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootcamp.microservices.app.companymovements.companyaccounts.CompanyMovementFeignCompanyAccount;
import bootcamp.microservices.app.companymovements.documents.CompanyAccount;
import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.repository.CompanyMovementRepository;
import bootcamp.microservices.app.companymovements.services.CompanyMovementService;

@Component
public class BalanceCalculate {

	@Autowired
	private CompanyMovementService service;

	@Autowired
	private CompanyMovementFeignCompanyAccount companyMovementFeignCompanyAccount;

	@Autowired
	private CompanyMovementRepository companyMovementRepository;

	public Double balanceAmount(String idAccount) {

		Double totallyBalance = 0d;

		Double addIncome = service.findByMovementTypeOrigin(idAccount).toStream()
				.filter(cm -> cm.getMovementType() == 1).mapToDouble(CompanyMovement::getAmount).sum();

		Double addIncomeTransferAndCreditPayment = service.findByMovementTypeDestiny(idAccount).toStream()
				.filter(cm -> cm.getMovementType() == 1).mapToDouble(CompanyMovement::getAmount).sum();

		Double addExpenses = service.findByMovementTypeOrigin(idAccount).toStream()
				.filter(cm -> cm.getMovementType() == 0).mapToDouble(CompanyMovement::getAmount).sum();

		totallyBalance = addIncome + addIncomeTransferAndCreditPayment - addExpenses;

		return totallyBalance;
	}

	public int numberOfCompanyAccountOperations(String idOriginMovement) {
		return Integer.parseInt(companyMovementRepository.findByIdOriginMovement(idOriginMovement)
				.filter(cm -> cm.getOperationType().getShortName() == "DEPOS")
				.filter(cm -> cm.getOperationType().getShortName() == "WITHD").count().block().toString());
	}

	public int numberOperationsMonth(String idOriginMovement) {
		return companyMovementFeignCompanyAccount.searchById(idOriginMovement).block().getOperationsNumber();
	}

}
