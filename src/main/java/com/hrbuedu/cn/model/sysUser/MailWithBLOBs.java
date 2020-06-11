package com.hrbuedu.cn.model.sysUser;

public class MailWithBLOBs extends Mail {
    private String text;

    private byte[] picture;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}