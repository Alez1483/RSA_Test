package AsnEncoding;

public enum AsnTag
{
    INTEGER (0x02),
    NULL (0x05),
    OBJECT_IDENTIFIER (0x06),
    BIT_STRING (0x03),
    SEQUENCE (0x30);

    private final byte tag;

    AsnTag(int a)
    {
        tag = (byte)a;
    }

    public byte getTagByte()
    {
        return tag;
    }
}
