package com.project1.generator;
 
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
 
public class LensIdGenerator implements IdentifierGenerator {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "LENS-";
        // Fetch the last lens ID from the database
        Query query = session.createNativeQuery("SELECT lens_id FROM lenses ORDER BY lens_id DESC LIMIT 1");
        List<String> resultList = query.getResultList();
        int nextId = 1;
        if (!resultList.isEmpty() && resultList.get(0) != null) {
            String lastId = resultList.get(0);
            int lastNumber = Integer.parseInt(lastId.replace(prefix, ""));
            nextId = lastNumber + 1;
        }
        return prefix + nextId;
    }
}