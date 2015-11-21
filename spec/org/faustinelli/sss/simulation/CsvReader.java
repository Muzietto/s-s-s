package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;
import org.faustinelli.sss.model.Trade;
import org.faustinelli.sss.util.Amount;

import java.io.*;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
public class CsvReader {

    private PrintStream output;

    public CsvReader(PrintStream anOutput) {
        output = anOutput;
    }

    public void run(StockMarket gbce, Simulation.RandomizedTradingClock clock, Map<String, Stock> stocks, String csvFile) {

        URL url = getClass().getResource(csvFile);
        File file = new File(url.getPath());

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ZonedDateTime nextCalculationTime = clock.lastTick().plusMinutes(new Long(20));

        try {
            br = new BufferedReader(new FileReader(file));


            while ((line = br.readLine()) != null) {

                // using comma as separator
                String[] tradeArray = line.split(cvsSplitBy);

                String symbol = tradeArray[0];
                String indicator = tradeArray[1];
                Integer price = Integer.parseInt(tradeArray[2]);
                Integer qty = Integer.parseInt(tradeArray[3]);

                Stock currentStock = stocks.get(symbol);
                Trade.Indicator tradingIndicator
                        = (indicator == "BUY") ? Trade.Indicator.BUY : Trade.Indicator.SELL;
                Amount tradingPrice = Amount.instance(price);

                Trade trade = gbce.trade(currentStock, tradingIndicator, tradingPrice, qty);
                //output.println(trade);

                if (clock.lastTick().compareTo(nextCalculationTime) > 0) {

                    output.println("*******************************************************");
                    output.println("* Time is: " + clock.lastTick() + "   *");
                    for (Stock stock : stocks.values()) {
                        output.println("* -------------- Stock " + stock.toString() + " ----------------- *");
                        output.println("*                   ticker price: " + gbce.tickerPrice(stock, clock.lastTick()) + "            *");
                        output.println("*                 dividend yield: " + gbce.dividendYield(stock, clock.lastTick()) + "                *");
                        output.println("*                      P/E ratio: " + gbce.peRatio(stock, clock.lastTick()) + "                   *");
                    }
                    output.println("* --------------------------------------------------- *");
                    output.println("*               GBCE all shares index: " + gbce.gbceAllSharesIndex(clock.lastTick()) + "             *");
                    output.println("******************************************************");

                    nextCalculationTime = clock.lastTick().plusMinutes(new Long(20));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        output.println("Done");
    }
}
