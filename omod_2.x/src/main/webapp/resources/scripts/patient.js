var visitOperations = {};

visitOperations.getVisitNoteEncounter = function(patientUuid, sectionId){
    emr.getFragmentActionWithCallback('marira', 'mariraPatientSearch', 'showVisitNoteEncounter',[],
        function(data){
            if(data == true){
                visitOperations.getEncounter(patientUuid, sectionId);
            }
        }
    );
};

visitOperations.getEncounter = function(patientUuid, sectionId){
    jQuery("#" + sectionId).empty();
    var params = [];
    params['patientUuid'] = patientUuid;
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
                    formatDiagnosis += '<span><b>' + diagnosis.question + ':</b> ';
                    diagnoses.push(diagnosis.question);
                }

                formatDiagnosis += diagnosis.answer + '</span>';
            });

            data.observations.forEach(function (observation){
                formatDiagnosis += '<span><b>' + observation.question + ':</b> ';
                formatDiagnosis += observation.answer + '</span>';
            });

            formatDiagnosis = formatDiagnosis.replace(/\n/ig, '<br />');

            jQuery("#" + sectionId).html(formatDiagnosis);
        }
    );
}
