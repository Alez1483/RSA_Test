package AsnEncoding;

public class AsnNull extends AsnElement
{
    public AsnNull()
    {
        super(AsnTag.NULL, new byte[0]);
    }
}
