package labs.aui.repos;

import labs.aui.SimpleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SimpleOwnerRepository extends JpaRepository<SimpleOwner, UUID> {

}
