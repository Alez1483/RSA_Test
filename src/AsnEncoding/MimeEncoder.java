package AsnEncoding;

import java.util.Base64;

public class MimeEncoder
{
    private static byte[] lineSeparatorBytes;

    static
    {
        String lineSeparator = System.lineSeparator();
        lineSeparatorBytes = new byte[lineSeparator.length()];

        for (int i = 0; i < lineSeparator.length(); i++)
        {
            lineSeparatorBytes[i] = (byte)lineSeparator.charAt(i);
        }
    }

    public static String bytesToString(byte[] bytes, int lineLength)
    {
        Base64.Encoder encoder = Base64.getMimeEncoder(lineLength, lineSeparatorBytes);
        return encoder.encodeToString(bytes);
    }

    //lineLength defaults to 64
    public static String bytesToString(byte[] bytes)
    {
        return bytesToString(bytes, 64);
    }
}
