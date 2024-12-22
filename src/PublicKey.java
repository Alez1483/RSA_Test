import AsnEncoding.AsnElement;
import AsnEncoding.AsnSequence;
import AsnEncoding.AsnTag;

import java.math.BigInteger;
import java.util.Base64;

public class PublicKey extends Key
{
    public PublicKey(BigInteger modulus, BigInteger exponent)
    {
        super(modulus, exponent);
    }

    @Override
    public String toString()
    {
        AsnElement modulusElement = new AsnElement(AsnTag.INTEGER, getModulus().toByteArray());
        AsnElement exponentElement = new AsnElement(AsnTag.INTEGER, getExponent().toByteArray());
        AsnSequence mainSequence = new AsnSequence(modulusElement, exponentElement);

        String lineSeparator = System.lineSeparator();
        byte[] lineSeparatorBytes = new byte[lineSeparator.length()];

        for (int i = 0; i < lineSeparator.length(); i++)
        {
            lineSeparatorBytes[i] = (byte)lineSeparator.charAt(i);
        }

        Base64.Encoder encoder = Base64.getMimeEncoder(64, lineSeparatorBytes);
        String keyStr = encoder.encodeToString(mainSequence.getBytes());

        return "-----BEGIN RSA PUBLIC KEY-----" + lineSeparator + keyStr + lineSeparator + "-----END RSA PUBLIC KEY-----";
    }
}
