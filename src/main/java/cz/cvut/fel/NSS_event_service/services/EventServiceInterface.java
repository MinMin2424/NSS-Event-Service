/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.services;

import cz.cvut.fel.NSS_event_service.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventServiceInterface {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    List<Event> getAllEventsByApplianceId(Long applianceId);
    List<Event> getAllEventsByRoomId(Long roomId);
    Optional<Event> resolveEvent(Long eventId);
    Event updateEvent(Event updatedEvent);
    void deleteEvent(Long id);
}

