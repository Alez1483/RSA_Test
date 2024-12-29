package AsnEncoding;

import java.math.BigInteger;

public class AsnInteger extends AsnElement
{
    public AsnInteger(BigInteger integer)
    {
        super(AsnTag.INTEGER, integer.toByteArray());
    }
}
