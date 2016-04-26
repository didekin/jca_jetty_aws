package com.didekin.trash.endpoints;

import com.didekin.trash.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.didekin.trash.configuration.Profiles.JETTY_LOCAL;

/**
 * User: pedro@didekin
 * Date: 07/04/16
 * Time: 17:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
@SpringApplicationConfiguration(classes = {Application.class})
@ActiveProfiles(JETTY_LOCAL)
public class GreetingControllerDevTest extends GreetingControllerTest {

    @Test
    public void testGreeting() throws Exception{
        super.testGreeting();
    }

    @Test
    public void testGreetingClose() throws Exception
    {
        super.testGreetingClose();
    }
}