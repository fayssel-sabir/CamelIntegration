package com.bper.camelintegration.CamelIntegration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RicercaRequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("RicercaRequestProcessor ********************");
    }
}
