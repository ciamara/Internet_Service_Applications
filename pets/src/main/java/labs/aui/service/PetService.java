package labs.aui.service;

import labs.aui.Pet;
import labs.aui.SimpleOwner;
import labs.aui.repos.PetRepository;
import labs.aui.repos.SimpleOwnerRepository;
import labs.aui.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final SimpleOwnerRepository simpleOwnerRepository;

    public PetService(PetRepository petRepository, SimpleOwnerRepository simpleOwnerRepository) {
        this.petRepository = petRepository;
        this.simpleOwnerRepository = simpleOwnerRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));
    }

    public Pet save(Pet pet) {
        UUID simpleOwnerId = pet.getSimpleOwner().getSimpleOwnerId();

        if (!simpleOwnerRepository.existsById(simpleOwnerId)) {
            throw new ResourceNotFoundException("Owner", simpleOwnerId);
        }

        return petRepository.save(pet);
    }

    @Transactional
    public void delete(UUID id) {
        Pet petToDelete = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));

        petRepository.delete(petToDelete);
    }

    @Transactional
    public void deletePetsByOwnerId(UUID ownerId) {
        if (!simpleOwnerRepository.existsById(ownerId)) {
            throw new ResourceNotFoundException("Owner", ownerId);
        }
        petRepository.deleteByOwnerId(ownerId);
    }

    public List<Pet> findByOwner(SimpleOwner simpleOwner) {
        return petRepository.findBySimpleOwner(simpleOwner);
    }
}
