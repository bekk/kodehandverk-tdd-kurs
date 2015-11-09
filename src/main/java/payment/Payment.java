package payment;

public interface Payment {
    /**
     * @param amount
     * @param creditCardNumber
     * @return true if the payment was successful, false otherwise
     */
    boolean pay(int amount, String creditCardNumber);
}
