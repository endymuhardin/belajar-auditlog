/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.service;

import com.muhardin.endy.belajar.auditlog.domain.Kategori;
import com.muhardin.endy.belajar.auditlog.domain.Produk;

import java.util.List;

/**
 *
 * @author endy
 */
public interface BelajarService {
    void simpan(Kategori k);
    void hapus(Kategori k);
    Kategori cariKategoriById(Integer id);
    
    void simpan(Produk p);
    void hapus(Produk p);
    Produk cariProdukById(Integer id);

    List<Object[]> history(Class entityClass, Integer id);
}
