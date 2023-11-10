package uz.ombor.warehouse.outcome;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseOutcomeRepository extends JpaRepository<WarehouseOutcomeEntity, Long> {
}