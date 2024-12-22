package AsnEncoding;

public enum AsnTag
{
    INTEGER (0x02),
    NULL (0x05),
    OBJECT_IDENTIFIER (0x06),
    SEQUENCE (0x30);

    private byte tag;

    private AsnTag(int a)
    {
        tag = (byte)a;
    }

    public byte getTagByte()
    {
        return tag;
    }
}
