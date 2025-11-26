package labs.aui.DTOS;

import java.util.Date;
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
public class OwnerCreateUpdateDTO {

    private String name;
    private String surname;
    private int phoneNumber;
    private Date dateOfBirth;
    private String address;
}

