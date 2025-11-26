package labs.aui.mappers;

import labs.aui.PetOwner;
import labs.aui.DTOS.PetOwnerCollectionDTO;
import labs.aui.DTOS.PetOwnerReadDTO;
import labs.aui.DTOS.PetOwnerCreateUpdateDTO;
import labs.aui.DTOS.PetCollectionDTO;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetOwnerMapperImplementation implements PetOwnerMapper {

    private final PetMapper petMapper;

    public PetOwnerMapperImplementation(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Override
    public PetOwner toEntity(PetOwnerCreateUpdateDTO dto) {
        PetOwner owner = new PetOwner();
        owner.setName(dto.getName());
        owner.setSurname(dto.getSurname());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setDateOfBirth(dto.getDateOfBirth());
        owner.setAddress(dto.getAddress());
        return owner;
    }

    @Override
    public PetOwnerReadDTO toReadDto(PetOwner entity) {
        List<PetCollectionDTO> petDtos = entity.getPets() != null ?
                entity.getPets().stream()
                        .map(petMapper::toCollectionDto)
                        .collect(Collectors.toList())
                : List.of();

        return PetOwnerReadDTO.builder()
                .ownerId(entity.getOwnerId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .address(entity.getAddress())
                .pets(petDtos)
                .build();
    }

    @Override
    public PetOwnerCollectionDTO toCollectionDto(PetOwner entity) {
        return PetOwnerCollectionDTO.builder()
                .ownerId(entity.getOwnerId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
