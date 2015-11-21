package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;
import org.faustinelli.sss.model.Trader;
import org.faustinelli.sss.model.TradingClock;
import org.faustinelli.sss.util.Amount;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Simulation {

    public static void main(String[] args) {


        ZonedDateTime januarySixth2010nineAM = ZonedDateTime.of(2010, 1, 6, 9, 0, 0, 0, ZoneId.systemDefault());
        RandomizedTradingClock clock = new RandomizedTradingClock(januarySixth2010nineAM, new Double(7.0));
        StockMarket gbce = StockMarket.instance(Trader.instance(clock));

        Map<String, Stock> stocks = new HashMap<String, Stock>();
        stocks.put("ALE", Stock.common("ALE", Amount.instance(60)));
        stocks.put("GIN", Stock.preferred("GIN", Amount.instance(10), new Integer(2)));
        stocks.put("JOE", Stock.common("JOE", Amount.instance(250)));
        stocks.put("POP", Stock.common("POP", Amount.instance(100)));
        stocks.put("PRE", Stock.preferred("PRE", Amount.instance(15), new Integer(2)));
        stocks.put("TEA", Stock.common("TEA", Amount.instance(100)));

        gbce.recordDividend(stocks.get("ALE"),Amount.instance(15));
        gbce.recordDividend(stocks.get("GIN"),Amount.instance(12));
        gbce.recordDividend(stocks.get("JOE"),Amount.instance(3));
        gbce.recordDividend(stocks.get("POP"),Amount.instance(9));
        gbce.recordDividend(stocks.get("PRE"),Amount.instance(10));
        gbce.recordDividend(stocks.get("TEA"),Amount.instance(3));

        new CsvReader(System.out).run(gbce, clock, stocks, "s_s_s_01_TRADES.csv");
    }

    public static class RandomizedTradingClock implements TradingClock {
        private ZonedDateTime lastTick;
        private Double stdDeviation;

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

        public ZonedDateTime lastTick() {
            return ZonedDateTime.parse(lastTick.toString());
        }
    }
}
