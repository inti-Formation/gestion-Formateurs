
package com.adaming.myapp.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("exporterController")
@ApplicationScoped
public class ExporterController implements Serializable {

	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    private final Logger LOGGER  = Logger.getLogger("ExporterController");

	private Boolean customExporter;

	public ExporterController() {
		customExporter = false;
		LOGGER.info("init ExporterController");
	}

	public Boolean getCustomExporter() {
		return customExporter;
	}

	public void setCustomExporter(Boolean customExporter) {
		this.customExporter = customExporter;
	}
}