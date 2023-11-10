package uz.ombor.warehouse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import uz.ombor.warehouse.dto.outcome.WarehouseInfoDTO;
import uz.ombor.warehouse.outcome.WarehouseOutcomeEntity;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Boolean create(String serial) {
        WarehouseEntity ombor = new WarehouseEntity();
        ombor.setSerial(serial);
        warehouseRepository.save(ombor);

        return true;
    }

    public List<List<WarehouseInfoDTO>> findAll() {
        List<WarehouseEntity> all = warehouseRepository.findAll();
        List<List<WarehouseInfoDTO>> result = new ArrayList<>();

        String lastDate = "";
        List<WarehouseInfoDTO> infos = new ArrayList<>();
        boolean first = true;
        for (WarehouseEntity warehouseOutcome : all) {
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String monthFormattedDate = warehouseOutcome.getCreatedDate().format(monthFormatter);

            if (!lastDate.equals(monthFormattedDate)) {
                if (!first) {
                    result.add(infos);
                }

                first = false;

                infos = new ArrayList<>();
                lastDate = monthFormattedDate;

                WarehouseInfoDTO info = new WarehouseInfoDTO();
                info.setId(warehouseOutcome.getId());

                String serial = warehouseOutcome.getSerial();
                char c = serial.charAt(2);
                char c1 = serial.charAt(3);
                String model = String.valueOf(c) + String.valueOf(c1);
                if (model.equals("AA")) {
                    info.setModel("CCP" + String.valueOf(serial.charAt(4) + String.valueOf(serial.charAt(5))));
                } else if (model.equals("AB")) {
                    info.setModel("OCP" + String.valueOf(serial.charAt(4) + String.valueOf(serial.charAt(5))));
                } else {
                    info.setModel("NONE");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
                String formattedDateTime = warehouseOutcome.getCreatedDate().format(formatter);

                info.setSerial(warehouseOutcome.getSerial());

                info.setCreatedDate(formattedDateTime);

                infos.add(info);
            } else {
                WarehouseInfoDTO info = new WarehouseInfoDTO();
                info.setId(warehouseOutcome.getId());

                String serial = warehouseOutcome.getSerial();
                char c = serial.charAt(2);
                char c1 = serial.charAt(3);
                String model = String.valueOf(c) + String.valueOf(c1);
                if (model.equals("AA")) {
                    info.setModel("CCP" + String.valueOf(serial.charAt(4) + String.valueOf(serial.charAt(5))));
                } else if (model.equals("AB")) {
                    info.setModel("OCP" + String.valueOf(serial.charAt(4) + String.valueOf(serial.charAt(5))));
                } else {
                    info.setModel("NONE");
                }

                info.setSerial(warehouseOutcome.getSerial());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
                String formattedDateTime = warehouseOutcome.getCreatedDate().format(formatter);

                info.setCreatedDate(formattedDateTime);

                infos.add(info);
            }
        }
        result.add(infos);
        return result;
    }

    public Long getCount() {
        return warehouseRepository.count();
    }
}