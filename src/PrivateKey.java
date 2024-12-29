import AsnEncoding.*;

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

    @Override
    public String toString()
    {
        AsnInteger version = new AsnInteger(BigInteger.ZERO);
        AsnInteger modulus = new AsnInteger(getModulus());
        AsnInteger publicExponent = new AsnInteger(e);
        AsnInteger privateExponent = new AsnInteger(getExponent());
        AsnInteger prime1 = new AsnInteger(p);
        AsnInteger prime2 = new AsnInteger(q);
        AsnInteger exponent1 = new AsnInteger(getExponent().mod(p.subtract(BigInteger.ONE)));
        AsnInteger exponent2 = new AsnInteger(getExponent().mod(q.subtract(BigInteger.ONE)));
        AsnInteger coefficient = new AsnInteger(BigIntegerUtilities.modularMultiplicativeInverse(q, p));

        AsnSequence mainSequence = new AsnSequence(
                version,
                modulus,
                publicExponent,
                privateExponent,
                prime1,
                prime2,
                exponent1,
                exponent2,
                coefficient
        );

        String base64Str = MimeEncoder.bytesToString(mainSequence.getBytes());
        return "-----BEGIN RSA PRIVATE KEY-----\n" +
                base64Str +
                "\n-----END RSA PRIVATE KEY-----";
    }
}
