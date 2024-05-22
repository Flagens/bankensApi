package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.AddressDTO;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addressDTOS = addressService.getAllAddressDTOs();
        if (addressDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressDTOS);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.getAddressDTO(id);
        if (addressDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(addressDTO);
    }

    @PostMapping("/createAddresses")
    public ResponseEntity<Address> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = addressService.createAddressFromDTO(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PutMapping("/updateAddresses/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        if (!addressService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        AddressDTO updatedDTO = addressService.updateAddressFromDTO(id, addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDTO);
    }

    @DeleteMapping("/deleteAddresses/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {
        if (!addressService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
