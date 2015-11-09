package org.faustinelli.sss.model;

import java.time.ZonedDateTime;

public interface TradingClock {
    ZonedDateTime time();
}
