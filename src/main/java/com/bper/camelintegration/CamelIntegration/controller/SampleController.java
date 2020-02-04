package com.bper.camelintegration.CamelIntegration.controller;

import org.apache.camel.*;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.DefaultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    ServletContext context;

    @Produce()
    private ProducerTemplate preProcessingTemplate;

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    @ResponseBody
    public String ping() {
        logger.debug("executing ping method");


        String implementationVersion = getClass().getPackage().getImplementationVersion();

        try {
            if (implementationVersion == null) {
                Properties properties = new Properties();
                InputStream resourceAsStream = context.getResourceAsStream("/META-INF/MANIFEST.MF");

                if (resourceAsStream != null) {
                    properties.load(resourceAsStream);
                    implementationVersion = properties.getProperty("Implementation-Version");
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        Message message = new DefaultMessage(preProcessingTemplate.getCamelContext());
        Exchange exchange = new DefaultExchange(preProcessingTemplate.getCamelContext());
        exchange.setIn(message);
        exchange.setProperty("operation", "ricerca");

        preProcessingTemplate.sendBody("direct:mainRoute", exchange);

        return "Pooooooooooooooooooooooooooong ... " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " version: " + implementationVersion+"\n";
    }
}
