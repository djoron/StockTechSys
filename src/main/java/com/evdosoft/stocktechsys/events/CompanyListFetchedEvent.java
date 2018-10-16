package com.evdosoft.stocktechsys.events;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.evdosoft.stocktechsys.models.Company;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompanyListFetchedEvent extends ApplicationEvent {
        
    private static final long serialVersionUID = 1L;
    private List<Company> companyList;

    public CompanyListFetchedEvent(Object source, List<Company> companyList) {
	super(source);
	this.companyList = companyList;
    }

}
