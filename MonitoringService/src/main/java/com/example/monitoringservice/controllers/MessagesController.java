package com.example.monitoringservice.controllers;


import com.example.monitoringservice.entities.Messages;
import com.example.monitoringservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessagesController {
    private final MessageService messagesService;

    @Autowired
    public MessagesController(MessageService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Messages> getMessages() {
        return messagesService.findAll();
    }

    @GetMapping(value = "/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Messages> getMessagesByDeviceId(@PathVariable("serialNumber") String serialNumber) {
        return messagesService.findAllByDeviceId(java.util.UUID.fromString(serialNumber));
    }
    @GetMapping(value = "/user/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Messages> getMessagesByUserId(@PathVariable("userID") String userID) {
        return messagesService.findAllByUserId(Long.parseLong(userID));
    }
}
