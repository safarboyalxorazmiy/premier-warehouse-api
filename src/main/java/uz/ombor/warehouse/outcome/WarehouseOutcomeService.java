package uz.ombor.warehouse.outcome;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ombor.warehouse.dto.outcome.WarehouseInfoDTO;
import uz.ombor.warehouse.dto.outcome.WarehouseOutcomeCreateDTO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseOutcomeService {
    private final WarehouseOutcomeRepository warehouseOutcomeRepository;

    public Boolean create(WarehouseOutcomeCreateDTO createDTO) {
        WarehouseOutcomeEntity warehouseOutcome = new WarehouseOutcomeEntity();
        warehouseOutcome.setSerial(createDTO.getSerial());
        warehouseOutcome.setGsCode(createDTO.getGscode());
        warehouseOutcomeRepository.save(warehouseOutcome);
        return true;
    }

    public List<WarehouseInfoDTO> findAll() {
        List<WarehouseOutcomeEntity> all = warehouseOutcomeRepository.findAll();
        List<WarehouseInfoDTO> result = new ArrayList<>();

        for (WarehouseOutcomeEntity warehouseOutcome : all) {
            WarehouseInfoDTO info = new WarehouseInfoDTO();
            info.setId(warehouseOutcome.getId());
            info.setSerial(warehouseOutcome.getSerial());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
            String formattedDateTime = warehouseOutcome.getCreatedDate().format(formatter);

            info.setCreatedDate(formattedDateTime);

            result.add(info);
        }

        return result;
    }
}