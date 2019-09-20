package it.lorenzoboaro.awesomeproject.reactnative

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.databind.ObjectWriter
import java.io.IOException
import java.lang.reflect.Type

object JacksonUtils {

    fun writerForType(mapper: ObjectMapper, type: Type): ObjectWriter {
        val javaType = mapper.typeFactory.constructType(type)
        return mapper.writerFor(javaType)
    }

    fun readerForType(mapper: ObjectMapper, type: Type): ObjectReader {
        val javaType = mapper.typeFactory.constructType(type)
        return mapper.readerFor(javaType)
    }

    /**
     * Convenient way to read a JSON Array String into a List
     */
    fun <T> readJsonArray(objectMapper: ObjectMapper, value: String): List<T> {
        val reader = JacksonUtils.readerForType(objectMapper, List::class.java)

        try {
            return reader.readValue(value)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    /**
     * Convenient way to write a List into a JSON Array string
     */
    fun <T> writeJsonArray(objectMapper: ObjectMapper, value: List<T>): String {
        val writer = JacksonUtils.writerForType(objectMapper, List::class.java)
        try {
            return writer.writeValueAsString(value)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

    }
}