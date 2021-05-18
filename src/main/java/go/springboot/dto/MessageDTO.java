package go.springboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String message;
    private String type;
    private String details;

}
