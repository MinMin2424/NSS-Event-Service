/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_event_service.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cz.cvut.fel.NSS_event_service.entities.Event;
import cz.cvut.fel.NSS_event_service.entities.EventState;

@Service
public class EventService implements EventServiceInterface {
    private final List<Event> events;
    public final AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    private KafkaTemplate<String, String> kafka;

    @Autowired
    public EventService( List<Event> events) {
        this.events = events;
    }

    public Event createEvent(Event event) {
        Long eventId = idGenerator.getAndIncrement();
        event.setId(eventId);
        events.add(event);
        kafka.send("log.created", String.format("Creating event with id: %d in room: %d and appliance: %d", event.getId(), event.getRoomId(), event.getApplianceId()));
        return event;
    }

    public List<Event> getAllEvents() {
        return events;
    }

    @Cacheable("events")
    public Event getEventById(Long id) {
        return events.stream()
                .filter(event -> Objects.equals(event.getId(), id))
                .findFirst().get();
    }

    public List<Event> getAllEventsByApplianceId(Long applianceId) {
        return events.stream()
                .filter(event -> Objects.equals(event.getApplianceId(), applianceId))
                .collect(Collectors.toList());
    }

    public List<Event> getAllEventsByRoomId(Long roomId) {
        return events.stream()
                .filter(event -> Objects.equals(event.getRoomId(), roomId))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "events", key = "#eventId")
    public Optional<Event> resolveEvent(Long eventId) {
        Optional<Event> resolvedEvent = events.stream()
                .filter(event -> Objects.equals(event.getId(),eventId))
                .findFirst();
        resolvedEvent.get().setEventState(EventState.IS_RESOLVED);
        kafka.send("log.created", String.format("Resolving event with id: %d in room: %d and appliance: %d", resolvedEvent.get().getId(), resolvedEvent.get().getRoomId(), resolvedEvent.get().getApplianceId()));
        return resolvedEvent;
    }

    @CachePut(value = "events", key = "#updatedEvent.id")
    public Event updateEvent(Event updatedEvent) {
        Event event = getEventById(updatedEvent.getEventId());
        if (event != null) {
            event.setEventDateTime(updatedEvent.getEventDateTime());
            event.setApplianceId(updatedEvent.getApplianceId());
            event.setPersonId(updatedEvent.getPersonId());
            event.setEventState(updatedEvent.getEventState());
            event.setRoomId(updatedEvent.getRoomId());
        } else {
            throw new RuntimeException("Event not found");
        }
        return event;
    }

    @CacheEvict(value = "events", key = "#id")
    public void deleteEvent(Long id) {
        events.removeIf(event -> event.getId().equals(id));
    }


}
