package com.evdosoft.stocktechsys.service.async;

import java.util.List;

public interface PriceHistoryServiceAsync {

    void prepareAndDownloadPriceHistory();
    void fetchAndSavePriceHistoryList(List<String> symbols, int period);
}
