package uz.ombor.warehouse.outcome;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "warehouse_outcome")
public class WarehouseOutcomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serial;

    private String gsCode;

    private LocalDateTime createdDate = LocalDateTime.now();
}
