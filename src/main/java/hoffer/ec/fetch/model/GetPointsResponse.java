package hoffer.ec.fetch.model;

import lombok.Data;

/**
 * Response POJO for getPoints api
 */
@Data
public class GetPointsResponse {
    private final long points;
}
