package labs.aui.service;

import labs.aui.Pet;
import labs.aui.SimpleOwner;
import labs.aui.exceptions.ResourceNotFoundException;
import labs.aui.repos.SimpleOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SimpleOwnerService {

    private final SimpleOwnerRepository simpleOwnerRepository;

    public SimpleOwnerService(SimpleOwnerRepository simpleOwnerRepository) {
        this.simpleOwnerRepository = simpleOwnerRepository;
    }

    public void createSimpleOwner(SimpleOwner simpleOwner) {
        simpleOwnerRepository.save(simpleOwner);
    }

    public void deleteSimpleOwner(UUID simpleOwnerId) {
        simpleOwnerRepository.deleteById(simpleOwnerId);
    }

    public SimpleOwner findById(UUID id) {
        return simpleOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SimpleOwner", id));
    }
}
