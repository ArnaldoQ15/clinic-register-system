/* Lista de tabelas e colunas para criar

   * all_person
   - person_id (serial) (LONG)
   - person_name (STRING)
   - person_age (INTEGER)
   - person_email (STRING)
   - person_sex (STRING)
/  - person_phone_id (FK)
   - person_document_cpf (INTEGER)
   - person_document_rg (INTEGER)
/   - person_address_id (FK)
   - person_register_date (DATE)

   * person_phone
/   - person_id (FK) (LONG)
   - phone_id (LONG)
   - phone_type (STRING)
   - phone_number (INTEGER)
   - phone_name (STRING)

   * person_address
/   - person_id (FK) (LONG)
   - address_id (LONG)
   - address_street (STRING)
   - address_number (STRING)
   - address_district (STRING)
   - address_complement (STRING)
   - address_state (STRING)
   - address_city (STRING)
   - address_postal_code (INTEGER)

    * person_doctor
/   - person_id (FK) (LONG)
   - doctor_id (LONG)
   - professional_register_number (INTEGER)
   - professional_register_state (STRING)
   - professional_register_validity (DATE)
   - doctor_especiality (STRING)
   - doctor_agenda

   * doctor_agenda
   - doctor_id (FK) (LONG)
   - funcionary_id (FK) (LONG)
   - agenda_id (LONG)
   - agenda_scale (STRING)
   - agenda_hours_list (STRING)
   - agenda_appointments_id (LONG)
   - doctor_choosed_days (STRING)
   - agenda_week_days (STRING)

    * person_funcionary ***ADICIONAR DOCTOR***
   - person_id (FK) (LONG)
   - funcionary_id (LONG)
   - funcionary_role (STRING)
   - funcionary_admission_date (DATE)
   - funcionary_email (STRING)

   * doctor_appointments
   - person_id (FK) (LONG)
   - doctor_id (FK) (LONG)
   - appointment_id (LONG)

   * person_pacient
   - person_id (FK) (LONG)
   - pacient_id (LONG)
   - pacient_child_id (FK) (LONG)
   - pacient_health_insurance_id (FK) (LONG)
   - pacient_prontuary_id (FK) (LONG)

   * pacient_child
   - person_id (FK) (LONG)
   - pacient_id (FK) (LONG)
   - pacient_child_id (LONG)
   - pacient_child_name (STRING)
   - pacient_child_age (INTEGER)
   - pacient_child_sex (STRING)
   - pacient_child_printed_term (BOOLEAN)

   * pacient_health_insurance
   - person_id (FK) (LONG)
   - pacient_id (FK) (LONG)
   - health_insurance_name (STRING)
   - health_insurance_number (INTEGER)
   - health_insurance_coverage (STRING)

   * pacient_prontuary
   - person_id (FK) (LONG)
   - pacient_id (FK) (LONG)
   - pacient_child_id (FK) (LONG)
   - pacient_prontuary_id (LONG)
   - pacient_first_time (BOOLEAN)
   - pacient_symptoms (STRING)

   ---------------------------------------------
   ---------------------------------------------
   ---------------------------------------------
   ---------DAQUI PRA BAIXO AINDA NÃO FOI CRIADO
   ---------------------------------------------
   ---------------------------------------------
   ---------------------------------------------

   * consults
   - person_id (FK) (LONG)
   - pacient_id (FK) (LONG)
   - consult_id (LONG)
   - consult_especiality (STRING)
   - consult_doctor (STRING)

   * consult_status
   - pacient_id (FK) (LONG) NN
   - consult_id (FK) (LONG) NN
   - consult_status_id (LONG) NN
   - consult_status_description (STRING) NN
   - consult_status_register_date (DATE) NN


 */