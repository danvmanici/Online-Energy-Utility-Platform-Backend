package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public ResponseEntity<List<SensorDTO>> getSensors() {
        List<SensorDTO> dtos = sensorService.findSensors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody SensorDTO personDTO) {
        UUID personID = sensorService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable("id") UUID personId) {
        SensorDTO dto = sensorService.findSensorById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
     public ResponseEntity<UUID> deleteSensor(@PathVariable UUID id){
        UUID sensorID = sensorService.delete(id);
        return new ResponseEntity<>(sensorID, HttpStatus.OK);
    }


    @PutMapping("/update")
    public  ResponseEntity<UUID> updateSensor(@Valid @RequestBody SensorDTO sensorDetailsDTO){
        UUID personID = sensorService.update(sensorDetailsDTO);
        return new ResponseEntity<>(personID, HttpStatus.OK);
    }
}
