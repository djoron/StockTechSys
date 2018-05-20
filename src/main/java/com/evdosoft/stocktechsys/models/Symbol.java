/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.evdosoft.stocktechsys.web.resource.SymbolResource;

import lombok.Data;

/**
 *
 * @author atlantis
 */
@Data
public class Symbol {

 // xxxx a verifier les types. DOuble pour entiers, Big decimal pour float
    private String symbol;
    private String name;
    private LocalDate date;
    private boolean isEnabled;
    private String type;
    private String iexId;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symbol other = (Symbol) obj;
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        return true;
    }

    public Symbol() {
	
    }
    
    public Symbol(SymbolResource resource) {
	this.iexId = resource.getIexId();
	this.isEnabled = Boolean.parseBoolean(resource.getIsEnabled());
	this.name = resource.getName();
	this.symbol = resource.getSymbol();
	this.type = resource.getType();
	this.date = LocalDate.parse(resource.getDate(), DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
    
}


/* Ajoute Long Id
override equals pour detecter doublons sur symbol
et hashcode --> va retourner symbol.hashcode - cle pour retrouver l<objet dans hashset


SymbolListFromDB
HashSet<Symbol> set=new HashSet<>();  

set.addall(SymbolListFromDb)
set.addall(SymbolUpdated).

List<Symbol> newSymbols = set
    .stream()
    .filter(s -> s.getId() == null)
    .collect(Collectors.toList());

newSymbols
    .stream()
    .forEach(s -> saveToSymbolDB(s));

update company DB
    



  vieille liste . set.addall(Liste de updated "Ravi");  





*/