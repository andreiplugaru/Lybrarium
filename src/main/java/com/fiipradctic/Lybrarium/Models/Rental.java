package com.fiipradctic.Lybrarium.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long itemId;
    private Long clientId;
    private Date rentedDate;
    private Date returnedDate;
}
