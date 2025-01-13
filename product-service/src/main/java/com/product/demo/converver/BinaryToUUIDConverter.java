package com.product.demo.converver;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;

import java.nio.ByteBuffer;
import java.util.UUID;

public class BinaryToUUIDConverter implements Converter<Binary, UUID> {
    @Override
    public UUID convert(Binary source) {
        final ByteBuffer buffer = ByteBuffer.wrap(source.getData());
        long mostSigBits = buffer.getLong();
        long leastSigBits = buffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }
}
