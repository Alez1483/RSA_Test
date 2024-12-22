import java.math.BigInteger;

public class Key
{
    private final BigInteger modulus; //n
    private final BigInteger exponent; //e or d

    public Key(BigInteger modulus, BigInteger exponent)
    {
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getExponent()
    {
        return exponent;
    }
}