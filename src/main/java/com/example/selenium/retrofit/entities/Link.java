package com.example.selenium.retrofit.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Data
@NoArgsConstructor
public class Link {
    
    private int id;
    private String url;
    private String title;
    private String description;
}
