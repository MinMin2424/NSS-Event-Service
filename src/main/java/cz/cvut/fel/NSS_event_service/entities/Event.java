/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.entities;

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

    public Event() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(int eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    public Long getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(Long applianceId) {
        this.applianceId = applianceId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
