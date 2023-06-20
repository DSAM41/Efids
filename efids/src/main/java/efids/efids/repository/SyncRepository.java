package efids.efids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import efids.efids.model.Sync;

@Repository
public interface SyncRepository extends JpaRepository<Sync, Integer> {

}
