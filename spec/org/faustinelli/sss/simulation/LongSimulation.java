package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;
import org.faustinelli.sss.model.Trader;
import org.faustinelli.sss.util.Amount;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class LongSimulation {

    public static void main(String[] args) throws  Exception {

        ZonedDateTime januarySixth2010nineAM = ZonedDateTime.of(2010, 1, 6, 9, 0, 0, 0, ZoneId.systemDefault());
        // max distance between trades 0.8 secs
        Simulation.RandomizedTradingClock clock = new Simulation.RandomizedTradingClock(januarySixth2010nineAM, new Double(0.8));
        StockMarket gbce = StockMarket.instance(Trader.instance(clock));

        Map<String, Stock> stocks = new HashMap<String, Stock>();
        stocks.put("ALE", Stock.common("ALE", Amount.instance(60)));
        stocks.put("GIN", Stock.preferred("GIN", Amount.instance(10), new Integer(2)));
        stocks.put("JOE", Stock.common("JOE", Amount.instance(250)));
        stocks.put("POP", Stock.common("POP", Amount.instance(100)));
        stocks.put("PRE", Stock.preferred("PRE", Amount.instance(15), new Integer(2)));
        stocks.put("TEA", Stock.common("TEA", Amount.instance(100)));

        gbce.recordDividend(stocks.get("ALE"), Amount.instance(15));
        gbce.recordDividend(stocks.get("GIN"), Amount.instance(12));
        gbce.recordDividend(stocks.get("JOE"), Amount.instance(3));
        gbce.recordDividend(stocks.get("POP"), Amount.instance(9));
        gbce.recordDividend(stocks.get("PRE"), Amount.instance(10));
        gbce.recordDividend(stocks.get("TEA"), Amount.instance(3));

        new CsvReader(new SimulationConsoleOutput(System.out)).run(gbce, clock, stocks, "s_s_s_02_TRADES.csv");
    }
}
