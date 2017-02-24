package org.openmrs.module.marira.fragment.controller;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.api.PatientService;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.module.marira.api.util.MariraConstants;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Patient search fragment logic
 */
public class MariraPatientSearchFragmentController {

	public String controller() {
		return "mariraPatientSearchFragment";
	}

	/**
	 * Searches for a 'Visit Note' encounter from patient's last active visit.
	 * @param patientUuid
	 * @param patientService
	 * @param visitService
	 * @return JSON
	 */
	public SimpleObject getEncounter(@RequestParam("patientUuid") String patientUuid,
	        @SpringBean("patientService") PatientService patientService,
	        @SpringBean("visitService") VisitService visitService) {
		if (StringUtils.isNotEmpty(patientUuid)) {
			Patient patient = patientService.getPatientByUuid(patientUuid);
			if (patient != null) {
				List<Visit> activeVisits = visitService.getActiveVisitsByPatient(patient);
				if (activeVisits.size() > 0) {
					Set<Encounter> encounters = activeVisits.get(0).getEncounters();
					String visitNoteEncounterTypeUuid = getVisitNoteEncounterTypeUuid();
					for (Encounter encounter : encounters) {
						if (StringUtils.isNotEmpty(visitNoteEncounterTypeUuid)
						        && encounter.getEncounterType()
						                .getUuid().equalsIgnoreCase(visitNoteEncounterTypeUuid)) {
							return SimpleObject.create("encounterId", encounter.getId());
						}
					}
				}
			}
		}

		return null;
	}

	public boolean showVisitNoteEncounter() {
		Boolean result = false;
		String property = Context.getAdministrationService().getGlobalProperty(
		    MariraConstants.SHOW_VISIT_NOTE_ENCOUNTER);
		if (!StringUtils.isEmpty(property)) {
			result = Boolean.parseBoolean(property);
		}

		return result;
	}

	private String getVisitNoteEncounterTypeUuid() {
		String result = null;
		String property = Context.getAdministrationService().getGlobalProperty(
		    MariraConstants.VISIT_NOTE_ENCOUNTER_TYPE_UUID);
		if (!StringUtils.isEmpty(property)) {
			result = property;
		}

		return result;
	}
}
