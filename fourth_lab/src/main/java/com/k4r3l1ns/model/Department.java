package com.k4r3l1ns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Department {

    private final UUID id = UUID.randomUUID();
    private final String name;
}
