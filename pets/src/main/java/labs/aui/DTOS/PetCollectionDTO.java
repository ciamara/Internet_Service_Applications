package labs.aui.DTOS;

import java.util.UUID;

import labs.aui.enums.Animal;
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
public class PetCollectionDTO {

    private UUID petId;

    private String name;
    private Animal animal;
}
