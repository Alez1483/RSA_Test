import java.math.BigInteger;

public class PrivateKey extends Key
{
    private final BigInteger q;
    private final BigInteger p;
    private final BigInteger e;

    public PrivateKey(BigInteger modulus, BigInteger privateExponent, BigInteger q, BigInteger p, BigInteger e)
    {
        super(modulus, privateExponent);

        this.q = q;
        this.p = p;
        this.e = e;
    }

    public BigInteger getQ()
    {
        return q;
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getE()
    {
        return e;
    }
}
