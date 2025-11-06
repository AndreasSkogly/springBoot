package model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeltagerRepository extends JpaRepository<Deltager, String> {
    boolean existsByMobil(String mobil);
    List<Deltager> findAllByOrderByFornavnAsc();
}
