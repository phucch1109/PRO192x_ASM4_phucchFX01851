package Test;

import Models.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.*;

public class SavingAccountTest {
    SavingAccount savingAccountNormal,savingAccountPremium;
    @org.junit.Before
    public void setUp() throws Exception {
         savingAccountPremium = new SavingAccount("111111",20000000);
         savingAccountNormal = new SavingAccount("222222",9000000);
    }

    @Test
    public void isPremium() {
        assertFalse(savingAccountNormal.isPremium());
        assertTrue(savingAccountPremium.isPremium());
    }

    @Test
    public void withdraw() {
        //kiem tra dieu kien duoi 50000
        assertFalse(savingAccountNormal.withdraw(45000));
        //kiem tra dieu kien boi so 10000
        assertFalse(savingAccountNormal.withdraw(68720));
        //kiem tra han muc duoi 50000
        assertFalse(savingAccountNormal.withdraw(8999000));
        //kiem tra qua 5000000 vi la tai khoan thuong
        assertFalse(savingAccountNormal.withdraw(8000000));
        assertTrue(savingAccountNormal.withdraw(50000));
        //kiem tra qua 5000000 vi la tai khoan premium
        assertTrue(savingAccountPremium.withdraw(8000000));
        //kiem tra da rut duoc tien chua?
        assertFalse(savingAccountPremium.withdraw(12000000));
    }

    @Test
    public void isAccepted() {
        assertFalse(savingAccountNormal.isAccepted(45000));
        //kiem tra dieu kien boi so 10000
        assertFalse(savingAccountNormal.isAccepted(68720));
        //kiem tra han muc duoi 50000
        assertFalse(savingAccountNormal.isAccepted(8999000));
        //kiem tra qua 5000000 vi la tai khoan thuong
        assertFalse(savingAccountNormal.isAccepted(8000000));
        assertTrue(savingAccountNormal.isAccepted(50000));
        //kiem tra qua 5000000 vi la tai khoan premium
        assertTrue(savingAccountPremium.isAccepted(8000000));

    }
}