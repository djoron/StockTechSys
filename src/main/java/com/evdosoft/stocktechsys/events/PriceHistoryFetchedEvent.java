package com.evdosoft.stocktechsys.events;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;

import com.evdosoft.stocktechsys.models.Chart;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PriceHistoryFetchedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private Map<String, List<Chart>> chartMap;
    
    public PriceHistoryFetchedEvent(Object source, Map<String, List<Chart>> chartMap) {
	super(source);
	this.chartMap = chartMap;
    }
}
