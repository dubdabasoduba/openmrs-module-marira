var visitOperations = {};

visitOperations.getVisitNoteEncounter = function(patientUuid, sectionId, encounterType){
    var params = [];
    params['encounterType'] = encounterType;
    emr.getFragmentActionWithCallback('marira', 'mariraPatientSearch', 'showEncounter', params,
        function(data){
            if(data == true){
                visitOperations.getEncounter(patientUuid, sectionId, encounterType);
            }
        }
    );
};

visitOperations.getEncounter = function(patientUuid, sectionId, encounterType){
    jQuery("#" + sectionId).empty();
    var params = [];
    params['patientUuid'] = patientUuid;
    params['encounterType'] = encounterType;
    emr.getFragmentActionWithCallback('marira', 'mariraPatientSearch', 'getEncounter', params,
        function(data){
            if(data != null && data.encounterId != null){
                visitOperations.getEncounterDetails(patientUuid, sectionId, data.encounterId);
            }
        }
    );
}

visitOperations.getEncounterDetails = function(patientUuid, sectionId, encounterId){
    var params = [];
    params['encounterId'] = encounterId;
    emr.getFragmentActionWithCallback('coreapps', 'visit/visitDetails', 'getEncounterDetails', params,
        function(data) {
            if(data == null){
                return;
            }

            var formatDiagnosis = '';
            var diagnoses = [];
            data.diagnoses.forEach(function (diagnosis, i){
                if(diagnoses.indexOf(diagnosis.question) == -1){
                    formatDiagnosis += "<div style='word-wrap: break-word;width:900px'><b>";
                    formatDiagnosis += diagnosis.question + ":</b> ";
                    diagnoses.push(diagnosis.question);
                }

                formatDiagnosis += diagnosis.answer + "</div>";
            });

            data.observations.forEach(function (observation){
                formatDiagnosis += "<div style='word-wrap: break-word;width:900px'><b>";
                formatDiagnosis += observation.question + ":</b> ";
                formatDiagnosis += observation.answer + "</div>";
                formatDiagnosis += "<br />";
            });

            formatDiagnosis = formatDiagnosis.replace(/\n/ig, '<br />');

            jQuery("#" + sectionId).html(formatDiagnosis);
        }
    );
}
