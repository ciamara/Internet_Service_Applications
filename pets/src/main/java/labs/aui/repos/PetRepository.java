package labs.aui.repos;

import labs.aui.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

import labs.aui.SimpleOwner;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {
    List<Pet> findBySimpleOwner(SimpleOwner simpleOwner);

    @Modifying
    @Transactional
    @Query("DELETE FROM Pet p WHERE p.simpleOwner.simpleOwnerId = :ownerId")
    void deleteByOwnerId(@Param("ownerId") UUID ownerId);
}
