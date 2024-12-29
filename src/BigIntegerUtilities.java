import java.math.BigInteger;

public class BigIntegerUtilities
{
    public static BigInteger modularMultiplicativeInverse(BigInteger value, BigInteger mod)
    {
        BigInteger secondLastMult = BigInteger.ZERO;
        BigInteger lastMult = BigInteger.ONE;

        BigInteger dividend = mod;
        BigInteger divisor = value;

        BigInteger remainder;

        do
        {
            BigInteger[] quotientRemainder = dividend.divideAndRemainder(divisor);
            remainder = quotientRemainder[1];
            BigInteger quotient = quotientRemainder[0];
            BigInteger newMult = secondLastMult.subtract(quotient.multiply(lastMult));
            secondLastMult = lastMult;
            lastMult = newMult;
            dividend = divisor;
            divisor = remainder;
        } while (remainder.compareTo(BigInteger.ONE) > 0); //remainder more than 1

        return lastMult.mod(mod);
    }
}
