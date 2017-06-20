package api.session;

import org.springframework.data.repository.CrudRepository;
import api.session.Session;

// This will be AUTO IMPLEMENTED by Spring into a Bean called sessionRepository
// CRUD refers Create, Read, Update, Delete

public interface SessionRepository extends CrudRepository<Session, Long> {

}
