package org.faustinelli.sss.simulation;

import junit.framework.TestCase;
import org.faustinelli.sss.model.TradingClock;

import java.time.ZonedDateTime;

public class RandomizedTradingClockTest extends TestCase {

    private TradingClock clock = null;

    @Override
    protected void setUp() throws Exception {
        clock = new Simulation.RandomizedTradingClock(ZonedDateTime.now(), new Double(5.0));
    }

    public void testVerifyStdDeviation() throws Exception {
        ZonedDateTime firstTick = clock.time();
        ZonedDateTime secondTick = clock.time();
        ZonedDateTime thirdTick = clock.time();
        ZonedDateTime fourthTick = clock.time();

        assertTrue(firstTick.compareTo(secondTick) <= 0);
        assertTrue(secondTick.compareTo(thirdTick) <= 0);
        assertTrue(thirdTick.compareTo(fourthTick) <= 0);
    }
}