package com.project1.generator;

import java.io.Serializable;
import java.util.List;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class SunglassIdGenerator implements IdentifierGenerator {
	@PersistenceContext
    private EntityManager entityManager;
 
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "SUNGLASS-";
        // Fetch the last lens ID from the database
        Query query = session.createNativeQuery("SELECT sun_glass_id FROM sun_glasses ORDER BY sun_glass_id DESC LIMIT 1");
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
