package dev.peertosir.littlemanager.repository.abstraction;

import dev.peertosir.littlemanager.model.Event;

import java.util.List;

public interface EventRepository<Event, Long> extends GenericRepository<Event, Long> {
    List<Event> getAllUserEvents(Long userId);
}