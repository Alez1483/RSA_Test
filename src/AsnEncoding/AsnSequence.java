package AsnEncoding;

public class AsnSequence extends AsnElement
{
    public AsnSequence(AsnElement... elements)
    {
        super(AsnTag.SEQUENCE, null);

        byte[][] allBytes = new byte[elements.length][];

        for (int index = 0; index < elements.length; index++)
        {
            allBytes[index] = elements[index].getBytes();
        }
        
        this.data = ByteArrayUtil.ConcatenateArrays(allBytes);
    }
}
