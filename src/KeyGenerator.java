import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator
{
    public static KeyPair generateKeys(final int bitLength, Random rand)
    {
        BigInteger p;
        BigInteger q;
        BigInteger n;
        BigInteger eulersTotient;
        BigInteger e = BigInteger.valueOf(65537); //

        do //gcd(eulerTotient, e) must be 1
        {
            p = BigInteger.probablePrime(bitLength, rand);

            do //primes must not be equal (maybe?)
            {
                q = BigInteger.probablePrime(bitLength, rand);
            } while (p.equals(q));

            n = p.multiply(q);
            eulersTotient = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        } while (eulersTotient.remainder(e).equals(BigInteger.ZERO));

        BigInteger d = modularMultiplicativeInverse(e, eulersTotient);

        PrivateKey privateKey = new PrivateKey(n, d, q, p, e);
        PublicKey publicKey = new PublicKey(n, e);
        return new KeyPair(privateKey, publicKey);
    }

    private static BigInteger modularMultiplicativeInverse(BigInteger value, BigInteger mod)
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
