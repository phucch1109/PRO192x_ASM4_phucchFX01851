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
        digitalCustomer.addAccount(new LoanAccount("111111"));
        digitalCustomer.addAccount(new SavingAccount("222222", 900000));

    }

    @Test
    public void getBalance() {
        assertEquals(900000, digitalCustomer.getBalance(), 0.001);
        digitalCustomer.withdrawAccount("111111",900000);
        assertEquals(1845000, digitalCustomer.getBalance(), 0.001);
    }


    @Test
    public void getPremium() {
        assertEquals("Normal",digitalCustomer.isPremium());
        digitalCustomer.addAccount(new SavingAccount("222232", 20000000));
        assertEquals("Premium",digitalCustomer.isPremium());
    }
}