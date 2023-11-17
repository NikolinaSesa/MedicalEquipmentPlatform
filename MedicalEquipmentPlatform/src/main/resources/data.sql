INSERT INTO address(address, city, country, zip_code) VALUES('Tone Hadzica 18', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Fruskogorska 22', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Rumenacka 4', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Nikole Tesle 88', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Futoski put bb', 'Novi Sad', 'Srbija', '2100');

INSERT INTO company(average_rating, company_name, description, address_id) VALUES(3.9, 'Benu', 'Lanac apoteka', 1);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(4.2, 'Lilly', 'Lanac apoteka & drogerija', 2);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(3.6, 'DrMax', 'Lanac apoteka', 3);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(4.1, 'Specijalisticka radnja MM', 'Privatna radnja', 4);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(2.9, 'Hemofarm', 'Fabrika lekova', 5);
INSERT INTO users(city, company_information, country, email, first_name, last_name, password, phone_number, profession) VALUES('Novi Sad', 'Privatna bolica New Hospital', 'Srbija', 'nikolina123', 'Nikolina', 'Sesa', '123', '0643456576', 'Secretary');

INSERT INTO equipment(name, quantity, company_id) VALUES('Plasters', 10000, 1);
INSERT INTO equipment(name, quantity, company_id) VALUES('Surgical mask', 100000, 1);
INSERT INTO equipment(name, quantity, company_id) VALUES('Urine sample', 200, 1);
INSERT INTO equipment(name, quantity, company_id) VALUES('Cotton wool', 3000, 1);

INSERT INTO equipment(name, quantity, company_id) VALUES('Stethoscope', 25, 2);
INSERT INTO equipment(name, quantity, company_id) VALUES('Scissor', 30, 2);
INSERT INTO equipment(name, quantity, company_id) VALUES('Gauze', 1000, 2);
INSERT INTO equipment(name, quantity, company_id) VALUES('Rubber gloves', 6000, 2);
INSERT INTO equipment(name, quantity, company_id) VALUES('Thermometer', 10, 2);