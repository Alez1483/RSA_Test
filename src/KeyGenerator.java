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

            do //primes must not be equal (maybe?), very unlikely anyways
            {
                q = BigInteger.probablePrime(bitLength, rand);
            } while (p.equals(q));

            n = p.multiply(q);
            eulersTotient = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        } while (eulersTotient.remainder(e).equals(BigInteger.ZERO));

        BigInteger d = BigIntegerUtilities.modularMultiplicativeInverse(e, eulersTotient);

        PrivateKey privateKey = new PrivateKey(n, d, q, p, e);
        PublicKey publicKey = new PublicKey(n, e);
        return new KeyPair(privateKey, publicKey);
    }
}
