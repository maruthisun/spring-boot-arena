package com.app.paintCalculator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAINT_DETAILS")
public class PaintDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String user_id;

    @Column(name="room_length")
    private int room_length;

    @Column(name="room_height")
    private int room_height;

    @Column(name="door_size")
    private int door_size;

    @Column(name="number_of_doors")
    private int number_of_doors;

    @Column(name="window_size")
    private int window_size;

    @Column(name="number_of_windows")
    private int number_of_windows;

    @Column(name="is_trim_required")
    private String is_trim_required;

    @Column(name="trim_size")
    private int trim_size;

    @Column(name="paint_required")
    private float paint_required;

    @Column(name="price")
    private float price;


}
