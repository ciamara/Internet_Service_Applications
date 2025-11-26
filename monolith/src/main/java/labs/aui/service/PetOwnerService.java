package labs.aui.service;

import labs.aui.Pet;
import labs.aui.PetOwner;
import labs.aui.repos.PetOwnerRepository;
import labs.aui.repos.PetRepository;
import labs.aui.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class PetOwnerService {

    private final PetOwnerRepository petOwnerRepository;
    private final PetRepository petRepository;

    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetRepository petRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.petRepository = petRepository;
    }

    public List<PetOwner> findAll() {
        return petOwnerRepository.findAll();
    }

    public PetOwner findById(UUID id) {
        return petOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PetOwner", id));
    }

    public PetOwner save(PetOwner owner) {
        return petOwnerRepository.save(owner);
    }

    public void delete(UUID id) {
        if (!petOwnerRepository.existsById(id)) {
            throw new ResourceNotFoundException("PetOwner", id);
        }
        petOwnerRepository.deleteById(id);
    }

    public List<Pet> findPetsByOwnerId(UUID ownerId) {

        Optional<PetOwner> owner = petOwnerRepository.findById(ownerId);
        if (owner.isEmpty()) {
            throw new ResourceNotFoundException("PetOwner", ownerId);
        }
        return petRepository.findByOwner(owner.get());
    }
}
