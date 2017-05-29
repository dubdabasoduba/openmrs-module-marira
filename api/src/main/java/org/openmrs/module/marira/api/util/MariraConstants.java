package org.openmrs.module.marira.api.util;

/**
 * Constants class for the module.
 */
public class MariraConstants {
	public static final String MODULE_ID = "marira";
	protected static final String MODULE_BASE = "/module/";
	public static final String MODULE_ROOT = MODULE_BASE + MODULE_ID + "/";
	public static final String JASPER_REPORT_PATIENT = MODULE_ROOT + "patientReport";

	public static final String MANAGE_MODULE_PAGE_EXTENSION_POINT_ID = "org.openmrs.module.marira.manage.module";

	public static final String LANDING_PAGE_EXTENSION_POINT_ID = "org.openmrs.module.marira.landing";

	public static final String APP_VIEW_VISIT_NOTE_ENCOUNTER = "App: View visit note encounter on the Patient Search";
	public static final String APP_VIEW_LAB_PRESCRIPTION_ENCOUNTER =
	        "App: View lab prescription encounter on the Patient Search";

	public static final String SHOW_VISIT_NOTE_ENCOUNTER = MODULE_ID + ".showVisitNoteEncounter";
	public static final String VISIT_NOTE_ENCOUNTER_TYPE_UUID = MODULE_ID + ".visitNoteEncounterTypeUuid";
	public static final String LAB_PRESCRIPTION_ENCOUNTER_TYPE_UUID = MODULE_ID + ".labPrescriptionEncounterTypeUuid";
	public static final String SHOW_LAB_PRESCRIPTION_ENCOUNTER = MODULE_ID + ".showLabPrescriptionEncounter";
}
