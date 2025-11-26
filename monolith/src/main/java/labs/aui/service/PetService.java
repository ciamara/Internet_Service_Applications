package labs.aui.service;

import labs.aui.Pet;
import labs.aui.PetOwner;
import labs.aui.repos.PetRepository;
import labs.aui.repos.PetOwnerRepository;
import labs.aui.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final PetOwnerService petOwnerService;

    public PetService(PetRepository petRepository, PetOwnerRepository petOwnerRepository, PetOwnerService petOwnerService) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.petOwnerService = petOwnerService;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));
    }

    public Pet save(Pet pet) {
        UUID ownerId = pet.getOwner().getOwnerId();

        if (!petOwnerRepository.existsById(ownerId)) {
            throw new ResourceNotFoundException("Owner", ownerId);
        }

        return petRepository.save(pet);
    }

    @Transactional
    public void delete(UUID id) {
        Pet petToDelete = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));

        PetOwner owner = petToDelete.getOwner();

        if (owner != null) {
            owner.getPets().remove(petToDelete);
            petOwnerService.save(owner);
        }
        petRepository.delete(petToDelete);
    }

    public List<Pet> findByOwner(PetOwner owner) {
        return petRepository.findByOwner(owner);
    }
}
