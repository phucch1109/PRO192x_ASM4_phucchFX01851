package Test;

import Models.LoanAccount;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LoanAccountTest {

    LoanAccount loanAccount;

    @org.junit.Before
    public void setUp() throws Exception {
        loanAccount = new LoanAccount("111111111111","111111");
        System.out.println(100000000-99940000);
    }

    @org.junit.Test
    public void isPremium() {
        assertFalse(loanAccount.isPremium());
        loanAccount.setBalance(20000000);
        assertTrue(loanAccount.isPremium());

    }

    @org.junit.Test
    public void withdraw() {
        assertTrue(loanAccount.withdraw(50000000));
        assertFalse(loanAccount.withdraw(50000000));
    }

    @org.junit.Test
    public void transactionFee() {
        assertEquals(5000, loanAccount.transactionFee(100000),0.001);
        loanAccount.setBalance(20000000);
        assertEquals(1000, loanAccount.transactionFee(100000),0.001);
    }

    @org.junit.Test
    public void isAccepted() {
        //kiem tra duoi 50000
        assertFalse(loanAccount.isAccepted(40000));
        //kiem tra qua han muc 100000000
        assertFalse(loanAccount.isAccepted(100000000));
        //kiem tra han muc con lai ko qua 50000(tinh ca transactionFee)
        assertFalse(loanAccount.isAccepted(99960000));
        assertTrue(loanAccount.isAccepted(95000000));
    }
}