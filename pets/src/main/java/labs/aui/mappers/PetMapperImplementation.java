package labs.aui.mappers;

import labs.aui.Pet;
import labs.aui.SimpleOwner;
import labs.aui.DTOS.PetCollectionDTO;
import labs.aui.DTOS.PetReadDTO;
import labs.aui.DTOS.PetCreateUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PetMapperImplementation implements PetMapper {

    @Lazy
    @Autowired
    public PetMapperImplementation() {}

    @Override
    public Pet toEntity(PetCreateUpdateDTO dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setAnimal(dto.getAnimal());
        pet.setDateOfBirth(dto.getDateOfBirth());
        pet.setWeight(dto.getWeight());
        pet.setBreed(dto.getBreed());
        pet.setColor(dto.getColor());
        pet.setGender(dto.getGender());

        if (dto.getOwnerId() != null) {
            SimpleOwner simpleOwner = new SimpleOwner();
            simpleOwner.setSimpleOwnerId(dto.getOwnerId());
            pet.setSimpleOwner(simpleOwner);
        }
        return pet;
    }

    @Override
    public PetReadDTO toReadDto(Pet entity) {
        return PetReadDTO.builder()
                .petId(entity.getPetId())
                .name(entity.getName())
                .animal(entity.getAnimal())
                .dateOfBirth(entity.getDateOfBirth())
                .weight(entity.getWeight())
                .breed(entity.getBreed())
                .color(entity.getColor())
                .gender(entity.getGender())
                .simpleOwner(entity.getSimpleOwner())
                .build();
    }

    @Override
    public PetCollectionDTO toCollectionDto(Pet entity) {
        return PetCollectionDTO.builder()
                .petId(entity.getPetId())
                .name(entity.getName())
                .animal(entity.getAnimal())
                .build();
    }
}
