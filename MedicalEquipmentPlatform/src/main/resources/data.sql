INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Tone Hadzica 18',
        'Novi Sad',
        'Srbija',
        '21000'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Fruskogorska 22',
        'Novi Sad',
        'Srbija',
        '21000'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Rumenacka 4',
        'Novi Sad',
        'Srbija',
        '21000'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Nikole Tesle 88',
        'Novi Sad',
        'Srbija',
        '2100'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Futoski put bb',
        'Novi Sad',
        'Srbija',
        '21000'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Marka Kraljevica 12',
        'Beograd',
        'Srbija',
        '11000'
    );

INSERT INTO
    address(
        address,
        city,
        country,
        zip_code
    )
VALUES (
        'Bulevar Kralja Aleksandra 3a',
        'Beograd',
        'Srbija',
        '11000'
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (3.9, 'Benu', 'Lanac apoteka', 1);

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        4.2,
        'DrMax',
        'Lanac apoteka',
        2
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        3.6,
        'Jankovic',
        'Lanac apoteka',
        3
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        4.1,
        'Specijalisticka radnja MM',
        'Privatna radnja',
        4
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        4.9,
        'Medtronic',
        'Kompanija medicinske opreme',
        5
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        3.6,
        'Cardinal Health',
        'Kompanija medicinske opreme',
        6
    );

INSERT INTO
    company(
        average_rating,
        company_name,
        description,
        address_id
    )
VALUES (
        3.9,
        'GE Healthcare',
        'Kompanija medicinske opreme',
        7
    );

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Plasters', 10000, 1);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Surgical mask', 100000, 1);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Urine sample', 200, 1);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Cotton wool', 3000, 1);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Stethoscope', 25, 2);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Scissor', 30, 2);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Gauze', 1000, 2);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Rubber gloves', 6000, 2);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Thermometer', 10, 2);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('CT Scenner', 2, 7);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('X-Ray Machine', 4, 7);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Surgical lights', 10, 7);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('ECG Machine', 3, 6);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Thermometer', 10, 6);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Fetal monitor', 10, 6);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES ('Patient monitor', 10, 6);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('ECG paper', 100, 3);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Electrodes', 200, 3);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Oxygen mask', 50, 3);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES (
        'Blood pressure monitor',
        45,
        4
    );

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('X-ray', 200, 4);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Scalpel', 100, 4);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Wheelchair', 20, 5);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Crutch', 35, 5);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Needle', 1200, 5);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Electrodes', 200, 5);

INSERT INTO
    equipment(name, quantity, company_id)
VALUES('Stethoscope', 40, 5);

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'miloslala@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Milos',
        'Mirnic',
        'Novi Sad',
        'Srbija',
        '0612345467',
        'ROLE_SYSTEM_ADMIN'
    );

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'anja00dim@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Anja',
        'Dimitrijevic',
        'Novi Sad',
        'Srbija',
        '0608798767',
        'ROLE_COMPANY_ADMIN'
    );

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'dragasvircevic@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Draga',
        'Svircevic',
        'Novi Sad',
        'Srbija',
        '0657869000',
        'ROLE_COMPANY_ADMIN'
    );

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'milicajoca@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Milica',
        'Gugleta',
        'Novi Sad',
        'Srbija',
        '0612345412',
        'ROLE_COMPANY_ADMIN'
    );

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'natalisubasic@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Natalija',
        'Subasic',
        'Novi Sad',
        'Srbija',
        '0645673422',
        'ROLE_COMPANY_ADMIN'
    );

INSERT INTO
    users(
        email,
        password,
        first_name,
        last_name,
        city,
        country,
        phone_number,
        role
    )
VALUES (
        'anasesa03@gmail.com',
        '$2a$10$6x5hs7PPoGLhzHn4FFThMeH1VohLh6BkzI6xx/EBR5pRuWeo0q/Ji',
        'Ana',
        'Sesa',
        'Novi Sad',
        'Srbija',
        '0675874666',
        'ROLE_USER'
    );

INSERT INTO system_admins(user_id) VALUES(1);

INSERT INTO company_admins(user_id, company_id) VALUES(2, 1);

INSERT INTO company_admins(user_id, company_id) VALUES(3, 2);

INSERT INTO company_admins(user_id, company_id) VALUES(4, 3);

INSERT INTO company_admins(user_id, company_id) VALUES(5, 4);

INSERT INTO
    regular_users(
        user_id,
        is_enabled,
        company_information,
        profession
    )
VALUES (
        6,
        true,
        'Privatna bolnica NewHospital',
        'Secretary'
    );

insert into
    appointment(
        date,
        duration,
        company_admin_id,
        regular_user_id
    )
values
('2023-12-25', '20', 2, null);