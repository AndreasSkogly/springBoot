package model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeltagerRepository extends JpaRepository<Deltager, String> {
    boolean existsByMobil(String mobil);
}
