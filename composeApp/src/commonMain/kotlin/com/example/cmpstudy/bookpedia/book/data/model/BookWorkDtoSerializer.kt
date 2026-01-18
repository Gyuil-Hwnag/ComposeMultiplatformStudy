package com.example.cmpstudy.bookpedia.book.data.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object BookWorkDtoSerializer : KSerializer<BookWorkDto> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        BookWorkDto::class.simpleName!!
    ) {
        element<String?>("description")
    }

    override fun deserialize(decoder: Decoder): BookWorkDto = decoder.decodeStructure(descriptor) {
        var description: String? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> {
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This decoder only works with JSON."
                    )
                    val element = jsonDecoder.decodeJsonElement()
                    description = when (element) {
                        is JsonObject -> {
                            decoder.json.decodeFromJsonElement(
                                element = element,
                                deserializer = DescriptionDto.serializer()
                            ).value
                        }

                        is JsonPrimitive if element.isString -> {
                            element.content
                        }

                        else -> null
                    }
                }

                CompositeDecoder.DECODE_DONE -> break
                else -> throw SerializationException("Unexpected index $index")
            }
        }

        return@decodeStructure BookWorkDto(description)
    }

    override fun serialize(encoder: Encoder, value: BookWorkDto) = encoder.encodeStructure(descriptor) {
        value.description?.let {
            encodeStringElement(descriptor, 0, it)
        }
    }
}
