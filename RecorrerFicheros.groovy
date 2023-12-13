import groovy.io.FileType

def procesarCaso(texto) {
    def t = new XmlSlurper().parseText(texto)
    
    def linea = []
    linea.add(t.patient.bcr_patient_barcode)
    linea.add(t.patient.tissue_source_site)
    
    linea.add(t.patient.stage_event.pathologic_stage)
    linea.add(t.patient.stage_event.tnm_categories.pathologic_categories.pathologic_T)
    linea.add(t.patient.stage_event.tnm_categories.pathologic_categories.pathologic_N)
    linea.add(t.patient.stage_event.tnm_categories.pathologic_categories.pathologic_M)
    
    linea.add(t.patient.age_at_initial_pathologic_diagnosis)
    linea.add(t.patient.other_dx[0])
    linea.add(t.patient.gender)
    linea.add(t.patient.race_list.race)
    linea.add(t.patient.ethnicity)
    
    linea.add(t.patient.adenocarcinoma_invasion)
    linea.add(t.patient.histological_type)
    linea.add(t.patient.person_neoplasm_cancer_status)
    linea.add(t.patient.year_of_initial_pathologic_diagnosis)
    linea.add(t.patient.initial_pathologic_diagnosis_method)
    linea.add(t.patient.surgery_performed_type)
    linea.add(t.patient.number_of_lymphnodes_positive_by_he)
    linea.add(t.patient.number_of_lymphnodes_positive_by_ihc)
    linea.add(t.patient.neoplasm_histologic_grade)
    linea.add(t.patient.maximum_tumor_dimension)
    linea.add(t.patient.residual_tumor)
    linea.add(t.patient.history_of_diabetes)
    linea.add(t.patient.family_history_of_cancer)
    
    linea.add(t.patient.radiation_therapy)
    linea.add(t.patient.postoperative_rx_tx)
    linea.add(t.patient.primary_therapy_outcome_success)
    linea.add(t.patient.new_tumor_events.new_tumor_event_after_initial_treatment)
    linea.add(t.patient.new_tumor_events.new_tumor_event.days_to_new_tumor_event_after_initial_treatment.precision)
    linea.add(t.patient.drugs.drug[0].therapy_types.therapy_type)
    linea.add(t.patient.drugs.drug[0].tx_on_clinical_trial)
    linea.add(t.patient.drugs.drug[0].drug_name)
    linea.add(t.patient.tobacco_smoking_history)

    
    println linea.join(',')
}



def list = []

def dir = new File("C:/Users/joserbb/Desktop/WIP/DT/Datos/Completos")
dir.eachFileRecurse (FileType.FILES) { file ->
  list << file
}

def numCaso = 1
list.eachWithIndex {it,index->
  if (it.path.contains("org_clinical.")) {
     
      if (numCaso >= 150 && numCaso < 200) {
          //print(it.path)
          def xml =new File(it.path).text
          procesarCaso(xml)
      }
      numCaso++
  }
      
}
print 'END'
