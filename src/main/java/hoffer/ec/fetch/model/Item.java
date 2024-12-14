package hoffer.ec.fetch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item data type POJO matching API spec (subtype of Receipt)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String shortDescription;
    private String price;
}
