CREATE TABLE public.bank (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    bic CHAR(11) NOT NULL UNIQUE
);

CREATE TABLE public.subject (
    id SERIAL PRIMARY KEY,
    type VARCHAR(10) NOT NULL CHECK (type IN ('PERSON', 'LEGAL')),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE public.currency (
    id SERIAL PRIMARY KEY,
    code CHAR(3) NOT NULL UNIQUE
);

CREATE TABLE public.account (
    id SERIAL PRIMARY KEY,
    owner_id INTEGER NOT NULL REFERENCES subject(id),
    base_currency_id INTEGER NOT NULL REFERENCES public.currency(id),
	type VARCHAR(10) NOT NULL CHECK (type IN ('DEBIT', 'CREDIT')),
    bank_id INTEGER REFERENCES public.bank(id),
    iban VARCHAR(34) UNIQUE,
    balance NUMERIC(16, 2) NOT NULL DEFAULT 0.00,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE public.transaction (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL REFERENCES public.account(id),
    type VARCHAR(10) NOT NULL CHECK (type IN ('WITHDRAW', 'DEPOSIT')),
	currency_id INTEGER NOT NULL REFERENCES currency(id),
    amount NUMERIC(16,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO public.currency (code) VALUES
('USD'),
('EUR'),
('JPY'),
('GBP'),
('AUD'),
('CAD'),
('CHF'),
('CNY'),
('SEK'),
('NZD'),
('MXN'),
('SGD'),
('HKD'),
('NOK'),
('KRW'),
('TRY'),
('INR'),
('RUB'),
('BRL'),
('ZAR');

-- dummy data
INSERT INTO subject (type, name) VALUES
('PERSON', 'Emma Thompson'),
('PERSON', 'Liam Carter'),
('PERSON', 'Sofia Rodriguez'),
('PERSON', 'Noah Patel'),
('PERSON', 'Ava Kim'),
('PERSON', 'Ethan Nguyen'),
('PERSON', 'Isabella Martinez'),
('PERSON', 'Jacob Lee'),
('PERSON', 'Mia Johnson'),
('PERSON', 'Oliver Brown'),
('LEGAL', 'BrightStar Solutions'),
('LEGAL', 'GlobalTech Innovations'),
('LEGAL', 'SilverPeak Enterprises'),
('LEGAL', 'Horizon Consulting Group'),
('LEGAL', 'Unity Systems Inc.'),
('LEGAL', 'Pioneer Dynamics LLC'),
('LEGAL', 'Evergreen Ventures'),
('LEGAL', 'Nexus Analytics Corp.'),
('LEGAL', 'Summit Energy Ltd.'),
('LEGAL', 'BlueWave Technologies');

INSERT INTO bank (name, bic) VALUES
('Prime Horizon Bank', 'PHBNUS44XXX'),
('Unity Financial Group', 'UFGBGB22XXX'),
('Sterling Credit Bank', 'SCBKDE33XXX'),
('Pinnacle Trust Bank', 'PTBKFRPPXXX'),
('Evergreen Savings', 'EVSVCA2SXXX'),
('Summit National Bank', 'SNBKUS55XXX'),
('Blue Ridge Banking', 'BRBKESMMXXX'),
('Crestwood Financial', 'CWFIJPJTXXX'),
('Nova Capital Bank', 'NCBKAU4AXXX'),
('Liberty Commerce Bank', 'LCBKIT2RXXX');


INSERT INTO account (owner_id, base_currency_id, type, bank_id, iban, balance, name) VALUES
(1, 3, 'CREDIT', 7, 'ES12BRBK0000000000000001', 500000.00, 'Emma Thompson CREDIT JPY'),
(1, 12, 'DEBIT', 7, 'ES12BRBK0000000000000002', 500000.00, 'Emma Thompson DEBIT SGD'),
(2, 18, 'CREDIT', 3, 'DE33SCBK0000000000000003', 500000.00, 'Liam Carter CREDIT RUB'),
(2, 6, 'DEBIT', 3, 'DE33SCBK0000000000000004', 500000.00, 'Liam Carter DEBIT CAD'),
(3, 9, 'CREDIT', 1, 'US12PHBN0000000000000005', 500000.00, 'Sofia Rodriguez CREDIT SEK'),
(3, 14, 'DEBIT', 1, 'US12PHBN0000000000000006', 500000.00, 'Sofia Rodriguez DEBIT NOK'),
(4, 2, 'CREDIT', 9, 'AU12NCBK0000000000000007', 500000.00, 'Noah Patel CREDIT EUR'),
(4, 17, 'DEBIT', 9, 'AU12NCBK0000000000000008', 500000.00, 'Noah Patel DEBIT INR'),
(5, 5, 'CREDIT', 4, 'FR12PTBK0000000000000009', 500000.00, 'Ava Kim CREDIT AUD'),
(5, 11, 'DEBIT', 4, 'FR12PTBK0000000000000010', 500000.00, 'Ava Kim DEBIT MXN'),
(6, 20, 'CREDIT', 2, 'GB29UFGB0000000000000011', 500000.00, 'Ethan Nguyen CREDIT ZAR'),
(6, 7, 'DEBIT', 2, 'GB29UFGB0000000000000012', 500000.00, 'Ethan Nguyen DEBIT CHF'),
(7, 13, 'CREDIT', 10, 'IT12LCBK0000000000000013', 500000.00, 'Isabella Martinez CREDIT HKD'),
(7, 4, 'DEBIT', 10, 'IT12LCBK0000000000000014', 500000.00, 'Isabella Martinez DEBIT GBP'),
(8, 16, 'CREDIT', 6, 'US55SNBK0000000000000015', 500000.00, 'Jacob Lee CREDIT TRY'),
(8, 8, 'DEBIT', 6, 'US55SNBK0000000000000016', 500000.00, 'Jacob Lee DEBIT CNY'),
(9, 10, 'CREDIT', 8, 'JP12CWFI0000000000000017', 500000.00, 'Mia Johnson CREDIT NZD'),
(9, 19, 'DEBIT', 8, 'JP12CWFI0000000000000018', 500000.00, 'Mia Johnson DEBIT BRL'),
(10, 1, 'CREDIT', 5, 'CA12EVSV0000000000000019', 500000.00, 'Oliver Brown CREDIT USD'),
(10, 15, 'DEBIT', 5, 'CA12EVSV0000000000000020', 500000.00, 'Oliver Brown DEBIT KRW'),
(11, 6, 'CREDIT', 3, 'DE33SCBK0000000000000021', 500000.00, 'BrightStar Solutions CREDIT CAD'),
(11, 12, 'DEBIT', 3, 'DE33SCBK0000000000000022', 500000.00, 'BrightStar Solutions DEBIT SGD'),
(12, 9, 'CREDIT', 7, 'ES12BRBK0000000000000023', 500000.00, 'GlobalTech Innovations CREDIT SEK'),
(12, 1, 'DEBIT', 7, 'ES12BRBK0000000000000024', 500000.00, 'GlobalTech Innovations DEBIT USD'),
(13, 14, 'CREDIT', 1, 'US12PHBN0000000000000025', 500000.00, 'SilverPeak Enterprises CREDIT NOK'),
(13, 18, 'DEBIT', 1, 'US12PHBN0000000000000026', 500000.00, 'SilverPeak Enterprises DEBIT RUB'),
(14, 2, 'CREDIT', 9, 'AU12NCBK0000000000000027', 500000.00, 'Horizon Consulting Group CREDIT EUR'),
(14, 17, 'DEBIT', 9, 'AU12NCBK0000000000000028', 500000.00, 'Horizon Consulting Group DEBIT INR'),
(15, 5, 'CREDIT', 4, 'FR12PTBK0000000000000029', 500000.00, 'Unity Systems Inc. CREDIT AUD'),
(15, 11, 'DEBIT', 4, 'FR12PTBK0000000000000030', 500000.00, 'Unity Systems Inc. DEBIT MXN'),
(16, 20, 'CREDIT', 2, 'GB29UFGB0000000000000031', 500000.00, 'Pioneer Dynamics LLC CREDIT ZAR'),
(16, 7, 'DEBIT', 2, 'GB29UFGB0000000000000032', 500000.00, 'Pioneer Dynamics LLC DEBIT CHF'),
(17, 13, 'CREDIT', 10, 'IT12LCBK0000000000000033', 500000.00, 'Evergreen Ventures CREDIT HKD'),
(17, 4, 'DEBIT', 10, 'IT12LCBK0000000000000034', 500000.00, 'Evergreen Ventures DEBIT GBP'),
(18, 16, 'CREDIT', 6, 'US55SNBK0000000000000035', 500000.00, 'Nexus Analytics Corp. CREDIT TRY'),
(18, 8, 'DEBIT', 6, 'US55SNBK0000000000000036', 500000.00, 'Nexus Analytics Corp. DEBIT CNY'),
(19, 10, 'CREDIT', 8, 'JP12CWFI0000000000000037', 500000.00, 'Summit Energy Ltd. CREDIT NZD'),
(19, 19, 'DEBIT', 8, 'JP12CWFI0000000000000038', 500000.00, 'Summit Energy Ltd. DEBIT BRL'),
(20, 1, 'CREDIT', 5, 'CA12EVSV0000000000000039', 500000.00, 'BlueWave Technologies CREDIT USD'),
(20, 15, 'DEBIT', 5, 'CA12EVSV0000000000000040', 500000.00, 'BlueWave Technologies DEBIT KRW');


INSERT INTO transaction (account_id, type, currency_id, amount) VALUES
(1, 'DEPOSIT', 3,  500000.00),
(2, 'DEPOSIT', 12,  500000.00),
(3, 'DEPOSIT', 18,  500000.00),
(4, 'DEPOSIT', 6,  500000.00),
(5, 'DEPOSIT', 9,  500000.00),
(6, 'DEPOSIT', 14,  500000.00),
(7, 'DEPOSIT', 2,  500000.00),
(8, 'DEPOSIT', 17,  500000.00),
(9, 'DEPOSIT', 5,  500000.00),
(10, 'DEPOSIT', 11,  500000.00),
(11, 'DEPOSIT', 20,  500000.00),
(12, 'DEPOSIT', 7,  500000.00),
(13, 'DEPOSIT', 13,  500000.00),
(14, 'DEPOSIT', 4,  500000.00),
(15, 'DEPOSIT', 16,  500000.00),
(16, 'DEPOSIT', 8,  500000.00),
(17, 'DEPOSIT', 10,  500000.00),
(18, 'DEPOSIT', 19,  500000.00),
(19, 'DEPOSIT', 1,  500000.00),
(20, 'DEPOSIT', 15,  500000.00),
(21, 'DEPOSIT', 6,  500000.00),
(22, 'DEPOSIT', 12,  500000.00),
(23, 'DEPOSIT', 9,  500000.00),
(24, 'DEPOSIT', 1,  500000.00),
(25, 'DEPOSIT', 14,  500000.00),
(26, 'DEPOSIT', 18,  500000.00),
(27, 'DEPOSIT', 2,  500000.00),
(28, 'DEPOSIT', 17,  500000.00),
(29, 'DEPOSIT', 5,  500000.00),
(30, 'DEPOSIT', 11,  500000.00),
(31, 'DEPOSIT', 20,  500000.00),
(32, 'DEPOSIT', 7,  500000.00),
(33, 'DEPOSIT', 13,  500000.00),
(34, 'DEPOSIT', 4,  500000.00),
(35, 'DEPOSIT', 16,  500000.00),
(36, 'DEPOSIT', 8,  500000.00),
(37, 'DEPOSIT', 10,  500000.00),
(38, 'DEPOSIT', 19,  500000.00),
(39, 'DEPOSIT', 1,  500000.00),
(40, 'DEPOSIT', 15,  500000.00);

commit;
