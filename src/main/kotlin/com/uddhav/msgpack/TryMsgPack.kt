package com.uddhav.msgpack

import org.msgpack.core.* // ktlint-disable no-wildcard-imports
import org.msgpack.value.ImmutableValue
import org.msgpack.value.MapValue
import org.msgpack.value.ValueFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.RuntimeException
import java.time.Instant
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class TryMsgPack {

    private fun unpack(unpacker: MessageUnpacker) {

        val mapValues = sequence<MapValue> {
            while (unpacker.hasNext()) {
                try {
                    yield(unpacker.unpackValue().asMapValue())
                } catch (e: MessagePackException) {
                    throw RuntimeException(e)
                }
            }
        }.asIterable()

        mapValues.forEach { v -> println(v.entrySet()) }
        unpacker.close()
    }

    private fun pack(): MessageBufferPacker {
        val packer = MessagePack.newDefaultBufferPacker()
        packer.packValue(payloadMap())
        packer.close()
        return packer
    }

    private fun payloadMap(): MapValue {
        val entries = listOf(
            Pair(ValueFactory.newString("fname"), ValueFactory.newString("Uddhav")),
            Pair(ValueFactory.newString("lname"), ValueFactory.newString("Arote")),
            Pair(ValueFactory.newString("source_time"), ValueFactory.newTimestamp(System.currentTimeMillis()))
        )
        return ValueFactory.newMap(
            *entries
                .plus(ValueFactory.newString("time") to ValueFactory.newInteger(Instant.now().epochSecond))
                .flatMap { it.toList<ImmutableValue?>() }.toTypedArray()
        ) as MapValue
    }

    fun packAsMsgpackFile(): File {
        val packer = pack()
        val tempFile = kotlin.io.path.createTempFile("create-msgpack-", ".msgpack.gz").toFile()
        val gos = GZIPOutputStream(FileOutputStream(tempFile))
        gos.write(packer.toByteArray())
        gos.close()
        return tempFile
    }

    fun readMsgPackFile(file: File) = unpack(MessagePack.newDefaultUnpacker(GZIPInputStream(FileInputStream(file))))
}

fun main(vararg args: String) {
    val obj = TryMsgPack()

    val file = obj.packAsMsgpackFile()
    println(file.path)
    obj.readMsgPackFile(file)
}
