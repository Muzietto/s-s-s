package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.io.PrintStream;
import java.util.Map;

public class SimulationFileOutput extends SimulationOutput {

    public SimulationFileOutput(PrintStream aStream) {
        super(aStream);
    }

    @Override
    public void output(StockMarket market, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks) {

    }
}
