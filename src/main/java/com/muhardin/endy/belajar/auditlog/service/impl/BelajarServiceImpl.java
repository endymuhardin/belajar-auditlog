/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.service.impl;

import com.muhardin.endy.belajar.auditlog.domain.Kategori;
import com.muhardin.endy.belajar.auditlog.domain.Produk;
import com.muhardin.endy.belajar.auditlog.service.BelajarService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    
}