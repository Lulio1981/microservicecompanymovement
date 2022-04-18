package bootcamp.microservices.app.companymovements.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootcamp.microservices.app.companymovements.documents.CompanyMovement;
import bootcamp.microservices.app.companymovements.services.CompanyMovementService;

@Component
public class BalanceCalculate {

	@Autowired
	private CompanyMovementService service;

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

}
