package labs.aui.DTOS;

import java.util.Date;
import java.util.List;
import java.util.UUID;
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
public class PetOwnerReadDTO {

    private UUID ownerId;

    private String name;
    private String surname;
    private int phoneNumber;
    private Date dateOfBirth;
    private String address;

    private List<PetCollectionDTO> pets;
}

