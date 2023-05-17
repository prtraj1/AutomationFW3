package dummy;

public class SBIBank implements Bank{
    @Override
    public void withdraw() {
        System.out.println("SBI's withdraw logic!!");
    }

    @Override
    public void deposit() {
        System.out.println("SBI's deposit logic");
    }

    @Override
    public void eKyc() {
        System.out.println("SBI's eKYC logic");
    }
}
