package com.example.selenium.retrofit.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Data
@NoArgsConstructor
public class List {
    
    private int id;
    private String name;
    private String description;
    private Boolean isPrivate;
}
