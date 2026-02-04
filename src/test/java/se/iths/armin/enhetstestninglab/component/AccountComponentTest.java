package se.iths.armin.enhetstestninglab.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountComponentTest {

    AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();
    }

    @Test
    public void testCheckAccountBalance() {
        assertEquals(0, accountComponent.checkAccountBalance());
    }

    @Test
    public void testWithdrawAmountSubtractsFromAccountBalance() {

        accountComponent.depositAmount(100);

        accountComponent.withdrawAmount(50);

        assertEquals(50, accountComponent.checkAccountBalance());
    }

    @Test
    public void testDepositAmountAddsToAccountBalance() {
        accountComponent.depositAmount(100);

        assertEquals(100, accountComponent.checkAccountBalance());
    }

    @Test
    public void testMultipleDepositAmountAddsToAccountBalance() {
        accountComponent.depositAmount(100);
        accountComponent.depositAmount(100);

        assertEquals(200, accountComponent.checkAccountBalance());
    }


}
