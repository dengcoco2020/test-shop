package com.gofluent.testshop.model;

import java.sql.Timestamp;
import java.util.Date;

public class JsonObject {

    public JsonObject(int status, String message, int result_count, Object payload) {
        this.status = status;
        this.message = message;
        this.result_count = result_count;
        this.payload = payload;
    }

    Date date = new Date();

    private Timestamp timestamp = new Timestamp(date.getTime());
    private int status;
    private String message;
    private int result_count;
    private Object payload;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult_count() {
        return result_count;
    }

    public void setResult_count(int result_count) {
        this.result_count = result_count;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
