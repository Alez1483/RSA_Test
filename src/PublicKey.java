import AsnEncoding.*;

import java.math.BigInteger;

public class PublicKey extends Key
{
    public PublicKey(BigInteger modulus, BigInteger exponent)
    {
        super(modulus, exponent);
    }

    @Override
    public String toString()
    {
        AsnInteger modulusElement = new AsnInteger(getModulus());
        AsnInteger exponentElement = new AsnInteger(getExponent());
        AsnSequence keySequence = new AsnSequence(modulusElement, exponentElement);
        AsnBitString keySequenceBitStr = new AsnBitString(keySequence.getBytes());

        AsnObjectIdentifier rsaIdentifier = new AsnObjectIdentifier("1.2.840.113549.1.1.1");
        AsnElement parameters = AsnNull.getInstance();
        AsnSequence rsaAlgorithmIdentifier = new AsnSequence(rsaIdentifier, parameters);

        AsnSequence mainSequence = new AsnSequence(rsaAlgorithmIdentifier, keySequenceBitStr);

        String base64Str = MimeEncoder.bytesToString(mainSequence.getBytes());
        return "-----BEGIN RSA PUBLIC KEY-----\n" +
                base64Str +
                "\n-----END RSA PUBLIC KEY-----";
    }
}
