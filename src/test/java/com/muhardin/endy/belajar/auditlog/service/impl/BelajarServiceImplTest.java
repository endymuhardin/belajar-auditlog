/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.auditlog.service.impl;

import com.muhardin.endy.belajar.auditlog.domain.Kategori;
import com.muhardin.endy.belajar.auditlog.service.BelajarService;
import java.io.File;
import java.sql.Connection;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

        belajarService.simpan(k);
    }
}
