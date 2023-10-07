package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private String name;
    private String surrname;
    private String pesel;
    private Address address_id;
    private String phone_number;



}
