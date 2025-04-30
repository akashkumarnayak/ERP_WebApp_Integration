package org.integration.erp.dtos;

import lombok.Data;
import org.integration.erp.entities.ReleaseStatus;

@Data
public class PoResponseRequestDto {
    Long poNumber;
    ReleaseStatus releaseStatus;
}
