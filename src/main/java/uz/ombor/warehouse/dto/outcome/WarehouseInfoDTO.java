package uz.ombor.warehouse.dto.outcome;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseInfoDTO {
    private Long id;
    private String serial;
    private String model;
    private String createdDate;
}
