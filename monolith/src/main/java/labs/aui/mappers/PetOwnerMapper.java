package labs.aui.mappers;

import labs.aui.PetOwner;
import labs.aui.DTOS.PetOwnerCollectionDTO;
import labs.aui.DTOS.PetOwnerCreateUpdateDTO;
import labs.aui.DTOS.PetOwnerReadDTO;

import org.springframework.stereotype.Component;

@Component
public interface PetOwnerMapper {
    PetOwner toEntity(PetOwnerCreateUpdateDTO dto);
    PetOwnerReadDTO toReadDto(PetOwner entity);
    PetOwnerCollectionDTO toCollectionDto(PetOwner entity);
}
