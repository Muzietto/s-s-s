package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.io.PrintStream;
import java.util.Map;

public abstract class SimulationOutput {
    protected PrintStream stream;

    public SimulationOutput(PrintStream aStream) {
        stream = aStream;
    }

    public abstract void output(StockMarket market, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks);

    public void close() {
        try {
            stream.println("Done");
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}
