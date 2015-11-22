package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.io.PrintStream;
import java.util.Map;

public class SimulationFileOutput implements SimulationOutput {
    private PrintStream stream;

    public SimulationFileOutput(PrintStream aStream) {
        stream = aStream;
    }

    @Override
    public void output(StockMarket market, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks) {

    }

    @Override
    public void close() {
        try {
            stream.println("Done");
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
