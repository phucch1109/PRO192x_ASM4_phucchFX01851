package Test;

import Models.DigitalCustomer;
import Models.LoanAccount;
import Models.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalCustomerTest {

    DigitalCustomer digitalCustomer;

    @org.junit.Before
    public void setUp() throws Exception {
        digitalCustomer = new DigitalCustomer("HappyMaker", "111111111111");
        digitalCustomer.addAccount(new LoanAccount("111111111111","111111"));
        digitalCustomer.addAccount(new SavingAccount("111111111111","222222", 900000));

    }

    @Test
    public void getBalance() {
        assertEquals(100900000, digitalCustomer.getBalance(), 0.001);

    }


    @Test
    public void getPremium() {
        assertEquals("Premium",digitalCustomer.isPremium());
    }
}