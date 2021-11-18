package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.entities.Monitored;
import ro.tuc.ds2020.services.ClientService;
import ro.tuc.ds2020.services.Receive;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/monitored")
public class MonitoredController {

    private final Receive service;

    @Autowired
    public MonitoredController(Receive service) {
        this.service = service;
    }

    @GetMapping(value = "/{sensor_id}")
    public ResponseEntity<List<Monitored>> getMonitoredBySensor(@PathVariable("sensor_id") String id) {
        List<Monitored> dto = service.findMonitoredValues(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
