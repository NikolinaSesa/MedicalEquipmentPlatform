INSERT INTO address(address, city, country, zip_code) VALUES('Tone Hadzica 18', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Fruskogorska 22', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Rumenacka 4', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Nikole Tesle 88', 'Novi Sad', 'Srbija', '2100');
INSERT INTO address(address, city, country, zip_code) VALUES('Futoski put bb', 'Novi Sad', 'Srbija', '2100');

INSERT INTO company(average_rating, company_name, description, address_id) VALUES(0.0, 'Benu', 'Lanac apoteka', 1);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(0.0, 'Lilly', 'Lanac apoteka & drogerija', 2);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(0.0, 'DrMax', 'Lanac apoteka', 3);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(0.0, 'Specijalisticka radnja MM', 'Privatna radnja', 4);
INSERT INTO company(average_rating, company_name, description, address_id) VALUES(0.0, 'Hemofarm', 'Fabrika lekova', 5);
INSERT INTO users(city, company_information, country, email, first_name, last_name, password, phone_number, profession) VALUES('Novi Sad', 'Privatna bolica New Hospital', 'Srbija', 'nikolina123', 'Nikolina', 'Sesa', '123', '0643456576', 'Secretary');