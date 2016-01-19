package org.openmrs.module.marira;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.marira.api.model.MariraSettings;

/**
 * Helper class to load and save the marira module global settings.
 */
public class ModuleSettings {
	private static final String PRINT_PATIENT_DETAILS = "marira.printPatientDetailsReport";
	private static final String PATIENT_DETAILS_REPORT_ID_PROPERTY = "marira.patientDetailsReportId";

	protected ModuleSettings() {}

	public static MariraSettings loadSettings() {
		MariraSettings mariraSettings = new MariraSettings();
		AdministrationService administrationService = Context.getAdministrationService();

		String property = administrationService.getGlobalProperty(PRINT_PATIENT_DETAILS);
		if (!StringUtils.isEmpty(property)) {
			mariraSettings.setPrintPatientsDetails(Boolean.parseBoolean(property));
		} else {
			mariraSettings.setPrintPatientsDetails(Boolean.FALSE);
		}

		property = administrationService.getGlobalProperty(PATIENT_DETAILS_REPORT_ID_PROPERTY);
		if (!StringUtils.isEmpty(property)) {
			mariraSettings.setPatientsDetailsReportId(Integer.parseInt(property));
		}

		return mariraSettings;
	}

	public static void saveSettings(MariraSettings mariraSettings) {
		if (mariraSettings == null) {
			throw new IllegalArgumentException("The settings to save must be defined.");
		}
	}
}
