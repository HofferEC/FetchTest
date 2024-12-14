package hoffer.ec.fetch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Receipt data type POJO matching API spec
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private String total;
    private List<Item> items;
}
