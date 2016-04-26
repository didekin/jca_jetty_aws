package com.didekin.trash.endpoints;

import com.didekin.trash.configuration.RetrofitConfigurationPre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.didekin.trash.configuration.Profiles.AWS_PRE;

/**
 * User: pedro@didekin
 * Date: 07/04/16
 * Time: 17:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RetrofitConfigurationPre.class})
@ActiveProfiles(AWS_PRE)
public class GreetingControllerPreTest extends GreetingControllerTest {

    @Test
    public void testGreeting() throws Exception
    {
        super.testGreeting();
    }

    @Test
    public void testGreetingClose() throws Exception
    {
        super.testGreetingClose();
    }
}