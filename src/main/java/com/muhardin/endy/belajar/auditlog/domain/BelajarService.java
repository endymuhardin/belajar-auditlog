/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.domain;

/**
 *
 * @author endy
 */
public interface BelajarService {
    void simpan(Kategori k);
    void hapus(Kategori k);
    
    void simpan(Produk p);
    void hapus(Produk p);
}
