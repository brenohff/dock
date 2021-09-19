package com.brenohff.dock.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TerminalEntity {

    @Id
    private int logic;

    private int sam;
    private int plat;
    private int mxr;
    private int mxf;

    private String serial;
    private String model;
    private String ptid;
    private String version;

    @SerializedName(value = "VERFM")
    @JsonProperty("VERFM")
    private String verfm;
}
