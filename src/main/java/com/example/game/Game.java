package com.example.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@jakarta.persistence.Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Game {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue
    private Long id;
    private String name;
    public enum Status {
        NEW, FINISHED, DROPPED
    }
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private Date createdAt;
    private Date updatedAt;

}
