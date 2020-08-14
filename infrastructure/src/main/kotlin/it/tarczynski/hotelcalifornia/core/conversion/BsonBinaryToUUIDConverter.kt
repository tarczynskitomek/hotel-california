package it.tarczynski.hotelcalifornia.core.conversion

import org.bson.types.Binary
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.util.*

@Component
class BsonBinaryToUUIDConverter : GenericConverter {

    override fun getConvertibleTypes(): MutableSet<GenericConverter.ConvertiblePair>? {
        return mutableSetOf(GenericConverter.ConvertiblePair(Binary::class.java, UUID::class.java))
    }

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        return if (isNullOrUUID(source, sourceType)) {
            source
        } else {
            require(source is Binary) { "Source object is not of type [%s]".format(Binary::class.java.simpleName) }
            toUUID(source)
        }
    }

    private fun toUUID(source: Binary): UUID {
        val byteBuffer = ByteBuffer.wrap(source.data)
        val mostSignificant = byteBuffer.long
        val leastSignificant = byteBuffer.long
        return UUID(mostSignificant, leastSignificant)
    }

    private fun isNullOrUUID(source: Any?, sourceType: TypeDescriptor) =
            source == null || sourceType.type == UUID::class.java
}
