package ro.tuc.ds2020.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService smartDeviceService;

    @Autowired
    public DeviceController(DeviceService smartDeviceService){
        this.smartDeviceService = smartDeviceService;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getSmartDevices() {
        List<DeviceDTO> dtos = smartDeviceService.findSmartDevice();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DeviceDTO personDTO) {
        UUID personID = smartDeviceService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getSmartDevice(@PathVariable("id") UUID personId) {
        DeviceDTO dto = smartDeviceService.findSmartDeviceById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UUID> deleteSmartDevice(@PathVariable UUID id){

        UUID deviceID = smartDeviceService.delete(id);
        return new ResponseEntity<>(deviceID, HttpStatus.OK);
    }

    @PutMapping("/update")
    public  ResponseEntity<UUID> updateClient(@Valid @RequestBody DeviceDTO smartDeviceDTO){
        UUID personID = smartDeviceService.update(smartDeviceDTO);
        return new ResponseEntity<>(personID, HttpStatus.OK);
    }

}
