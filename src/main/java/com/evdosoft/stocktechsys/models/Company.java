/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 *
 * @author atlantis
 */
@Data
public class Company {

    private Long id;
    private String symbol;
    private String companyName;
    private String exchange;
    private String industry;
    private String website;
    private String description;
    @JsonProperty("CEO")
    private String ceo;
    private String issueType;
    private String sector;   
    
    
}