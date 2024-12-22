package AsnEncoding;

public class AsnSequence extends AsnElement
{
    public AsnSequence(AsnElement... elements)
    {
        super(AsnTag.SEQUENCE, null);

        byte[] data = new byte[0];

        for (AsnElement element : elements)
        {
            data = ByteArrayUtil.ConcatenateArrays(data, element.getBytes());
        }
        this.data = data;
    }
}
