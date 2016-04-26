package com.didekin.trash.endpoints;

import com.didekin.trash.dominio.ErrorBean;
import com.didekin.trash.dominio.Greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SuppressWarnings("WeakerAccess")
@RestController
public class GreetingController {

    public static final String GREETING_URL = "/greeting";
    public static final String GREETING_URL_CLOSE = "/closed/greeting";
    public static final String NAME_PARAM = "prop";
    public static final String ERROR = "/error";
    public static final String MIME_JSON = "application/json";

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class.getCanonicalName());

    private static final String template = "Hello, %s!";
    private static final String templateClose = "Hello Close, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = GREETING_URL, method = GET ,produces = MIME_JSON)
    public Greeting greeting(@RequestParam(value = "prop", defaultValue = "World") String name)
    {
        logger.debug("greeting(), name = " + name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = GREETING_URL_CLOSE, method = GET ,produces = MIME_JSON)
    public Greeting greetingClose(@RequestParam(value = "prop", defaultValue = "World") String name)
    {
        logger.debug("greetingClose(), name = " + name);
        return new Greeting(counter.incrementAndGet(), String.format(templateClose, name));
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // The specific status is in the errorBean.
    public ErrorBean entityExceptionHandling(Exception e)
    {
        logger.error("entityExceptionHandling(): " + e.getMessage());
        return new ErrorBean(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST.value());
    }
}
