package com.seals.camplanner.event;

import com.seals.camplanner.location.LocationTestUtils;
import com.seals.camplanner.event.models.Event;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class EventTestUtils {

    public static Event getSampleEvent() {
        Event event = new Event();
        Instant now = Instant.now();
        Timestamp starts = Timestamp.from(now);
        Timestamp ends = Timestamp.from(now.plus(2, ChronoUnit.DAYS));
        event.setEventDate(starts);
        event.setStarts(starts);
        event.setEnds(ends);
        event.setLocation(LocationTestUtils.getSampleLocation());
        return event;
    }
}
