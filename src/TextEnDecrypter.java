import java.math.BigInteger;

public class TextEnDecrypter
{
    public static BigInteger encryptMessage(String message, Key publicKey)
    {
        byte[] messageData = message.getBytes();
        BigInteger messageNum = new BigInteger(messageData);

        if (messageNum.compareTo(publicKey.getModulus()) >= 0)
        {
            System.out.println("Message is too long to encrypt");
            return BigInteger.ZERO;
        }

        return cryptBigInteger(messageNum, publicKey);
    }

    public static String decryptMessage(BigInteger cryptedMessage, Key privateKey)
    {
        BigInteger decryptedData = cryptBigInteger(cryptedMessage, privateKey);
        return new String(decryptedData.toByteArray());
    }

    //can both encrypt and decrypt BigIntegers
    //depends on the key used (private or public) which way the cryption happens
    public static BigInteger cryptBigInteger(BigInteger message, Key key)
    {
        return message.modPow(key.getExponent(), key.getModulus());
    }
}
