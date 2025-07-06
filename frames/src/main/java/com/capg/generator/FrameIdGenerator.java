package com.capg.generator;

import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import jakarta.persistence.Query;

public class FrameIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "FRAME-";
        
        Query query = session.createQuery("SELECT f.frameId FROM Frames f ORDER BY f.frameId DESC", String.class);
        //ensures that only one result (latest ID) is fetched.
        query.setMaxResults(1); //  Equivalent to LIMIT 1

        String lastId = (String) query.getResultList().stream().findFirst().orElse(null); // ✅ Avoids NoResultException

        int nextId = 1;
        if (lastId != null) {
            int lastNumber = Integer.parseInt(lastId.replace(prefix, ""));
            nextId = lastNumber + 1;
        }

        return prefix + nextId;
    }
}
