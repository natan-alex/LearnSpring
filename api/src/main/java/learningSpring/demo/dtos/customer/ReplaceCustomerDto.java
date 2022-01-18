package learningSpring.demo.dtos.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplaceCustomerDto {
    private final String name;

    @JsonCreator
    public ReplaceCustomerDto(
        @JsonProperty String name
    ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
