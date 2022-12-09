package Test;

import Asm2.Customer;
import Models.DigitalBank;
import Models.DigitalCustomer;
import dao.CustomerDao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoTest {

    @Before
    public void setUp() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new DigitalCustomer("Happy Maker", "111111111111"));
        CustomerDao.save(customerList);
        DigitalBank.addSavingAccount("111111111111", "123456", 50000000);
        DigitalBank.addSavingAccount("111111111111", "123476", 50000000);
    }

    @Test
    public void save() {

    }

    @Test
    public void list() {
    }

    @Test
    public void update() {
    }
}