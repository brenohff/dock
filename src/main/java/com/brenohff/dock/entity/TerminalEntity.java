package com.brenohff.dock.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class TerminalEntity {

    @Id
    private int logic;

    private String serial;
    private String model;
    private int sam;
    private String ptid;
    private int plat;
    private String version;
    private int mxr;
    private int mxf;

    @SerializedName(value = "VERFM")
    @JsonProperty("VERFM")
    private String verfm;
}
