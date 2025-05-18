/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Event {

    private Long id;
    private int eventDateTime;
    private EventState eventState;
    private Long applianceId;
    private Long personId;
    private Long roomId;
    private Long eventId;

    public Event(Integer eventDateTime, Long applianceId,Long personId, Long roomId) {
        this.eventDateTime = eventDateTime;
        this.applianceId = applianceId;
        this.personId = personId;
        this.eventState = EventState.DEFAULT;
        this.eventId = 0L;
        this.roomId = roomId;
    }

}
