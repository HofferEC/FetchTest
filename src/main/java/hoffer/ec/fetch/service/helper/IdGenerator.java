package hoffer.ec.fetch.service.helper;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdGenerator {

    /**
     * Returns a functionally unique ID for the receipt. If we intend to scale this system, we'd hook this up to a
     * sequential ID generating service to guarantee uniqueness. Given the prompt specifies "Data does not need to
     * survive an application restart", I'll assume UUID collision is a non-issue.
     *
     * @return a functionally unique id
     */
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
