package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private Long address_id;
    private String city;
    private String street;
    private String house_number;
    private String postal_code;


}
