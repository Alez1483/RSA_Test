package AsnEncoding;

import java.util.ArrayList;
import java.util.List;

public class AsnObjectIdentifier extends AsnElement
{
    public AsnObjectIdentifier(String oid)
    {
        super(AsnTag.OBJECT_IDENTIFIER, null);

        String[] numberStrings = oid.split("\\.");
        long[] values = new long[numberStrings.length]; //long should be plenty for most OIDs

        for (int i = 0; i < numberStrings.length; i++)
        {
            values[i] = Long.parseUnsignedLong(numberStrings[i]);
        }

        //calculating the length beforehand is not worth it so use list instead
        List<Byte> bytes = new ArrayList<>();

        if (values.length < 2) {
            throw new IllegalArgumentException("OID must include at least 2 nodes");
        }

        //first two nodes encoded to a byte
        long firstByte = values[0] * 40 + values[1];
        if (firstByte > 0xFF)
        {
            throw new IllegalArgumentException("2 first nodes of OID are encoded to byte so the maximum ");
        }
        bytes.add((byte)firstByte);

        for (int i = 2; i < values.length; i++)
        {
            if (values[i] < 0b1000_0000)
            {
                bytes.add((byte)values[i]);
            }
            else
            {
                addMultiByteNode(bytes, values[i]);
            }
        }

        //to primitive array
        byte[] bytesArray = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++)
        {
            bytesArray[i] = bytes.get(i);
        }
        this.data = bytesArray;
    }
    private void addMultiByteNode(List<Byte> arrayToAdd, long nodeValue)
    {
        List<Byte> result = new ArrayList<>(); //reversed order
        result.add((byte)(0b0111_1111 & nodeValue)); //last always 0xxxxxxx, rest starting with 1

        int shiftCount = 7;
        long shiftedNode;
        while ((shiftedNode = nodeValue >>> shiftCount) != 0)
        {
            result.add((byte)(0b1000_0000 | shiftedNode));
            shiftCount += 7;
        }

        for (int i = result.size() - 1; i >= 0; i--)
        {
            arrayToAdd.add(result.get(i));
        }
    }
}
