
package com.adaming.myapp.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.stereotype.Component;

@Component("exporterController")
@ApplicationScoped
public class ExporterController implements Serializable {

	private static final long serialVersionUID = 20120316L;

	private Boolean customExporter;

	public ExporterController() {
		customExporter = false;
	}

	public Boolean getCustomExporter() {
		return customExporter;
	}

	public void setCustomExporter(Boolean customExporter) {
		this.customExporter = customExporter;
	}
}