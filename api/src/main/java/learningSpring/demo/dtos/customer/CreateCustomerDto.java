package learningSpring.demo.dtos.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCustomerDto {
    private final String name;

    @JsonCreator
    public CreateCustomerDto(
        @JsonProperty String name
    ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
