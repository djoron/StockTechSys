package com.evdosoft.stocktechsys.events;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.dao.ChartDao;
import com.evdosoft.stocktechsys.models.Chart;

@Component
public class PriceHistoryFetchedListener implements ApplicationListener<PriceHistoryFetchedEvent> {

    @Autowired
    private ChartDao chartDao;
    
    @Override
    public void onApplicationEvent(PriceHistoryFetchedEvent event) {
	
	Map<String, List<Chart>> chartMap = event.getChartMap();
	
	saveChartMap(chartMap);
    }

    private void saveChartMap(Map<String, List<Chart>> chartMap) {
	chartDao.saveMultipleChartListToDb(chartMap);
    }

}
