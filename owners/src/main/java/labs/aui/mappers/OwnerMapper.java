package labs.aui.mappers;

import labs.aui.Owner;
import labs.aui.DTOS.OwnerCollectionDTO;
import labs.aui.DTOS.OwnerCreateUpdateDTO;
import labs.aui.DTOS.OwnerReadDTO;

import org.springframework.stereotype.Component;

@Component
public interface OwnerMapper {
    Owner toEntity(OwnerCreateUpdateDTO dto);
    OwnerReadDTO toReadDto(Owner entity);
    OwnerCollectionDTO toCollectionDto(Owner entity);
}
