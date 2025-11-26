package labs.aui.mappers;

import labs.aui.Owner;
import labs.aui.DTOS.OwnerCollectionDTO;
import labs.aui.DTOS.OwnerReadDTO;
import labs.aui.DTOS.OwnerCreateUpdateDTO;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerMapperImplementation implements OwnerMapper {

    public OwnerMapperImplementation() {}

    @Override
    public Owner toEntity(OwnerCreateUpdateDTO dto) {
        Owner owner = new Owner();
        owner.setName(dto.getName());
        owner.setSurname(dto.getSurname());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setDateOfBirth(dto.getDateOfBirth());
        owner.setAddress(dto.getAddress());
        return owner;
    }

    @Override
    public OwnerReadDTO toReadDto(Owner entity) {
        return OwnerReadDTO.builder()
                .ownerId(entity.getOwnerId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .address(entity.getAddress())
                .build();
    }

    @Override
    public OwnerCollectionDTO toCollectionDto(Owner entity) {
        return OwnerCollectionDTO.builder()
                .ownerId(entity.getOwnerId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
