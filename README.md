# s-s-s

Unit tests (JUnit 3) are to be found in:

 - `spec/org/faustinelli/sss/model`
 - `spec/org/faustinelli/sss/util`


Two simulations are runnable in:

 - `spec/org/faustinelli/sss/simulation/Simulation.java` (short simulation - 10.000 trades - approx 1 trade/3.5 sec)
 - `spec/org/faustinelli/sss/simulation/LongSimulation.java` (long simulation - 640.000 trades - approx 1 trade/0.4 sec)

Both simulations feature 6 stocks (4 common, 2 preferred) and present the following data calculated every 20 minutes:

 - stock prices
 - dividend yields
 - P/E ratio's

Simulations may output to:

 - console (as configured now)
 - CSV file (comment/uncomment lines as specified inside the .java files)

Input data for the simulations are provided in:

 - `spec/org/faustinelli/simulation/s_s_s_01_TRADES.csv` (short simulation)
 - `spec/org/faustinelli/simulation/s_s_s_02_TRADES.csv` (long simulation)

CSV outputs produced by the two simulations are to be found in:

 - `spec/org/faustinelli/simulation/s_s_s_01_TRADES.out.csv` (short simulation - 28 output datasets)
 - `spec/org/faustinelli/simulation/s_s_s_02_TRADES.out.csv` (long simulation - 200 output datasets)

Excel spreadsheet `doc/data/s_s_s_01.xls` provides charts giving insight in the following simulation data:

 - trades ordered in time (only for short simulation)
 - short simulation results
 - long simulation results

__marco_faustinelli@yahoo.com__

__Released under GPL-2.0 License__
