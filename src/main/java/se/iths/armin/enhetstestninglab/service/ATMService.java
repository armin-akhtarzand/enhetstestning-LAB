package se.iths.armin.enhetstestninglab.service;

import org.springframework.stereotype.Service;
import se.iths.armin.enhetstestninglab.component.AccountComponent;
import se.iths.armin.enhetstestninglab.exception.InsufficientFundsException;
import se.iths.armin.enhetstestninglab.exception.InvalidAmountException;
import se.iths.armin.enhetstestninglab.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {


    private final int maxWithdrawAmount = 500;
    private final int minAmount = 1;
    private final AccountComponent accountComponent;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public int performCheckAccountBalance() {
        return accountComponent.checkAccountBalance();
    }

    public void performDepositAmount(int amount) {
        if (amount < minAmount) {
            throw new InvalidAmountException("Invalid deposit amount");
        }
        accountComponent.depositAmount(amount);
    }

    public void performWithdrawAmount(int amount) {
        if (amount < minAmount) {
            throw new InvalidAmountException("Invalid withdrawal amount");
        }
        if (amount > maxWithdrawAmount) {
            throw new MaxWithdrawalExceededException("Invalid withdrawal amount");
        }
        if (amount > accountComponent.checkAccountBalance()) {
            throw new InsufficientFundsException("Withdrawal amount exceeds the account balance");
        }
        accountComponent.withdrawAmount(amount);
    }


}
