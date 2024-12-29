import java.math.BigInteger;
import java.util.Random;

public class Main
{
    //generates keys and uses them to encrypt and decrypt a message as well as prints the keys
    public static void main(String[] args)
    {
        Random rand = new Random();
        final int bitLength = 1024; //length of the generated primes, modulo is roughly 2 times that

        KeyPair keys = KeyGenerator.generateKeys(bitLength, rand);

        String message = "Hullo, let's write something here!\n";

        BigInteger cryptedMessage = TextEnDecrypter.encryptMessage(message, keys.publicKey);
        System.out.println("Decrypted message: " + TextEnDecrypter.decryptMessage(cryptedMessage, keys.privateKey));

        System.out.println(keys.publicKey);
        System.out.println();
        System.out.println(keys.privateKey);
    }
}