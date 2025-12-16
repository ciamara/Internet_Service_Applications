package labs.aui.controllers;

import labs.aui.Pet;
import labs.aui.DTOS.PetCollectionDTO;
import labs.aui.DTOS.PetReadDTO;
import labs.aui.DTOS.PetCreateUpdateDTO;
import labs.aui.SimpleOwner;
import labs.aui.mappers.PetMapper;
import labs.aui.repos.SimpleOwnerRepository;
import labs.aui.service.PetService;
import labs.aui.service.SimpleOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners")
public class OwnerPetController {

    private final PetService petService;
    private final SimpleOwnerService simpleOwnerService;
    private final PetMapper petMapper;

    public OwnerPetController(PetService petService, SimpleOwnerService simpleOwnerService, PetMapper petMapper) {
        this.petService = petService;
        this.simpleOwnerService = simpleOwnerService;
        this.petMapper = petMapper;
    }

    @GetMapping("/{ownerId}/pets")
    public ResponseEntity<List<PetCollectionDTO>> getPetsByOwner(@PathVariable("ownerId") UUID ownerId) {

        SimpleOwner simpleOwner = simpleOwnerService.findById(ownerId);

        if (simpleOwner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found");
        }

        List<Pet> pets = petService.findByOwner(simpleOwner);

        List<PetCollectionDTO> dtos = pets.stream()
                .map(petMapper::toCollectionDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}