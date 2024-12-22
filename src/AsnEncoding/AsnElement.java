package AsnEncoding;

public class AsnElement
{
    protected AsnTag tag;
    protected byte[] data;

    public AsnElement(AsnTag tag, byte[] data)
    {
        this.data = data;
        this.tag = tag;
    }

    public byte[] getBytes()
    {
        byte[] lengthBytes = getLengthBytes(data.length);

        return ByteArrayUtil.ConcatenateArrays( new byte[]{ tag.getTagByte() }, lengthBytes, data);
    }

    protected byte[] getLengthBytes(int length)
    {
        byte[] lengthBytes;

        if (length <= 0b0111_1111)
        {
            lengthBytes = new byte[]{ (byte)length };
        }
        else
        {
            int bytes = byteLength(length);
            lengthBytes = new byte[bytes + 1];
            lengthBytes[0] = (byte)(0b1000_0000 | bytes);

            for (int i = 4 - bytes, j = 1; i < 4; i++, j++)
            {
                lengthBytes[j] = getByte(length, i);
            }
        }

        return lengthBytes;
    }

    private int byteLength(int i)
    {
        if (i > 0xFFFFFF)
        {
            return 4;
        }
        else if (i > 0xFFFF)
        {
            return 3;
        }
        else if (i > 0xFF)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }
    protected byte getByte(int value, int index)
    {
        return (byte)(0xFF & (value >> 8 * (3 - index)));
    }
}
