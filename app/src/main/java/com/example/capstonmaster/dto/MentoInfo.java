package com.example.capstonmaster.dto;

import java.io.Serializable;

public class MentoInfo implements Serializable {
    private String etc;
    private String mento;
    private String metting;
    private String target;

    public MentoInfo(String etc, String mento, String metting, String target) {
        this.etc = etc;
        this.mento = mento;
        this.metting = metting;
        this.target = target;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getMento() {
        return mento;
    }

    public void setMento(String mento) {
        this.mento = mento;
    }

    public String getMetting() {
        return metting;
    }

    public void setMetting(String metting) {
        this.metting = metting;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
