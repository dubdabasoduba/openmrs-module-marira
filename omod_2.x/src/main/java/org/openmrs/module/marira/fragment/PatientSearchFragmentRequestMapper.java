package org.openmrs.module.marira.fragment;

import org.openmrs.ui.framework.fragment.FragmentRequest;
import org.openmrs.ui.framework.fragment.FragmentRequestMapper;
import org.springframework.stereotype.Component;

/**
 * Overrides the patient search fragment
 */
@Component
public class PatientSearchFragmentRequestMapper implements FragmentRequestMapper {

	/**
	 * Implementations should call {@link FragmentRequest#setProviderNameOverride(String)} and
	 * {@link FragmentRequest#setFragmentIdOverride(String)}, and return true if they want to remap a request, or return
	 * false if they didn't remap it.
	 * @param request may have its providerNameOverride and pageNameOverride set
	 * @return true if this page was mapped (by overriding the provider and/or page), false otherwise
	 */
	public boolean mapRequest(FragmentRequest request) {
		if (request.getProviderName().equals("openhmis.commons")) {
			if (request.getFragmentId().equals("patientSearchFragment")) {
				request.setProviderNameOverride("marira");
				request.setFragmentIdOverride("mariraPatientSearch");
				return true;
			}
		}
		return false;
	}
}
