/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.cvut.fel.NSS_event_service.entities.Event;
import cz.cvut.fel.NSS_event_service.services.EventService;

@RestController
@RequestMapping("")
public class EventController implements EventControllerInterface {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.createEvent(event);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/rooms/{roomId}/events")
    public List<Event> getAllEventsByRoomId(@PathVariable Long roomId) {
        return eventService.getAllEventsByRoomId(roomId);
    }

    @GetMapping("/appliances/{applianceId}/events")
    public List<Event> getAllEventsByApplianceId(@PathVariable Long applianceId) {
        return eventService.getAllEventsByApplianceId(applianceId);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if(event == null){
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(event);
        }
    }

    @PutMapping("/events")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<Event> resolveEvent(@PathVariable Long eventId) {
        Optional<Event> updatedEvent = eventService.resolveEvent(eventId);
        return ResponseEntity.ok(updatedEvent.get());
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}
