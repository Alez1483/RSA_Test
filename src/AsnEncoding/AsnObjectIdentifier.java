package AsnEncoding;

public class AsnObjectIdentifier extends AsnElement
{
    public AsnObjectIdentifier(String oid)
    {
        super(AsnTag.OBJECT_IDENTIFIER, null);

        String[] numberStrings = oid.split("\\.");
        int[] values = new int[numberStrings.length];

        for (int i = 0; i < numberStrings.length; i++)
        {
            values[i] = Integer.parseInt(numberStrings[i]);
        }

        
    }
}
