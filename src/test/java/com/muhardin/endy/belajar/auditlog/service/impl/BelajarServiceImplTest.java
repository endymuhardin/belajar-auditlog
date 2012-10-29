/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.service.impl;

import com.muhardin.endy.belajar.auditlog.domain.Kategori;
import com.muhardin.endy.belajar.auditlog.domain.Produk;
import com.muhardin.endy.belajar.auditlog.service.BelajarService;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author endy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:com/muhardin/endy/**/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
public class BelajarServiceImplTest {

    @Autowired private BelajarService belajarService;
    @Autowired private DataSource dataSource;

    @Before
    public void resetDatabase() throws Exception {
        Connection conn = dataSource.getConnection();
        DatabaseOperation.CLEAN_INSERT.execute(new DatabaseConnection(conn),
                new FlatXmlDataSetBuilder().build(new File("src/test/resources/sample-data.xml")));
    }

    @Test
    public void testSimpanKategori() {
        Kategori k = new Kategori();
        k.setKode("K-001");
        k.setNama("Kategori 001");

        assertNull(k.getId());
        belajarService.simpan(k);
        assertNotNull(k.getId());
    }
    
    @Test
    public void testCrudKategori() {
        Kategori k = new Kategori();
        k.setKode("K-999");
        k.setNama("Kategori 999");

        assertNull(k.getId());
        belajarService.simpan(k);
        assertNotNull(k.getId());
        
        k.setKode("K-999-X");
        k.setNama("Kategori 999-X");
        belajarService.simpan(k);
        Kategori kx = belajarService.cariKategoriById(k.getId());
        assertEquals("K-999-X", kx.getKode());
        assertEquals("Kategori 999-X", kx.getNama());
        
        belajarService.hapus(k);
        assertNull(belajarService.cariKategoriById(k.getId()));
    }
    
    @Test
    public void testUpdateKategori() {
        Kategori k = new Kategori();
        k.setId(99);
        k.setKode("K-199");
        k.setNama("Kategori 99 (updated)");
        belajarService.simpan(k);

        Kategori kx = belajarService.cariKategoriById(99);
        assertEquals("K-199", kx.getKode());
        assertEquals("Kategori 99 (updated)", kx.getNama());
    }
    
    @Test
    public void testSimpanProduk(){
        Produk p = new Produk();
        p.setKode("P-001");
        p.setNama("Produk 001");
        p.setHarga(new BigDecimal(120000));

        Kategori k = belajarService.cariKategoriById(98);
        p.setKategori(k);
        
        assertNull(p.getId());
        belajarService.simpan(p);
        assertNotNull(p.getId());
    }
    
    @Test
    public void testUpdateProduk(){
        Produk p = belajarService.cariProdukById(999);
        p.setKode("P-199");
        p.setNama("Produk 199 (updated)");
        p.setHarga(new BigDecimal(115000));

        belajarService.simpan(p);
        Produk px = belajarService.cariProdukById(999);
        assertEquals("P-199", px.getKode());
        assertEquals("Produk 199 (updated)", px.getNama());
    }
    
    @Test
    public void testUpdateKategoriProduk(){
        Produk p = belajarService.cariProdukById(999);
        
        Kategori k = belajarService.cariKategoriById(98);
        p.setKategori(k);
        belajarService.simpan(p);
        
        Produk px = belajarService.cariProdukById(999);
        assertEquals(new Integer(98), new Integer(px.getKategori().getId()));
    }
}
