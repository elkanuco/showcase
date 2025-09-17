# Dummy Data Description

This document describes the dummy data populated into the database tables for testing purposes.  
All tables have an ID primary key column, which is auto-incremented and starts from 1.

---

## Table: `currency`

| ID  | Code |
|------|-------|
| 1 | USD |
| 2 | EUR |
| 3 | JPY |
| 4 | GBP |
| 5 | AUD |
| 6 | CAD |
| 7 | CHF |
| 8 | CNY |
| 9 | SEK |
| 10 | NZD |
| 11 | MXN |
| 12 | SGD |
| 13 | HKD |
| 14 | NOK |
| 15 | KRW |
| 16 | TRY |
| 17 | INR |
| 18 | RUB |
| 19 | BRL |
| 20 | ZAR |

*Contains commonly used currency codes.*

---

## Table: `subject`

| ID | Type  | Name                    |
|----|-------|-------------------------|
| 1  | PERSON | Emma Thompson           |
| 2  | PERSON | Liam Carter             |
| 3  | PERSON | Sofia Rodriguez         |
| 4  | PERSON | Noah Patel              |
| 5  | PERSON | Ava Kim                 |
| 6  | PERSON | Ethan Nguyen            |
| 7  | PERSON | Isabella Martinez       |
| 8  | PERSON | Jacob Lee               |
| 9  | PERSON | Mia Johnson             |
| 10 | PERSON | Oliver Brown            |
| 11 | LEGAL  | BrightStar Solutions    |
| 12 | LEGAL  | GlobalTech Innovations  |
| 13 | LEGAL  | SilverPeak Enterprises  |
| 14 | LEGAL  | Horizon Consulting Group|
| 15 | LEGAL  | Unity Systems Inc.      |
| 16 | LEGAL  | Pioneer Dynamics LLC    |
| 17 | LEGAL  | Evergreen Ventures      |
| 18 | LEGAL  | Nexus Analytics Corp.   |
| 19 | LEGAL  | Summit Energy Ltd.      |
| 20 | LEGAL  | BlueWave Technologies   |

*Subjects cover natural persons (`PERSON`) and legal entities (`LEGAL`).*

---

## Table: `bank`

| ID | Name                   | BIC          |
|----|------------------------|--------------|
| 1  | Prime Horizon Bank     | PHBNUS44XXX  |
| 2  | Unity Financial Group  | UFGBGB22XXX  |
| 3  | Sterling Credit Bank   | SCBKDE33XXX  |
| 4  | Pinnacle Trust Bank    | PTBKFRPPXXX  |
| 5  | Evergreen Savings      | EVSVCA2SXXX  |
| 6  | Summit National Bank   | SNBKUS55XXX  |
| 7  | Blue Ridge Banking     | BRBKESMMXXX  |
| 8  | Crestwood Financial    | CWFIJPJTXXX  |
| 9  | Nova Capital Bank      | NCBKAU4AXXX  |
| 10 | Liberty Commerce Bank  | LCBKIT2RXXX  |

*Banks with typical BIC codes serving account holders.*

---

## Table: `account`

| ID | Owner ID | Base Currency ID | Type   | Bank ID | IBAN                      | Balance   | Name                      |
|----|----------|------------------|--------|---------|---------------------------|-----------|---------------------------|
| 1  | 1        | 3 (JPY)          | CREDIT | 7       | ES12BRBK0000000000000001  | 500,000.00| Emma Thompson CREDIT JPY  |
| 2  | 1        | 12 (SGD)         | DEBIT  | 7       | ES12BRBK0000000000000002  | 500,000.00| Emma Thompson DEBIT SGD   |
| 3  | 2        | 18 (RUB)         | CREDIT | 3       | DE33SCBK0000000000000003  | 500,000.00| Liam Carter CREDIT RUB    |
| 4  | 2        | 6 (CAD)          | DEBIT  | 3       | DE33SCBK0000000000000004  | 500,000.00| Liam Carter DEBIT CAD     |
| 5  | 3        | 9 (SEK)          | CREDIT | 1       | US12PHBN0000000000000005  | 500,000.00| Sofia Rodriguez CREDIT SEK|
| 6  | 3        | 14 (NOK)         | DEBIT  | 1       | US12PHBN0000000000000006  | 500,000.00| Sofia Rodriguez DEBIT NOK |
| 7  | 4        | 2 (EUR)          | CREDIT | 9       | AU12NCBK0000000000000007  | 500,000.00| Noah Patel CREDIT EUR     |
| 8  | 4        | 17 (INR)         | DEBIT  | 9       | AU12NCBK0000000000000008  | 500,000.00| Noah Patel DEBIT INR      |
| 9  | 5        | 5 (AUD)          | CREDIT | 4       | FR12PTBK0000000000000009  | 500,000.00| Ava Kim CREDIT AUD        |
| 10 | 5        | 11 (MXN)         | DEBIT  | 4       | FR12PTBK0000000000000010  | 500,000.00| Ava Kim DEBIT MXN         |
| 11 | 6        | 20 (ZAR)         | CREDIT | 2       | GB29UFGB0000000000000011  | 500,000.00| Ethan Nguyen CREDIT ZAR   |
| 12 | 6        | 7 (CHF)          | DEBIT  | 2       | GB29UFGB0000000000000012  | 500,000.00| Ethan Nguyen DEBIT CHF    |
| 13 | 7        | 13 (HKD)         | CREDIT | 10      | IT12LCBK0000000000000013  | 500,000.00| Isabella Martinez CREDIT HKD|
| 14 | 7        | 4 (GBP)          | DEBIT  | 10      | IT12LCBK0000000000000014  | 500,000.00| Isabella Martinez DEBIT GBP|
| 15 | 8        | 16 (TRY)         | CREDIT | 6       | US55SNBK0000000000000015  | 500,000.00| Jacob Lee CREDIT TRY      |
| 16 | 8        | 8 (CNY)          | DEBIT  | 6       | US55SNBK0000000000000016  | 500,000.00| Jacob Lee DEBIT CNY       |
| 17 | 9        | 10 (NZD)         | CREDIT | 8       | JP12CWFI0000000000000017  | 500,000.00| Mia Johnson CREDIT NZD    |
| 18 | 9        | 19 (BRL)         | DEBIT  | 8       | JP12CWFI0000000000000018  | 500,000.00| Mia Johnson DEBIT BRL     |
| 19 | 10       | 1 (USD)          | CREDIT | 5       | CA12EVSV0000000000000019  | 500,000.00| Oliver Brown CREDIT USD   |
| 20 | 10       | 15 (KRW)         | DEBIT  | 5       | CA12EVSV0000000000000020  | 500,000.00| Oliver Brown DEBIT KRW    |
| ... | ... | ... | ... | ... | ... | ... | ... |

*(Accounts belong to `subject` owners, have base currency, account type, bank association, IBAN, balance, and descriptive name.)*

---

## Table: `transaction`

| ID | Account ID | Type    | Currency ID | Amount     |
|----|------------|---------|-------------|------------|
| 1  | 1          | DEPOSIT | 3 (JPY)     | 500,000.00 |
| 2  | 2          | DEPOSIT | 12 (SGD)    | 500,000.00 |
| 3  | 3          | DEPOSIT | 18 (RUB)    | 500,000.00 |
| 4  | 4          | DEPOSIT | 6 (CAD)     | 500,000.00 |
| 5  | 5          | DEPOSIT | 9 (SEK)     | 500,000.00 |
| 6  | 6          | DEPOSIT | 14 (NOK)    | 500,000.00 |
| 7  | 7          | DEPOSIT | 2 (EUR)     | 500,000.00 |
| 8  | 8          | DEPOSIT | 17 (INR)    | 500,000.00 |
| 9  | 9          | DEPOSIT | 5 (AUD)     | 500,000.00 |
| 10 | 10         | DEPOSIT | 11 (MXN)    | 500,000.00 |
| ... | ...        | ...     | ...         | ...        |

*Initial deposits corresponding to each account with amount 500,000.00 in respective currencies.*

---

## Notes

- All ID columns are auto-incremented starting at 1.
- Foreign keys:
  - `account.owner_id` references `subject.id`
  - `account.base_currency_id` references `currency.id`
  - `account.bank_id` references `bank.id`
  - `transaction.account_id` references `account.id`
  - `transaction.currency_id` references `currency.id`
- The amount values are uniform at 500,000.00 as dummy funds.
- Account `type` fields use values like 'CREDIT' or 'DEBIT'.
- Subject `type` is either 'PERSON' or 'LEGAL' indicating individual or company.
- Banks have realistic BICs associated.
- IBANs are mock but follow a valid format for currency and bank.

---

## Summary

This dataset provides a solid basis for testing:

- Multi-currency accounts and transactions
- Relationships between individuals/companies, their accounts, banks, and transactions
- Realistic but deterministic dummy data for development and integration testing

---

| [Back](../README.md)|
|--------|
