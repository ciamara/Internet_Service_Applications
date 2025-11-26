package labs.aui.controllers;

import labs.aui.Pet;
import labs.aui.DTOS.PetCollectionDTO;
import labs.aui.DTOS.PetReadDTO;
import labs.aui.DTOS.PetCreateUpdateDTO;
import labs.aui.mappers.PetMapper;
import labs.aui.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @GetMapping
    public ResponseEntity<List<PetCollectionDTO>> getAllPets() {
        List<PetCollectionDTO> dtos = petService.findAll().stream()
                .map(petMapper::toCollectionDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetReadDTO> getPetById(@PathVariable UUID petId) {
        Pet pet = petService.findById(petId);
        return ResponseEntity.ok(petMapper.toReadDto(pet));
    }

    @PostMapping
    public ResponseEntity<PetReadDTO> createPet(@RequestBody PetCreateUpdateDTO dto) {
        Pet petToCreate = petMapper.toEntity(dto);
        Pet createdPet = petService.save(petToCreate);

        return new ResponseEntity<>(petMapper.toReadDto(createdPet), HttpStatus.CREATED);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetReadDTO> updatePet(@PathVariable UUID petId, @RequestBody PetCreateUpdateDTO dto) {

        Pet petToUpdate = petMapper.toEntity(dto);
        petToUpdate.setPetId(petId);

        Pet updatedPet = petService.save(petToUpdate);

        return ResponseEntity.ok(petMapper.toReadDto(updatedPet));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable UUID petId) {
        petService.delete(petId);
        return ResponseEntity.noContent().build();
    }
}
