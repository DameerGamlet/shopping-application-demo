package com.product.demo.converver;

import org.bson.types.Binary;
import org.springframework.core.convert.converter.Converter;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDToBinaryConverter implements Converter<UUID, Binary> {
    @Override
    public Binary convert(UUID source) {
        final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(source.getMostSignificantBits());
        buffer.putLong(source.getLeastSignificantBits());
        return new Binary(buffer.array());
    }
}
