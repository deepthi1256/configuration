package com.config.configuration.connections.messageQueue.pojo;

import java.time.OffsetDateTime;

public class Message {

    private String body;

    private long sequenceNumber;

    private OffsetDateTime scheduleTimeStamp;

    public Message(String body, long sequenceNumber, OffsetDateTime scheduleTimeStamp) {
        this.body = body;
        this.sequenceNumber = sequenceNumber;
        this.scheduleTimeStamp = scheduleTimeStamp;
    }

    public Message(String body, long sequenceNumber) {
        this.body = body;
        this.sequenceNumber = sequenceNumber;
    }

    public Message(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public OffsetDateTime getScheduleTimeStamp() {
        return scheduleTimeStamp;
    }

    public void setScheduleTimeStamp(OffsetDateTime scheduleTimeStamp) {
        this.scheduleTimeStamp = scheduleTimeStamp;
    }
}
