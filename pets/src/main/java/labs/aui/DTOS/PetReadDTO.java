package labs.aui.DTOS;

import java.util.Date;
import java.util.UUID;

import labs.aui.enums.Animal;
import labs.aui.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import labs.aui.SimpleOwner;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetReadDTO {

    private UUID petId;

    private String name;
    private Animal animal;
    private Date dateOfBirth;
    private Float weight;
    private String breed;
    private String color;
    private Gender gender;

    private SimpleOwner simpleOwner;
}
