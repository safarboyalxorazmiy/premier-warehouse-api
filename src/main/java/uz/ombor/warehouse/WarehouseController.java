package uz.ombor.warehouse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ombor.warehouse.dto.outcome.WarehouseInfoDTO;
import uz.ombor.warehouse.dto.outcome.WarehouseOutcomeCreateDTO;
import uz.ombor.warehouse.outcome.WarehouseOutcomeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final WarehouseOutcomeService warehouseOutcomeService;


    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(
            @RequestParam String serial
    ) {
        return ResponseEntity.ok(warehouseService.create(serial));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/outcome/create")
    public ResponseEntity<Boolean> create(
            @RequestBody WarehouseOutcomeCreateDTO warehouseOutcomeCreateDTO
    ) {
        return ResponseEntity.ok(warehouseOutcomeService.create(warehouseOutcomeCreateDTO));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get/all")
    public ResponseEntity<List<List<WarehouseInfoDTO>>> getAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(warehouseService.getCount());
    }
}