package org.erfan.erfanu25.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.erfan.erfanu25.domain.Enum.StatusType;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class Order {
    Long id;
    int quantity;
    String itemName;
    Long customerId;
    long price;
    StatusType status;
    LocalDateTime date;

}
