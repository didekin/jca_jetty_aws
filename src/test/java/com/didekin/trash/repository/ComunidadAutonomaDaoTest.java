package com.didekin.trash.repository;

import com.didekin.trash.configuration.DataSourceConfigurationDev;
import com.didekin.trash.configuration.DataSourceConfigurationPre;
import com.didekin.trash.dominio.ComunidadAutonoma;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 26/04/16
 * Time: 18:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationDev.class, DataSourceConfigurationPre.class})
public class ComunidadAutonomaDaoTest {

    @Autowired
    private ComunidadAutonomaDao comunidadDao;

    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insert_ca.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete_ca.sql")
    @Test
    public void getComunidadById() throws Exception
    {
         assertThat(comunidadDao.getComunidadById(12L), is(new ComunidadAutonoma(12L, "Galicia")));
    }
}