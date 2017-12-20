package com.nullpointer.getbitcoin.logic.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Khaustov on 20.12.2017.
 */

public class Packet {
    private int protocolVersion;
    private PacketType packetType;
    private long sendTime;
    private String userUUID;
    private byte[] data;

    public Packet(int protocolVersion, PacketType packetType, long sendTime,
                  String userUUID, byte[] data) {
        this.protocolVersion = protocolVersion;
        this.packetType = packetType;
        this.sendTime = sendTime;
        this.userUUID = userUUID;
        this.data = data;
    }

    public byte[] getAsByteArray() {
        byte[] protocolVersionBytes = ByteBuffer.allocate(4).putInt(protocolVersion).array();
        byte[] packetTypeBytes = ByteBuffer.allocate(4).putInt(packetType.getValue()).array();
        byte[] timestampBytes = ByteBuffer.allocate(8).putLong(sendTime).array();
        byte[] userUUIDBytes = userUUID.getBytes();
        byte[] userUUIDBytesSize = ByteBuffer.allocate(4).putInt(userUUIDBytes.length).array();
        byte[] dataSize = ByteBuffer.allocate(4).putInt(data.length).array();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(protocolVersionBytes);
            byteArrayOutputStream.write(packetTypeBytes);
            byteArrayOutputStream.write(timestampBytes);
            byteArrayOutputStream.write(userUUIDBytesSize);
            byteArrayOutputStream.write(userUUIDBytes);
            byteArrayOutputStream.write(dataSize);
            byteArrayOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
