/* Lista de tabelas e colunas para criar

   * all_person
   - person_id (serial) (LONG)
   - person_name (STRING)
   - person_age (INTEGER)
   - person_email (STRING)
   - person_sex (STRING)
   - person_phone (INTEGER)
   - person_document_cpf (INTEGER)
   - person_document_rg (INTEGER)
   - person_address (STRING)
   - person_register_date (DATE)

   * person_phone
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - person_phone_type (STRING)
   - person_phone_number (INTEGER)
   - person_phone_name (STRING)

   * person_address
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - person_address_street (STRING)
   - person_address_number (INTEGER)
   - person_address_district (STRING)
   - person_address_complement (STRING)
   - person_address_state (STRING)
   - person_address_city (STRING)
   - person_address_postal_code (INTEGER)

    * person_doctor
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - doctor_id (LONG)
   - professional_register_number (INTEGER)
   - professional_register_state (STRING)
   - professional_register_validity (DATE)
   - doctor_especiality (STRING)
   - doctor_agenda

    * person_funcionary
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - funcionary_id (LONG)
   - funcionary_role (STRING)
   - funcionary_admission_date (DATE)
   - funcionary_email (STRING)

   * doctor_agenda
   - person_id (FK) (LONG)
   - person_name(FK) (STRING)
   - funcionary_id (FK) (LONG)
   - agenda_id (LONG)
   - agenda_scale (STRING)
   - agenda_hours_list (STRING)
   - agenda_appointments
   - agenda_hours_available (STRING)

   * doctor_appointments
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - doctor_id (FK) (LONG)
   - appointment_id (LONG)

   * person_pacient
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - pacient_child_data
   - pacient_prontuary

   * consults
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - consults_id (LONG)
   - consults_especiality (STRING)
   - consults_doctor (STRING)

   * pacient_child
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - pacient_child_id (LONG)
   - pacient_child_name (STRING)
   - pacient_child_age (INTEGER)
   - pacient_child_sex (STRING)
   - pacient_child_printed_term (BOOLEAN)

   * pacient_health_insurance
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - person_document_cpf (FK) (INTEGER)
   - person_document_rg (FK) (INTEGER)
   - health_insurance_name (STRING)
   - health_insurance_number (INTEGER)
   - health_insurance_coverage (STRING)

   * pacient_prontuary
   - person_id (FK) (LONG)
   - person_name (FK) (STRING)
   - pacient_child_id (FK) (LONG)
   - pacient_prontuary_id (LONG)
   - pacient_symptoms (STRING)
   -



 */