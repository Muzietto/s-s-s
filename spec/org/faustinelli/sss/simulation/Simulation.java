package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;
import org.faustinelli.sss.model.TradingClock;
import org.faustinelli.sss.util.Amount;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Simulation {

    public static void main(String[] args) {

        StockMarket gbce = StockMarket.GBCE();

        Map<String, Stock> stocks = new HashMap<String, Stock>();
        stocks.put("ALE", Stock.common("ALE", Amount.instance(60)));
        stocks.put("GIN", Stock.preferred("GIN", Amount.instance(100), new Integer(2)));
        stocks.put("JOE", Stock.common("JOE", Amount.instance(250)));
        stocks.put("POP", Stock.common("POP", Amount.instance(100)));
        stocks.put("PRE", Stock.preferred("PRE", Amount.instance(80), new Integer(3)));
        stocks.put("TEA", Stock.common("TEA", Amount.instance(100)));

        new CsvReader().run(gbce, stocks, "s_s_s_01_TRADES.csv");
    }

    public static class RandomizedTradingClock implements TradingClock {
        ZonedDateTime lastTick;
        Double stdDeviation;

        public RandomizedTradingClock(ZonedDateTime start, Double deviation) {
            lastTick = start;
            stdDeviation = deviation;
        }

        @Override
        public ZonedDateTime time() {
            double random = Math.random();
            Integer spreadSeconds = (int) Math.round(random *stdDeviation);

            lastTick = lastTick.plusSeconds(spreadSeconds);
            return ZonedDateTime.parse(lastTick.toString());
        }
    }
}
