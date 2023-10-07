package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.model.AddressDTO;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionDTO;
import com.example.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ModelMapper modelMapper;

    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);

    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }


    public boolean existsById(Long id) {return addressRepository.existsById(id);}

    public AddressDTO mapEntityToDTO(Address entity) {
        return modelMapper.map(entity, AddressDTO.class);
    }

    public Address mapDTOToEntity(AddressDTO dto) {
        return modelMapper.map(dto, Address.class);
    }



}
