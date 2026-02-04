package se.iths.armin.enhetstestninglab.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {

    private int accountBalance = 0;


    public int checkAccountBalance() {
        return accountBalance;
    }

    public void depositAmount(int amount) {
        accountBalance += amount;
    }

    public void withdrawAmount(int amount) {
        accountBalance -= amount;
    }
}
