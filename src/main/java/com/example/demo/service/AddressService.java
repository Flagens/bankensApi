package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.model.AddressDTO;
import com.example.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public AddressDTO getAddressDTO(Long id) {
        Address address = getAddress(id);
        if (address == null) {
            return null;
        }
        return mapEntityToDTO(address);
    }

    public List<AddressDTO> getAllAddressDTOs() {
        return getAllAddresses().stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address createAddressFromDTO(AddressDTO addressDTO) {
        Address address = mapDTOToEntity(addressDTO);
        return createAddress(address);
    }

    public AddressDTO updateAddressFromDTO(Long id, AddressDTO addressDTO) {
        Address address = mapDTOToEntity(addressDTO);
        address.setAddress_id(id);
        Address updatedAddress = updateAddress(address);
        return mapEntityToDTO(updatedAddress);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

    public AddressDTO mapEntityToDTO(Address entity) {
        return modelMapper.map(entity, AddressDTO.class);
    }

    public Address mapDTOToEntity(AddressDTO dto) {
        return modelMapper.map(dto, Address.class);
    }
}
