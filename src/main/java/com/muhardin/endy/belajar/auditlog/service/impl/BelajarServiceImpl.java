/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.service.impl;

import com.muhardin.endy.belajar.auditlog.domain.AuditLogDenganUsername;
import com.muhardin.endy.belajar.auditlog.domain.Kategori;
import com.muhardin.endy.belajar.auditlog.domain.Produk;
import com.muhardin.endy.belajar.auditlog.service.BelajarService;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.order.AuditOrder;
import org.hibernate.envers.query.order.PropertyAuditOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author endy
 */
@Service @Transactional
public class BelajarServiceImpl implements BelajarService {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void simpan(Kategori k) {
        sessionFactory.getCurrentSession().saveOrUpdate(k);
    }

    @Override
    public void hapus(Kategori k) {
        sessionFactory.getCurrentSession().delete(k);
    }

    @Override
    public Kategori cariKategoriById(Integer id) {
        return (Kategori) sessionFactory.getCurrentSession().get(Kategori.class, id);
    }

    @Override
    public void simpan(Produk p) {
        sessionFactory.getCurrentSession().saveOrUpdate(p);
    }

    @Override
    public void hapus(Produk p) {
        sessionFactory.getCurrentSession().delete(p);
    }

    @Override
    public Produk cariProdukById(Integer id) {
        return (Produk) sessionFactory.getCurrentSession().get(Produk.class, id);
    }

    @Override
    public List<Object[]> history(Class entityClass, Integer id) {
        AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
        List<Object[]> hasil = reader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.revisionProperty("timestamp").desc())
                .getResultList();
        return hasil;
    }
}
