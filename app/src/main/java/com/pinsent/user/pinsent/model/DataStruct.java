package com.pinsent.user.pinsent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cheng on 2017/9/5.
 */

public class DataStruct implements Serializable{
    private String brand;
    private String containerPosition;
    private String containerName;
    private int percent;
    public void setBrand(String brand){
        this.brand=brand;
    }
    public void setContainerPosition(String containerPosition){
        this.containerPosition=containerPosition;
    }
    public void setContainerName(String containerName){
        this.containerName=containerName;
    }
    public void setPercent(int percent){
        this.percent=percent;
    }
    public String getBrand(){
        return brand;
    }
    public String getContainerPosition(){
        return containerPosition;
    }
    public String getContainerName(){
        return containerName;
    }
    public int getPercent(){
        return percent;
    }
}
