package com.fiipradctic.Lybrarium.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comic_book")
public class ComicBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String series;
    private int number;

}
