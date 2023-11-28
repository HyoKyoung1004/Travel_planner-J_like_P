package com.trip.project.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{
	
	@Value("${file.path}")
	private String documentRoot;
	
	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		if (factory instanceof TomcatServletWebServerFactory) {
            TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
            if (!StringUtils.isEmpty(documentRoot)) {
            	
                File root = new File(documentRoot);
                tomcat.setDocumentRoot(root);
//                tomcat.setContextPath("/img");
                //System.out.print(root);
                System.out.println(tomcat.getDocumentRoot());
            }
        }
	}
}