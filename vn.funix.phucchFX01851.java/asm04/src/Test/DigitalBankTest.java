package Test;

import Asm2.Account;
import Asm2.Customer;
import Models.DigitalBank;
import exception.CustomerIdNotValidException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DigitalBankTest {

    ArrayList<Customer> customers;
    ArrayList<Account> accounts;
    @Before
    public void setUp() throws Exception {
    customers = new ArrayList<>();
    accounts = new ArrayList<>();
    customers.add(new Customer("happy","111111111111"));
    accounts.add((new Account("123456",9000000,"111111111111")));
    }

    @Test(expected = CustomerIdNotValidException.class)
    public void getCustomerById() {
        assertNotNull(DigitalBank.getCustomerById(customers,"111111111111"));
        DigitalBank.getCustomerById(customers,"000000");
    }

    @Test
    public void isAccountNumberExisted() {
        assertEquals(false,DigitalBank.isAccountNumberExisted(accounts,"141651"));
        assertEquals(true,DigitalBank.isAccountNumberExisted(accounts,"123456"));
    }
    @Test(expected = CustomerIdNotValidException.class)
    public void isCustomerIdExisted() {
        DigitalBank.isCustomerIdExisted(customers,"141651");
        assertEquals(true,DigitalBank.isCustomerIdExisted(customers,"111111111111"));
    }
    @Test
    public void getAccountByAccountNumber() {
        assertNotNull(DigitalBank.getAccountByAccountNumber(accounts,"123456"));
        assertNull(DigitalBank.getAccountByAccountNumber(accounts,"000000"));
    }
}