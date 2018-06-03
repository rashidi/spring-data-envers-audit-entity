package rz.demo.boot.data.envers.audit;

import org.hibernate.envers.RevisionListener;

/**
 * @author Rashidi Zin
 */
public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;

        auditRevisionEntity.setUsername("wade.wilson");
    }

}
