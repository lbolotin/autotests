package Lesson6.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CommonCategory {
    private String title;
    private String StringAmount;
}


