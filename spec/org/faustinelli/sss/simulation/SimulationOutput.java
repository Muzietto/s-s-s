package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.util.Map;

public interface SimulationOutput {
    void output(StockMarket market, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks);

    void close();
}
