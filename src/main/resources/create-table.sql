CREATE TABLE public.person(
	person_id SERIAL PRIMARY KEY,
	person_name VARCHAR(255) NOT NULL,
	person_age INT NOT NULL,
	person_email VARCHAR(255) NOT NULL,
	person_sex VARCHAR(255) NOT NULL,
	person_birthday DATE NULL,
	person_document_cpf VARCHAR(255) NOT NULL,
	person_document_rg VARCHAR(255) NULL,
	person_register_date TIMESTAMP NOT NULL,
	person_last_update TIMESTAMP NULL,
	person_status BOOL NOT NULL
);

CREATE TABLE public.person_address(
	address_id SERIAL PRIMARY KEY,
	"state" VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	district VARCHAR(255) NOT NULL,
	street VARCHAR(255) NOT NULL,
	"number" VARCHAR(255) NOT NULL,
	complement VARCHAR(255) NULL,
	postal_code INT NOT NULL,
	register_date TIMESTAMP NOT NULL,
    last_update TIMESTAMP NULL,
	address_status BOOL NOT NULL,
	person_id SERIAL NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE public.person_doctor (
	person_id SERIAL PRIMARY KEY,
	doctor_name VARCHAR(255) NOT NULL,
	medical_especiality VARCHAR(255) NOT NULL,
	professional_register_number INT NOT NULL,
	professional_register_state VARCHAR(255) NOT NULL,
	professional_register_validity DATE NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE public.person_doctor_agenda (
	agenda_id SERIAL PRIMARY KEY,
	data_status BOOL NOT NULL,
	doctor_name VARCHAR(255) NOT NULL,
	person_id SERIAL NOT NULL,
	medical_especiality VARCHAR(255) NOT NULL,
	day_week VARCHAR(255) NOT NULL,
	last_update TIMESTAMP NULL,
	morning0800 BOOL NOT NULL,
	morning0830 BOOL NOT NULL,
	morning0900 BOOL NOT NULL,
	morning0930 BOOL NOT NULL,
	morning1000 BOOL NOT NULL,
	morning1030 BOOL NOT NULL,
	morning1100 BOOL NOT NULL,
	morning1130 BOOL NOT NULL,
	afternoon1400 BOOL NOT NULL,
	afternoon1430 BOOL NOT NULL,
	afternoon1500 BOOL NOT NULL,
	afternoon1530 BOOL NOT NULL,
	afternoon1600 BOOL NOT NULL,
	afternoon1630 BOOL NOT NULL,
	afternoon1700 BOOL NOT NULL,
	afternoon1730 BOOL NOT NULL,
	night1800 BOOL NOT NULL,
	night1830 BOOL NOT NULL,
	night1900 BOOL NOT NULL,
	night1930 BOOL NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person_doctor (person_id)
);

CREATE TABLE public.person_employee (
	person_id SERIAL PRIMARY KEY,
	employee_name VARCHAR(255) NOT NULL,
	"role" VARCHAR(255) NOT NULL,
	admission_date VARCHAR(255) NOT NULL,
	shutdown_date DATE NULL,
	institucional_email VARCHAR(255) NULL,
	FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE public.person_pacient (
	person_id SERIAL PRIMARY KEY,
	pacient_name VARCHAR(255) NOT NULL,
	health_insurance_id SERIAL NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE public.person_pacient_child (
	child_id SERIAL PRIMARY KEY,
	child_name VARCHAR(255) NOT NULL,
	child_age INT NOT NULL,
	child_birthday DATE NOT NULL,
	child_sex VARCHAR(255) NOT NULL,
	register_date TIMESTAMP NOT NULL,
	last_update TIMESTAMP NULL,
	printed_term bool NOT NULL,
	person_id SERIAL NOT NULL,
	responsable VARCHAR(255) NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person_pacient (person_id)
);

CREATE TABLE public.person_pacient_health_insurance (
	health_insurance_id SERIAL PRIMARY KEY,
	coverage VARCHAR(255) NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"number" BIGINT NOT NULL,
	person_register_date TIMESTAMP NOT NULL,
	person_last_update TIMESTAMP NULL,
	person_id SERIAL NOT NULL
);

CREATE TABLE public.person_pacient_prontuary (
	prontuary_id SERIAL PRIMARY KEY,
	first_time bool NOT NULL,
	symptoms VARCHAR(255) NOT NULL,
	register_date TIMESTAMP NOT NULL,
	last_update TIMESTAMP NULL,
	person_id SERIAL NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person_pacient (person_id)
);

CREATE TABLE public.person_phone (
	phone_id SERIAL PRIMARY KEY,
	"number" VARCHAR(255) NOT NULL,
	person_phone_name VARCHAR(80) NULL,
	"type" VARCHAR(255) NOT NULL,
	register_date TIMESTAMP NOT NULL,
    last_update TIMESTAMP NULL,
    phone_status BOOL NOT NULL,
    person_id SERIAL NOT NULL,
	FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE public.consult (
	consult_id SERIAL PRIMARY KEY,
	person_pacient_person_id SERIAL NOT NULL,
	day_request VARCHAR(255) NOT NULL,
	hour_request VARCHAR(255) NOT NULL,
	medical_especiality VARCHAR(255) NOT NULL,
	person_doctor_person_id SERIAL NOT NULL,
	"status" VARCHAR(255) NOT NULL,
	register_date TIMESTAMP NOT NULL,
	last_status_update TIMESTAMP NULL,
	FOREIGN KEY (person_pacient_person_id) REFERENCES person_pacient (person_id),
	FOREIGN KEY (person_doctor_person_id) REFERENCES person_doctor (person_id)
);

ALTER TABLE public.person_pacient
    ADD CONSTRAINT person_pacient_health_insurance_person_id_fkey
        FOREIGN KEY (health_insurance_id)
            REFERENCES person_pacient_health_insurance(health_insurance_id);