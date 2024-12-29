package AsnEncoding;

public class AsnNull extends AsnElement
{
    private static AsnNull instance;

    public static AsnNull getInstance()
    {
        if (instance == null)
        {
            instance = new AsnNull();
        }
        return instance;
    }

    private AsnNull()
    {
        super(AsnTag.NULL, new byte[0]);
    }
}
