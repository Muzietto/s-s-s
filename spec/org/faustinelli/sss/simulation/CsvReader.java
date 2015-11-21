package org.faustinelli.sss.simulation;

import org.faustinelli.sss.model.Stock;
import org.faustinelli.sss.model.StockMarket;
import org.faustinelli.sss.model.Trade;
import org.faustinelli.sss.util.Amount;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
public class CsvReader {

    public void run(StockMarket gbce, Map<String, Stock> stocks, String csvFile) {

        URL url = getClass().getResource(csvFile);
        File file = new File(url.getPath());

        BufferedReader br = null;
        int counter = 0;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] trade = line.split(cvsSplitBy);

                String symbol = trade[0];
                String indicator = trade[1];
                Integer price = Integer.parseInt(trade[2]);
                Integer qty = Integer.parseInt(trade[3]);

                System.out.println("Trade " + ++counter + " [" + ""
                        + "symbol="      + symbol
                        + " , indicator=" + indicator
                        + " , price="     + price.toString()
                        + " , qty="       + qty.toString()
                        + "]");

                Stock stock = stocks.get(symbol);
                Trade.Indicator tradingIndicator
                        = (indicator == "BUY") ? Trade.Indicator.BUY : Trade.Indicator.SELL;
                Amount tradingPrice = Amount.instance(price);

                gbce.trade(stock, tradingIndicator, tradingPrice, qty);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

        System.out.println("Done");

    }
}
