package com.didekin.trash.endpoints;

import com.didekin.trash.Application;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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

    /*@ClassRule
    public static ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable
        {
            Runtime.getRuntime().exec("/usr/local/bin/nginx");
        }

        @Override
        protected void after()
        {
            try {
                Runtime.getRuntime().exec("/usr/local/bin/nginx -s quit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };*/
}