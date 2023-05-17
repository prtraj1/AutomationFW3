package dummy;

public class KotakBank implements Bank{
    @Override
    public void withdraw() {
        System.out.println("Kotak's withdraw logic!!");
    }

    @Override
    public void deposit() {
        System.out.println("Kotak's deposit logic");
    }

    @Override
    public void eKyc() {
        System.out.println("Kotak's eKYC logic");
    }
}
