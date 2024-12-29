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
            System.arraycopy(array, 0, resultArray, index, array.length);
            index += array.length;
        }

        return resultArray;
    }

    public static String bytesToString(byte[] bytes)
    {
        StringBuilder str = new StringBuilder();

        for (byte b: bytes) {
            int i = b & 0xFF;
            str.append(String.format("%4s-%4s",
                    Integer.toBinaryString(i >> 4),
                    Integer.toBinaryString(i & 0xF)).replace(' ', '0')).append('\n');
        }

        return str.toString();
    }
    public static String bytesToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();

        for (var b: bytes)
        {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}
