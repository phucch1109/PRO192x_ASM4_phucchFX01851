import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Account> accounts;

    public Customer(String name, String id) {
        super(name,id);
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String isPremium() {
        for (Account i :
                accounts) {
            if (i.isPremium()) return "Premium";
        }
        return "Normal";
    }

    public boolean addAccount(Account account) {
        for (Account i :
                accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(i.getAccountNumber())) {
                System.out.println("Error , account already exist");
                return false;
            }

        }
        accounts.add(account);
        return true;
    }

    public double getBalance() {
        double total = 0;
        for (Account i :
                accounts) {
            total += i.getBalance();
        }
        return total;
    }

    public void displayInformation(){
        //System.out.println(getCustomerId() + " |       " + getName() + " |  " + isPremium() + " " + String.format("%,.2f", getBalance())+"đ");
        System.out.printf("%-12s%-3s%16s%-1s%7s%-1s%13.2f%1s\n",getCustomerId()," | ",getName()," | ",isPremium()," | ",getBalance(),"đ");
        if (accounts.size()==0) System.out.println("This user has no account"); else
        for (int i=0;i<accounts.size();i++) {
           // System.out.println(i+1 + "   " + accounts.get(i).getAccountNumber() + "  |                " + String.format("%,.2f",accounts.get(i).getBalance())+"đ");
            System.out.printf("%-6d%6s%3s%42.2f%1s\n",i+1,accounts.get(i).getAccountNumber()," | ",accounts.get(i).getBalance(),"đ");
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
