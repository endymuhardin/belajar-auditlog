/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.hibernate.listener;

import com.muhardin.endy.belajar.auditlog.domain.AuditLogDenganUsername;
import org.hibernate.envers.RevisionListener;

/**
 *
 * @author endy
 */
public class AuditLogUsernameListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        AuditLogDenganUsername obj = (AuditLogDenganUsername) o;
        
        // sementara hardcoded dulu, nantinya ambil dari user yang sedang login
        obj.setUsername("endy"); 
    }
    
}
