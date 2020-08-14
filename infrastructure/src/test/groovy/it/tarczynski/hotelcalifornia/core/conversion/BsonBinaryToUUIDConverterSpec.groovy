package it.tarczynski.hotelcalifornia.core.conversion

import java.nio.ByteBuffer
import org.bson.types.Binary
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair
import spock.lang.Specification
import spock.lang.Subject

@Subject(BsonBinaryToUUIDConverter)
class BsonBinaryToUUIDConverterSpec extends Specification {

    private static final TypeDescriptor UUID_TD = TypeDescriptor.forObject(UUID.randomUUID())
    private static final TypeDescriptor BINARY_TD = TypeDescriptor.forObject(new Binary("".bytes))

    private BsonBinaryToUUIDConverter converter = new BsonBinaryToUUIDConverter()

    def 'should convert from BSON to UUID'() {
        given:
        UUID uuid = UUID.randomUUID()
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16])
        byteBuffer.putLong(uuid.getMostSignificantBits())
        byteBuffer.putLong(uuid.getLeastSignificantBits())

        and:
        Binary binary = new Binary(byteBuffer.array())

        when:
        UUID converted = converter.convert(binary, BINARY_TD, UUID_TD) as UUID

        then:
        converted == uuid
    }

    def 'should return null given null'() {
        expect:
        converter.convert(null, BINARY_TD, UUID_TD) == null
    }

    def 'should throw given unsupported type'() {
        when:
        converter.convert("not a binary", BINARY_TD, UUID_TD)

        then:
        thrown(IllegalArgumentException)
    }

    def 'should return a set with a single ConvertiblePair of Binary -> UUID'() {
        expect:
        converter.convertibleTypes == [new ConvertiblePair(Binary, UUID)].toSet()
    }
}
