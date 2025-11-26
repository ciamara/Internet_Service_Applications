package labs.aui.mappers;

import labs.aui.Pet;
import labs.aui.DTOS.PetCollectionDTO;
import labs.aui.DTOS.PetReadDTO;
import labs.aui.DTOS.PetCreateUpdateDTO;

import org.springframework.stereotype.Component;

@Component
public interface PetMapper {
    Pet toEntity(PetCreateUpdateDTO dto);
    PetReadDTO toReadDto(Pet entity);
    PetCollectionDTO toCollectionDto(Pet entity);

}
