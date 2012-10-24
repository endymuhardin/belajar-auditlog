/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.domain;

import com.muhardin.endy.belajar.auditlog.hibernate.listener.AuditLogUsernameListener;
import javax.persistence.Entity;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 *
 * @author endy
 */
@Entity(name="REVINFO")
@RevisionEntity(AuditLogUsernameListener.class)
public class AuditLogDenganUsername extends DefaultRevisionEntity {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
