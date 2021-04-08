package dev.peertosir.littlemanager.repository.impl;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.model.Event;
import dev.peertosir.littlemanager.repository.abstraction.EventRepository;
import dev.peertosir.littlemanager.utils.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOEventRepository implements EventRepository<Event, Long> {

    private final String filesDir = System.getProperty("user.dir") + System.getProperty("file.separator") +
            "files" + System.getProperty("file.separator");

    public List<Event> getAllUserEvents(Long userId) {
        List<Event> returnValue = getAll().stream().filter(event -> event.getUserId().equals(userId))
                .collect(Collectors.toList());
        System.out.println(returnValue);
        return returnValue;
    }

    @Override
    public List<Event> getAll() {
        List<Event> returnValue = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filesDir + "events.txt"))) {
            returnValue = ((List<Event>)ois.readObject());
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("No events exists yet");
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("IO error occured");
        }
        return returnValue;
    }

    @Override
    public Event getById(Long aLong) throws NotFoundException {
        return null;
    }

    @Override
    public long create(Event event) {
        List<Event> events = getAll();
        event.setId(Helpers.calcEntityId(events, event));
        events.add(event);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "events.txt")))
        {
            oos.writeObject(events);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return event.getId();
    }

    @Override
    public void update(Event event, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
