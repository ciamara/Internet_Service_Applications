package labs.aui.service;

import labs.aui.Owner;
import labs.aui.repos.OwnerRepository;
import labs.aui.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Owner findById(UUID id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", id));
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void delete(UUID id) {
        if (!ownerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Owner", id);
        }
        ownerRepository.deleteById(id);
    }
}
