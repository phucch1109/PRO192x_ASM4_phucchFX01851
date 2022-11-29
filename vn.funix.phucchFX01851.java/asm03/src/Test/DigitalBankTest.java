package Test;

import Models.DigitalBank;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalBankTest {

    DigitalBank bank;
    @Before
    public void setUp() throws Exception {
        bank= new DigitalBank();
        bank.addCustomer("111111111111","happy");
        bank.addLoanAccount("111111111111", "151651");
        bank.addSavingAccount("111111111111", "895545", 2849000);
    }

    @Test
    public void getCustomerById() {
        assertNotNull(bank.getCustomerById("111111111111"));
        assertNull(bank.getCustomerById("111112111111"));
    }

    @Test
    public void isAccountNumberExisted() {
        assertEquals(true,bank.isAccountNumberExisted("151651"));
        assertEquals(false,bank.isAccountNumberExisted("141651"));
    }
}