# PACS.008 and CAMT.025 Explained

With the migration from traditional SWIFT MT messages to **ISO 20022**, new XML-based message formats are being used across payment systems worldwide.

For Treasury, Payments, and Liquidity teams at institutions like the EIB, understanding **PACS** and **CAMT** messages is now essential. 

---

# PACS.008 – FI to Customer Credit Transfer

## Purpose

**pacs.008 (FIToFICustomerCreditTransfer)** is the ISO 20022 equivalent of the traditional **MT103**.

It is used to carry payment instructions for customer payments between financial institutions. 

---

## Typical Flow

```text
Customer A
    ↓
Bank A
    ↓
pacs.008
    ↓
Bank B
    ↓
Customer B
```

Example:

```text
EIB pays a supplier €5M
```

or

```text
Borrower repays an EIB loan
```

The payment instruction is exchanged using a PACS.008 message.

---

## Key Information Contained

A PACS.008 includes:

- Debtor (payer)
- Creditor (beneficiary)
- Amount
- Currency
- Value date
- Remittance information
- Charges information
- UETR (Unique End-to-End Transaction Reference)



---

## Mapping

```text
MT103
  ↓
pacs.008
```

---

# PACS.009 – Financial Institution Transfer

Since Treasury teams often see PACS.008 together with PACS.009:

```text
MT202
  ↓
pacs.009
```

Used for:

- Liquidity transfers
- Correspondent banking settlement
- Treasury funding
- Cover payments



---

# CAMT Messages

CAMT stands for:

```text
Cash Management
```

These messages are primarily used for:

- Account reporting
- Reconciliation
- Liquidity monitoring
- Balance management

Treasury teams consume CAMT messages daily. 

---

# CAMT.025 – Receipt / Status Request

## Purpose

**camt.025 (Receipt)** is used to acknowledge, reject, or respond to a request related to account management or payment processing.

In many implementations it serves as a technical or business response indicating whether a message has been received and accepted for processing. 

---

## Simplified Example

```text
System A
   ↓ sends request
System B
   ↓
CAMT.025
   ↓
Acknowledgement/Response
```

The message may indicate:

```text
Accepted
Rejected
Received
Processed
```

depending on the business flow.

---

# More Important CAMT Messages for Treasury

## CAMT.052

Intraday Account Report

Equivalent to:

```text
MT942
```

Provides:

- Intraday balances
- Incoming payments
- Outgoing payments
- Liquidity position

Used during the day by Treasury desks. 

---

## CAMT.053

Bank Statement

Equivalent to:

```text
MT940
```

Provides:

- Opening balance
- Credits
- Debits
- Closing balance

Used for daily reconciliation. 

---

## CAMT.054

Debit/Credit Notification

Real-time notification when money enters or leaves an account.

Example:

```text
Loan repayment received
Bond coupon paid
Supplier payment executed
```

Treasury systems often process CAMT.054 messages automatically. 

---

# EIB Treasury Example

A typical end-to-end flow could be:

```text
1. Borrower sends repayment
    ↓
    pacs.008

2. Cash arrives at EIB account
    ↓
    camt.054

3. Treasury receives intraday balance
    ↓
    camt.052

4. End-of-day reconciliation
    ↓
    camt.053

5. Interbank liquidity transfer
    ↓
    pacs.009
```

---

# Quick Reference

```text
MT103  → pacs.008  Customer payment

MT202  → pacs.009  Bank-to-bank payment

MT940  → camt.053  End-of-day statement

MT942  → camt.052  Intraday statement

Notification → camt.054 Debit/Credit advice

Acknowledgement / status → camt.025
```

---

# For an EIB Liquidity or Treasury Role

The ISO 20022 messages you are most likely to encounter are:

```text
pacs.008  Customer credit transfers
pacs.009  Interbank settlements
camt.052  Intraday balances
camt.053  End-of-day statements
camt.054  Debit/Credit notifications
camt.025  Acknowledgements and status responses
```

These messages together provide the complete picture of **cash movements, account balances, liquidity monitoring, reconciliation, and payment processing** within a modern investment bank such as the EIB.