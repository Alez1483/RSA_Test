import java.math.BigInteger;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        final int bitLength = 1024;

        KeyPair keys = KeyGenerator.generateKeys(bitLength, rand);

        String message = "Hullo, let's try something here!";

        BigInteger cryptedMessage = TextCryption.cryptMessage(message, keys.publicKey);
        System.out.println("modulo: " + cryptedMessage);
        System.out.println("Decrypted message: " + TextCryption.decryptMessage(cryptedMessage, keys.privateKey));

        System.out.println(keys.publicKey);
    }
}