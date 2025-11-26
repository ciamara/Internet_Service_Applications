package labs.aui.DTOS;

import java.io.Serializable;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SimpleOwner{

    private UUID simpleOwnerId;
    private String simpleOwnerName;

//    public SimpleOwner(UUID simpleOwnerId, String simpleOwnerName) {
//        this.simpleOwnerId = simpleOwnerId;
//        this.simpleOwnerName = simpleOwnerName;
//    }
}
