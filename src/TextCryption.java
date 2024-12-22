import java.math.BigInteger;

public class TextCryption
{
    public static BigInteger cryptMessage(String message, Key publicKey)
    {
        byte[] messageData = message.getBytes();
        BigInteger messageNum = new BigInteger(messageData);

        if (messageNum.compareTo(publicKey.getModulus()) >= 0)
        {
            System.out.println("Message length was too much");
            return BigInteger.ZERO;
        }

        return cryptBigInteger(messageNum, publicKey);
    }

    public static String decryptMessage(BigInteger cryptedNum, Key privateKey)
    {
        BigInteger decryptedData = cryptBigInteger(cryptedNum, privateKey);
        return new String(decryptedData.toByteArray());
    }

    public static BigInteger cryptBigInteger(BigInteger message, Key key)
    {
        return message.modPow(key.getExponent(), key.getModulus());
    }
}
