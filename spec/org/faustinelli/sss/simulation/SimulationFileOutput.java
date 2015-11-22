package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationFileOutput extends SimulationOutput {
    private List<String> marketData = new ArrayList<String>();

    public SimulationFileOutput(PrintStream aStream) {
        super(aStream);
    }

    @Override
    public void output(StockMarket market, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks) {

        marketData.clear();
        //marketData.add(String.valueOf(clock.lastTick().toEpochSecond()));

        for (Stock stock : stocks.values()) {
            marketData.add(String.valueOf(market.tickerPrice(stock, clock.lastTick()).value()));
        }

        marketData.add(String.valueOf(market.gbceAllSharesIndex(clock.lastTick())));

        stream.println(String.join(",", marketData));
    }
}
