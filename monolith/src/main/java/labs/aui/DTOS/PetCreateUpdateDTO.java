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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetCreateUpdateDTO {

    private String name;
    private Animal animal;
    private Date dateOfBirth;
    private Float weight;
    private String breed;
    private String color;
    private Gender gender;

    private UUID ownerId;
}
