package AsnEncoding;

public class AsnBitString extends AsnElement
{
    //no unused bytes supported for now
    public AsnBitString(byte[] data)
    {
        super(AsnTag.BIT_STRING, null);
        //can only assume all bits are used, could make another constructor with unused count as arg
        final byte unusedBits = 0;
        byte[] allData = new byte[data.length + 1]; //includes unused bytes as well as the data
        allData[0] = unusedBits;
        System.arraycopy(data, 0, allData, 1, data.length);
        this.data = allData;
    }
}
