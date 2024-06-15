package com.lps.subscriptions.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Subscriber")
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "El nombre del subscritor no puede estar vacio")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "El correo  no tiene formato válido")
    private String email;

    @Column(name = "creation_date")
    private Date creationDate;


}
