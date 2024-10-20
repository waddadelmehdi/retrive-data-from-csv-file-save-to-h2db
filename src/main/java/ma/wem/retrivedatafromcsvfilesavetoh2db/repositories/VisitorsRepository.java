package ma.wem.retrivedatafromcsvfilesavetoh2db.repositories;

import ma.wem.retrivedatafromcsvfilesavetoh2db.dto.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorsRepository extends JpaRepository<Visitors, Long> {
}
