/* Lista de tabelas e colunas para criar

   * all_person
   - person_id (serial)
   - person_name
   - person_age
   - person_email
   - person_sex
   - person_phone
   - person_register_date

   * person_phone
   - person_id (FK)
   - person_name (FK)
   - person_phone_type
   - person_phone_number
   - person_phone_name

    * person_doctor
   - person_id (FK)
   - person_name (FK)
   - professional_register_number
   - professional_register_state
   - professional_register_validity
   - doctor_especiality
   - doctor_agenda

    * person_funcionary
   - person_id (FK)
   - person_name (FK)
   - funcionary_id
   - funcionary_role
   - funcionary_admission_date
   - funcionary_email

   * doctor_agenda
   - person_id (FK)
   - person_name(FK)
   - funcionary_id (FK)
   - agenda_id
   - agenda_scale


 */