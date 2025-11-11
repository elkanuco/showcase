package lu.elkanuco.thirdparty.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class CreatePersonAssignmentDTO {
    private String fullname;
    private String pictureurl;
    private UUID projectId;
    private UUID roleId;
}