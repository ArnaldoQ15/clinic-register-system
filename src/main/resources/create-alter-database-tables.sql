CREATE DATABASE clinic_register;

    CREATE TABLE all_person(
        person_id SERIAL PRIMARY KEY,
        person_name VARCHAR(255) NOT NULL,
        person_age SMALLINT NOT NULL,
        person_sex VARCHAR(255) NOT NULL,
        person_phone_id SERIAL NOT NULL,
        person_document_cpf INT NOT NULL,
        person_document_rg INT NOT NULL,
        person_address_id SERIAL NOT NULL,
        person_register_date TIMESTAMP NOT NULL
    );

    CREATE TABLE person_phone(
        person_id SERIAL NOT NULL,
        phone_id SERIAL PRIMARY KEY,
        phone_type VARCHAR(255) NOT NULL,
        phone_number INT NOT NULL,
        phone_name VARCHAR(255)
    );

    CREATE TABLE person_address(
        person_id SERIAL NOT NULL,
        address_id SERIAL PRIMARY KEY,
        address_street VARCHAR(255) NOT NULL,
        address_number VARCHAR(255) NOT NULL,
        address_district VARCHAR(255) NOT NULL,
        address_complement VARCHAR(255),
        address_state VARCHAR(255) NOT NULL,
        address_city VARCHAR(255) NOT NULL,
        address_postal_code INT NOT NULL
    );

    CREATE TABLE person_doctor(
        person_id SERIAL NOT NULL,
        doctor_id SERIAL PRIMARY KEY,
        professional_register_number INT NOT NULL,
        professional_register_state VARCHAR(255) NOT NULL,
        professional_register_validity DATE NOT NULL,
        doctor_especiality VARCHAR(255) NOT NULL,
        agenda_id INT NOT NULL
    );

    CREATE TABLE doctor_agenda(
        doctor_id SERIAL NOT NULL,
        funcionary_id SERIAL NOT NULL,
        agenda_id SERIAL PRIMARY KEY,
        agenda_scale VARCHAR(255),
        agenda_hours_list VARCHAR(255),
        doctor_choosed_days VARCHAR(255),
        agenda_week_days VARCHAR(255)
    );

    CREATE TABLE person_funcionary(
        person_id SERIAL NOT NULL,
        funcionary_id SERIAL PRIMARY KEY,
        funcionary_role VARCHAR(255) NOT NULL,
        funcionary_admission_date DATE NOT NULL,
        funcionary_email VARCHAR(255) NOT NULL
    );

    CREATE TABLE doctor_appointments(
        person_id SERIAL NOT NULL,
        doctor_id SERIAL NOT NULL,
        appointment SERIAL PRIMARY KEY
    );

    CREATE TABLE person_pacient(
        person_id SERIAL NOT NULL,
        pacient_id SERIAL PRIMARY KEY,
        pacient_child_id SERIAL,
        pacient_health_insurance_id SERIAL,
        pacient_prontuary_id SERIAL NOT NULL
    );

    CREATE TABLE pacient_child(
        person_id SERIAL,
        pacient_id SERIAL,
        pacient_child_id SERIAL PRIMARY KEY,
        pacient_child_name VARCHAR(255),
        pacient_child_age INT,
        pacient_child_sex VARCHAR(255),
        pacient_child_printed_term BOOLEAN
    );

    CREATE TABLE pacient_health_insurance(
        person_id SERIAL,
        pacient_id SERIAL,
        health_insurance_id SERIAL PRIMARY KEY,
        health_insurance_name VARCHAR(255),
        health_insurance_number INT,
        health_insurance_coverage VARCHAR(255)
    );

    CREATE TABLE pacient_prontuary(
        person_id SERIAL NOT NULL,
        pacient_id SERIAL NOT NULL,
        pacient_child_id SERIAL NOT NULL,
        pacient_prontuary_id SERIAL PRIMARY KEY,
        pacient_first_time BOOLEAN,
        pacient_symptoms VARCHAR(255) NOT NULL
    );

/*
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - ALTER  TABLES - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - *
* - * - * - * - * - * - * - * - Adding the foreign keys to the appropriate tables - * - * - * - * - * - *
*/


    ALTER TABLE all_person
        ADD CONSTRAINT fk_phone_id
            FOREIGN KEY (person_phone_id)
                REFERENCES person_phone (phone_id);
    ALTER TABLE all_person
        ADD CONSTRAINT fk_address_id
            FOREIGN KEY (person_address_id)
                REFERENCES person_address (address_id);



    ALTER TABLE person_phone
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);



    ALTER TABLE person_address
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);



    ALTER TABLE person_doctor
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);



    ALTER TABLE doctor_agenda
        ADD CONSTRAINT fk_doctor_id
            FOREIGN KEY (doctor_id)
                REFERENCES person_doctor (doctor_id);



    ALTER TABLE person_funcionary
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);



    ALTER TABLE doctor_appointments
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);
    ALTER TABLE doctor_appointments
        ADD CONSTRAINT fk_doctor_id
            FOREIGN KEY (doctor_id)
                REFERENCES person_doctor (doctor_id);



    ALTER TABLE person_pacient
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);
    ALTER TABLE person_pacient
        ADD CONSTRAINT fk_pacient_child_id
            FOREIGN KEY (pacient_child_id)
                REFERENCES pacient_child (pacient_child_id);
    ALTER TABLE person_pacient
        ADD CONSTRAINT fk_pacient_health_insurance_id
            FOREIGN KEY (pacient_health_insurance_id)
                REFERENCES pacient_health_insurance (health_insurance_id);
    ALTER TABLE person_pacient
        ADD CONSTRAINT fk_pacient_prontuary_id
            FOREIGN KEY (pacient_prontuary_id)
                REFERENCES pacient_prontuary (pacient_prontuary_id);



    ALTER TABLE pacient_child
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);
    ALTER TABLE pacient_child
        ADD CONSTRAINT fk_pacient_id
            FOREIGN KEY (pacient_id)
                REFERENCES person_pacient (pacient_id);



    ALTER TABLE pacient_health_insurance
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);
    ALTER TABLE pacient_health_insurance
        ADD CONSTRAINT fk_pacient_id
            FOREIGN KEY (pacient_id)
                REFERENCES person_pacient (pacient_id);



    ALTER TABLE pacient_prontuary
        ADD CONSTRAINT fk_person_id
            FOREIGN KEY (person_id)
                REFERENCES all_person (person_id);
    ALTER TABLE pacient_prontuary
        ADD CONSTRAINT fk_pacient_id
            FOREIGN KEY (pacient_id)
                REFERENCES person_pacient (pacient_id);
    ALTER TABLE pacient_prontuary
        ADD CONSTRAINT fk_pacient_child_id
            FOREIGN KEY (pacient_child_id)
                REFERENCES pacient_child (pacient_child_id);