package labs.aui.controllers;

import labs.aui.DTOS.PetCollectionDTO;
import labs.aui.DTOS.PetOwnerCollectionDTO;
import labs.aui.DTOS.PetOwnerCreateUpdateDTO;
import labs.aui.DTOS.PetOwnerReadDTO;
import labs.aui.PetOwner;
import labs.aui.mappers.PetMapper;
import labs.aui.mappers.PetOwnerMapper;
import labs.aui.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners")
public class PetOwnerController {

    private final PetOwnerService petOwnerService;
    private final PetOwnerMapper petOwnerMapper;
    private final PetMapper petMapper;

    @Autowired
    public PetOwnerController(PetOwnerService petOwnerService, PetOwnerMapper petOwnerMapper, PetMapper petMapper) {
        this.petOwnerService = petOwnerService;
        this.petOwnerMapper = petOwnerMapper;
        this.petMapper = petMapper;
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerCollectionDTO>> getAllOwners() {
        List<PetOwnerCollectionDTO> dtos = petOwnerService.findAll().stream()
                .map(petOwnerMapper::toCollectionDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<PetOwnerReadDTO> getOwnerById(@PathVariable UUID ownerId) {
        PetOwner owner = petOwnerService.findById(ownerId);
        return ResponseEntity.ok(petOwnerMapper.toReadDto(owner));
    }

    @PostMapping
    public ResponseEntity<PetOwnerReadDTO> createOwner(@RequestBody PetOwnerCreateUpdateDTO dto) {
        PetOwner owner = petOwnerMapper.toEntity(dto);
        PetOwner createdOwner = petOwnerService.save(owner);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petOwnerMapper.toReadDto(createdOwner));
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<PetOwnerReadDTO> updateOwner(@PathVariable UUID ownerId, @RequestBody PetOwnerCreateUpdateDTO dto) {
        PetOwner owner = petOwnerMapper.toEntity(dto);
        owner.setOwnerId(ownerId);
        PetOwner updatedOwner = petOwnerService.save(owner);
        return ResponseEntity.ok(petOwnerMapper.toReadDto(updatedOwner));
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable UUID ownerId) {
        petOwnerService.delete(ownerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ownerId}/pets")
    public ResponseEntity<List<PetCollectionDTO>> getPetsByOwner(@PathVariable UUID ownerId) {
        List<PetCollectionDTO> dtos = petOwnerService.findPetsByOwnerId(ownerId).stream()
                .map(petMapper::toCollectionDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
