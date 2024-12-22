package AsnEncoding;

public class ByteArrayUtil
{
    public static byte[] ConcatenateArrays(byte[]... arrays)
    {
        int totalLength = 0;
        for (byte[] array: arrays)
        {
            totalLength += array.length;
        }

        byte[] resultArray = new byte[totalLength];

        int index = 0;

        for (byte[] array: arrays)
        {
            for (byte b: array)
            {
                resultArray[index] = b;
                index++;
            }
        }

        return resultArray;
    }
}
