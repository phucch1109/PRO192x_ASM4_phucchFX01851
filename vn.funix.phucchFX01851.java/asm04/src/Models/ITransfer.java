package Models;

import Asm2.Account;

public interface ITransfer {
    void transfer(Account receiveAccount, double amount);
}
