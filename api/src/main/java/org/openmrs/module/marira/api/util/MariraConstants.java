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
}
