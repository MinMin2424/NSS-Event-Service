/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.controllers;

import cz.cvut.fel.NSS_event_service.entities.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventControllerInterface {
    ResponseEntity<Event> createEvent(Event event);
    List<Event> getAllEvents();
    List<Event> getAllEventsByRoomId(Long roomId);
    List<Event> getAllEventsByApplianceId(Long applianceId);
    ResponseEntity<Event> getEventById(Long id);
    ResponseEntity<Event> updateEvent(Event event);
    ResponseEntity<Event> resolveEvent(Long eventId);
    ResponseEntity<String> deleteEvent(Long id);
}

