package com.example.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    public enum Status {
        NEW, FINISHED, DROPPED
    }
    @Enumerated
    private Status status;
    private Date createdAt;
    private Date updatedAt;

}
