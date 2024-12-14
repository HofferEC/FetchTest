package hoffer.ec.fetch.persistence.hash;

import hoffer.ec.fetch.persistence.IReceiptDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HashMapReceiptDB implements IReceiptDAO {

    private final HashMap<String, Long> db;

    /**
     * Obviously in a production system we'd use a db here not a HashMap, but given the prompt specifies "It is
     * sufficient to store information in memory", I'm using a HashMap here. We can pretty easily create another impl of
     * the IReceiptDAO interface with a real db in order to upgrade the storage in-place.
     */
    public HashMapReceiptDB() {
        db = new HashMap<>();
    }

    /**
     * Gets a receipt's point value from the map.
     *
     * @param id | the ID of the receipt
     * @return the points the receipt is worth
     */
    @Override
    public long get(String id) {
        return db.get(id);
    }

    /**
     * Adds a receipt to the map.
     *
     * @param id     | the ID of the receipt
     * @param points | the points the receipt is worth
     */
    @Override
    public void put(String id, long points) {
        db.put(id, points);
    }
}
