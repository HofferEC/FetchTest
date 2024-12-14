package hoffer.ec.fetch.persistence;

import org.springframework.stereotype.Repository;

public interface IReceiptDAO {
    public long get(String id);
    public void put(String id, long points);
}
