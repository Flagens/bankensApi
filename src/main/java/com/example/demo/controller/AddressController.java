package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses")
    public List<AddressDTO> getAllAddresses(){
        List<Address> addresses = addressService.getAllAddresses();
        return addresses.stream().map(addressService::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddresses(@PathVariable Long id) {
        Address address = addressService.getAddress(id);

        if(address == null) {
            return ResponseEntity.notFound().build();
        }

        AddressDTO addressDTO = addressService.mapEntityToDTO(address);


        return ResponseEntity.accepted().body(addressDTO);

    }

    @PostMapping("/createAddresses")
    public ResponseEntity<Address> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = addressService.mapDTOToEntity(addressDTO);
        address = addressService.addAddress(address);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(address);
    }

    @PutMapping("/updateAddresses/{id}")
    public ResponseEntity<Object> updateAddresses (@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        if (!addressService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Address address = addressService.mapDTOToEntity(addressDTO);
        address.setAddress_id(id);

        Address updatedAddress = addressService.updateAddress(address);

        AddressDTO updatedDTO = addressService.mapEntityToDTO(updatedAddress);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(updatedDTO);
    }

    @DeleteMapping("/deleteAddresses/{id}")
    public ResponseEntity<Object> deleteAddresses(@PathVariable Long id) {
        if (addressService.existsById(id)) {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
