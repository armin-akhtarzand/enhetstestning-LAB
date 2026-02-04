package se.iths.armin.enhetstestninglab.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.armin.enhetstestninglab.component.AccountComponent;
import se.iths.armin.enhetstestninglab.exception.InsufficientFundsException;
import se.iths.armin.enhetstestninglab.exception.InvalidAmountException;
import se.iths.armin.enhetstestninglab.exception.MaxWithdrawalExceededException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ATMServiceTest {

    @Mock
    private AccountComponent accountComponent;
    @InjectMocks
    private ATMService atmService;


    @Test
    public void testCheckAccountBalanceReturnsRightAmount() {
        when(accountComponent.checkAccountBalance()).thenReturn(100);
        Assertions.assertEquals(100, atmService.performCheckAccountBalance());

    }

    @Test
    public void testDepositCallsAccountComponent() {
        Assertions.assertDoesNotThrow(() -> atmService.performDepositAmount(100));

        Mockito.verify(accountComponent).depositAmount(100);

    }

    @Test
    public void testExceptionOnDepositBelowMinAmount() {
        Assertions.assertThrows(InvalidAmountException.class, () -> atmService.performDepositAmount(0));
    }

    @Test
    public void testWithdrawCallsAccountComponent() {

        when(accountComponent.checkAccountBalance()).thenReturn(100);

        Assertions.assertDoesNotThrow(() -> atmService.performWithdrawAmount(100));

        Mockito.verify(accountComponent).withdrawAmount(100);

    }

    @Test
    public void testExceptionOnWithdrawBelowMinAmount() {
        Assertions.assertThrows(InvalidAmountException.class, () -> atmService.performWithdrawAmount(0));
    }

    @Test
    public void testExceptionOnWithdrawOverMaxAmount() {
        Assertions.assertThrows(MaxWithdrawalExceededException.class, () -> atmService.performWithdrawAmount(550));
    }

    @Test
    public void testExceptionOnWithdrawAboveAccountBalance() {
        when(accountComponent.checkAccountBalance()).thenReturn(50);
        Assertions.assertThrows(InsufficientFundsException.class, () -> atmService.performWithdrawAmount(100));
    }
}
