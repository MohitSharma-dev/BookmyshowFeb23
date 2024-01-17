package com.example.bookmyshowfeb23.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    String name;
//    List<String> actors;
}

// Models -> Tables : atomicity
//         Middle Men
