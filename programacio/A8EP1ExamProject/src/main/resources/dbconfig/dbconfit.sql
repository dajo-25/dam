DROP TABLE IF EXISTS payroll;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS category;

CREATE TABLE employee (
    dni         varchar(16)      PRIMARY KEY,
    name        varchar(128),
    age         int,
    email       varchar(128)
);

CREATE TABLE category (
    name            varchar(64)     PRIMARY KEY,
    base_salary     int
);

CREATE TABLE payroll (
    id                  serial          PRIMARY KEY,
    irpf                int,
    month               int,
    year                int,
    dni_employee        varchar(16)     REFERENCES employee (dni),
    name_cat            varchar(64)     REFERENCES category (name)
);

INSERT INTO employee VALUES ('448-34-6538', 'Maryl Blainey', 60, 'mblainey0@nytimes.com');
INSERT INTO employee VALUES ('296-62-0322', 'Werner Carrivick', 36, 'wcarrivick1@deliciousdays.com');
INSERT INTO employee VALUES ('858-87-1031', 'Penelope McPolin', 27, 'pmcpolin2@indiegogo.com');
INSERT INTO employee VALUES ('407-69-6788', 'Kiah Bulley', 44, 'kbulley3@amazonaws.com');
INSERT INTO employee VALUES ('197-72-7644', 'Zuzana Bramstom', 35, 'zbramstom4@simplemachines.org');
INSERT INTO employee VALUES ('173-86-6585', 'Fern Mangenot', 59, 'fmangenot5@gravatar.com');
INSERT INTO employee VALUES ('243-28-9318', 'Efrem Rushton', 67, 'erushton6@ask.com');
INSERT INTO employee VALUES ('350-70-6471', 'Renelle Simoncello', 21, 'rsimoncello7@cocolog-nifty.com');
INSERT INTO employee VALUES ('374-13-2171', 'Joshua McEvilly', 41, 'jmcevilly8@1688.com');
INSERT INTO employee VALUES ('787-06-5796', 'Ruddy Nafziger', 36, 'rnafziger9@booking.com');
INSERT INTO employee VALUES ('376-27-8749', 'Cary Corbie', 32, 'ccorbiea@amazon.co.uk');
INSERT INTO employee VALUES ('105-34-8324', 'Trace Melley', 43, 'tmelleyb@home.pl');
INSERT INTO employee VALUES ('273-17-6952', 'Hort Balfour', 28, 'hbalfourc@reuters.com');
INSERT INTO employee VALUES ('159-67-0189', 'Tedra Ketchaside', 42, 'tketchasided@ebay.co.uk');
INSERT INTO employee VALUES ('337-65-3914', 'Rasia Leele', 64, 'rleelee@army.mil');
INSERT INTO employee VALUES ('872-55-4968', 'De Glading', 53, 'dgladingf@51.la');
INSERT INTO employee VALUES ('629-14-7299', 'Gerhard Tullis', 32, 'gtullisg@newsvine.com');
INSERT INTO employee VALUES ('656-21-0284', 'Analise Whithalgh', 38, 'awhithalghh@fda.gov');
INSERT INTO employee VALUES ('120-32-4527', 'Danyelle Scourfield', 52, 'dscourfieldi@xinhuanet.com');
INSERT INTO employee VALUES ('186-29-5613', 'Normie De Biasio', 42, 'ndebiasioj@cbslocal.com');

INSERT INTO category VALUES ('VP Marketing', 3488);
INSERT INTO category VALUES ('Media Manager II', 3238);
INSERT INTO category VALUES ('Research Associate', 1185);
INSERT INTO category VALUES ('Compensation Analyst', 1976);
INSERT INTO category VALUES ('Financial Advisor', 3376);
INSERT INTO category VALUES ('Food Chemist', 2442);
INSERT INTO category VALUES ('Professor', 1177);
INSERT INTO category VALUES ('Help Desk Technician', 1584);

INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (21, 1, 2018, '376-27-8749', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (17, 3, 2015, '872-55-4968', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (23, 6, 2015, '374-13-2171', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (16, 2, 2021, '787-06-5796', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (16, 3, 2020, '350-70-6471', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (14, 7, 2018, '273-17-6952', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (2, 4, 2017, '243-28-9318', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (18, 10, 2020, '350-70-6471', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (12, 4, 2017, '858-87-1031', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (2, 4, 2022, '656-21-0284', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (9, 6, 2018, '243-28-9318', 'Food Chemist');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (18, 3, 2016, '105-34-8324', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (11, 10, 2019, '448-34-6538', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (3, 4, 2022, '120-32-4527', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (11, 10, 2020, '120-32-4527', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (8, 12, 2016, '197-72-7644', 'VP Marketing');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (21, 11, 2015, '337-65-3914', 'Help Desk Technician');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (23, 6, 2020, '243-28-9318', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (24, 5, 2022, '407-69-6788', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (11, 1, 2015, '186-29-5613', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (12, 10, 2022, '105-34-8324', 'Food Chemist');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (23, 6, 2021, '872-55-4968', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (24, 11, 2020, '656-21-0284', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (12, 1, 2015, '376-27-8749', 'Help Desk Technician');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (16, 2, 2019, '407-69-6788', 'VP Marketing');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (10, 4, 2021, '872-55-4968', 'Food Chemist');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (18, 10, 2017, '173-86-6585', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (15, 10, 2022, '197-72-7644', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (7, 6, 2016, '186-29-5613', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (7, 12, 2019, '173-86-6585', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (17, 5, 2015, '656-21-0284', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (9, 4, 2021, '186-29-5613', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (8, 8, 2015, '350-70-6471', 'VP Marketing');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (23, 6, 2020, '374-13-2171', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (4, 4, 2019, '197-72-7644', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (16, 6, 2018, '337-65-3914', 'Help Desk Technician');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (10, 10, 2021, '159-67-0189', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (9, 12, 2015, '872-55-4968', 'Food Chemist');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (24, 3, 2015, '376-27-8749', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (8, 4, 2016, '337-65-3914', 'Research Associate');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (21, 3, 2021, '350-70-6471', 'Food Chemist');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (19, 10, 2021, '120-32-4527', 'VP Marketing');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (15, 6, 2020, '296-62-0322', 'VP Marketing');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (10, 2, 2022, '173-86-6585', 'Help Desk Technician');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (6, 7, 2021, '337-65-3914', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (11, 2, 2018, '273-17-6952', 'Compensation Analyst');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (17, 10, 2022, '872-55-4968', 'Financial Advisor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (15, 3, 2022, '407-69-6788', 'Professor');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (20, 8, 2022, '273-17-6952', 'Media Manager II');
INSERT INTO payroll (irpf, month, year, dni_employee, name_cat) VALUES (21, 6, 2015, '105-34-8324', 'VP Marketing');