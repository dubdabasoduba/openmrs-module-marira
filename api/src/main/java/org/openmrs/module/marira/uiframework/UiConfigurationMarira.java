package org.openmrs.module.marira.uiframework;

import org.openmrs.module.openhmis.commons.uiframework.UiConfigurationFactory;

/**
 * The OpenMRS UI Framework configuration settings.
 */
public class UiConfigurationMarira extends UiConfigurationFactory {

	@Override
	public String getModuleId() {
		return "marira";
	}
}
