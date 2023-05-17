package dummy;

public class TestBank {
    public static void main(String[] args) {
        Bank sbiBank = new SBIBank();
        sbiBank.deposit();
        sbiBank.eKyc();
        sbiBank.withdraw();

        Bank kotakBank = new KotakBank();
        kotakBank.deposit();
        kotakBank.eKyc();
        kotakBank.withdraw();
    }
}
