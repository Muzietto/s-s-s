package org.faustinelli.sss.simulation;

import junit.framework.TestCase;

import java.time.ZonedDateTime;

public class RandomizedTradingClockTest extends TestCase {

    private Simulation.RandomizedTradingClock clock = null;

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

    public void testAccessInnerVariable() throws Exception {
        ZonedDateTime firstTick = clock.time();
        ZonedDateTime verifyFirstTick = clock.lastTick();
        assertEquals(verifyFirstTick, firstTick);

        ZonedDateTime secondTick = clock.time();
        ZonedDateTime verifySecondTick = clock.lastTick();
        assertEquals(verifySecondTick, secondTick);
    }
}