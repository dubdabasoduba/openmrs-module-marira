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
            if(data != null){
                var formatDiagnosis = '<p>';
                var diagnoses = [];
                data.diagnoses.forEach(function (diagnosis, i){
                    if(diagnoses.indexOf(diagnosis.question) == -1){
                        formatDiagnosis += '<b>' + diagnosis.question + ':</b> ';
                        diagnoses.push(diagnosis.question);
                    }

                    formatDiagnosis += diagnosis.answer;
                    if(i + 1 < data.diagnoses.length){
                        formatDiagnosis += '<br />';
                    }
                });

                data.observations.forEach(function (observation){
                    if(observation.question == 'Text of encounter note'){
                        var answer = observation.answer;
                        answer = answer.replace(/\n/g, ' ');

                        if(answer.indexOf(':') == -1){
                            formatDiagnosis += '<br />';
                        }

                        answer.split(' ').forEach(function(word, i){
                            if(word.indexOf(':') > 0){
                                formatDiagnosis += '<br /><b>' + visitOperations.capitalizeFirstLetter(word) + '</b>';
                            } else {
                                if(i == 0){
                                    formatDiagnosis += '<br />';
                                }

                                formatDiagnosis += word + ' ';
                            }
                        });
                    }
                });
                formatDiagnosis += '</p>';

                jQuery("#" + sectionId).html(formatDiagnosis);
            }
        }
    );
}

visitOperations.capitalizeFirstLetter = function(word) {
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
};
