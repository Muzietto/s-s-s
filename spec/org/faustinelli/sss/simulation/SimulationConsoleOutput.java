package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;

import java.io.PrintStream;
import java.util.Map;

public class SimulationConsoleOutput extends SimulationOutput {

    public SimulationConsoleOutput(PrintStream aStream) {
        super(aStream);
    }

    @Override
    public void output(StockMarket gbce, Simulation.RandomizedTradingClock clock, Map<String,
            Stock> stocks) {

        stream.println("*******************************************************");
        stream.println("* Time is: " + clock.lastTick() + "   *");
        for (Stock stock : stocks.values()) {
            stream.println("* -------------- Stock " + stock.toString() + " ----------------- *");
            stream.println("*                   ticker price: " + gbce.tickerPrice(stock, clock.lastTick()) + "            *");
            stream.println("*                 dividend yield: " + gbce.dividendYield(stock, clock.lastTick()) + "                *");
            stream.println("*                      P/E ratio: " + gbce.peRatio(stock, clock.lastTick()) + "                   *");
        }
        stream.println("* --------------------------------------------------- *");
        stream.println("*               GBCE all shares index: " + gbce.gbceAllSharesIndex(clock.lastTick()) + "             *");
        stream.println("******************************************************");

    }
}
