# CAMT Messages Explained (ISO 20022 Cash Management)

## What Does CAMT Mean?

**CAMT** stands for:

```text
Cash Management
```

CAMT messages are part of the **ISO 20022** standard and are used for:

- Account reporting
- Balance reporting
- Transaction notifications
- Reconciliation
- Liquidity management
- Investigation and exception handling

Unlike **PACS** messages, which move payments through the system, **CAMT** messages provide visibility and reporting on those payments. 

---

# Where CAMT Fits

A typical payment lifecycle looks like:

```text
PAIN
 ↓
Payment initiation

PACS
 ↓
Payment processing & settlement

CAMT
 ↓
Account reporting & cash visibility
```

Example:

```text
Supplier Payment
   ↓
pain.001
   ↓
pacs.008
   ↓
Settlement
   ↓
camt.054
   ↓
camt.052
   ↓
camt.053
```

---

# Why CAMT Is Important for Treasury

CAMT messages provide answers to questions such as:

```text
What is my account balance?

Has a payment arrived?

Was a payment rejected?

How much liquidity is available?

Which transactions occurred today?
```

For institutions like the EIB, CAMT messages are a primary source of liquidity information.

---

# Major CAMT Message Types

## CAMT.025

### Receipt / Acknowledgement

Used to acknowledge or respond to requests.

Purpose:

```text
Message received
Message accepted
Message rejected
```

Often used in workflow and exception management.

---

## CAMT.026

### Status Request

Used to request information about:

- Account reports
- Statements
- Account-related processing

Example:

```text
Please provide current status
```

---

## CAMT.027

### Claim Non-Receipt

Used when an expected message was not received.

Example:

```text
Expected statement missing
```

---

## CAMT.028

### Resolution of Investigation

Response to:

```text
camt.026
camt.027
```

Provides investigation outcome.

---

# Account Reporting Messages

These are the most important CAMT messages for Treasury.

---

## CAMT.052

### Bank to Customer Account Report

Equivalent of:

```text
MT942
```

Provides:

- Intraday balances
- Pending transactions
- Incoming payments
- Outgoing payments
- Available liquidity

Example:

```text
10:00 Balance : €500M
11:00 Incoming : €50M
12:00 Outgoing : €20M
```

Used throughout the day by Treasury teams. 

---

## CAMT.053

### Bank to Customer Statement

Equivalent of:

```text
MT940
```

Provides end-of-day reporting.

Contains:

- Opening balance
- Debit transactions
- Credit transactions
- Closing balance
- Booking details

Example:

```text
Opening Balance  €1.0B
Credits          €200M
Debits           €150M
Closing Balance  €1.05B
```

Used for:

- Reconciliation
- Accounting
- Regulatory reporting
- Treasury controls



---

## CAMT.054

### Debit Credit Notification

Real-time transaction notification.

Equivalent to a transaction advice.

Generated when:

```text
Money arrives
Money leaves
```

Examples:

### Credit

```text
Loan repayment received
```

### Debit

```text
Bond coupon paid
```

Treasury systems often consume CAMT.054 automatically for real-time cash positioning. 

---

# Payment Investigation CAMT Messages

## CAMT.029

### Resolution of Investigation

Provides investigation results.

Example:

```text
Payment found
Payment settled
Payment failed
```

Commonly seen together with PACS investigations.

---

## CAMT.056

### FIToFIPaymentCancellationRequest

Request cancellation of an already transmitted payment.

Example:

```text
Duplicate payment

Wrong beneficiary

Fraud suspect payment
```

Often triggered by operations teams.

---

# Additional Commonly Encountered CAMT Messages

## CAMT.057

Notification of a cancellation request outcome.

---

## CAMT.060

Account Reporting Request.

Used to request:

```text
Balance reports

Statements

Transaction reports
```

---

# Mapping CAMT to Legacy SWIFT MT

```text
MT940
   ↓
CAMT.053
End-of-day Statement

MT942
   ↓
CAMT.052
Intraday Report

Debit/Credit Advice
   ↓
CAMT.054

Investigation Messages
   ↓
CAMT.029

Cancellation Requests
   ↓
CAMT.056
```



---

# CAMT Messages at the EIB

Consider a borrower repaying a loan.

## Step 1

Payment arrives.

```text
pacs.008
```

---

## Step 2

Treasury receives transaction notification.

```text
camt.054
```

Result:

```text
+ €100M credited
```

---

## Step 3

Liquidity position updated.

```text
camt.052
```

Result:

```text
Current intraday balance visible
```

---

## Step 4

End-of-day reconciliation.

```text
camt.053
```

Result:

```text
Official bank statement generated
```

---

# CAMT Categories Treasury Should Know

## Real-Time Monitoring

```text
camt.052
camt.054
```

Purpose:

- Intraday liquidity
- Cash positioning
- Transaction monitoring

---

## End-of-Day Reconciliation

```text
camt.053
```

Purpose:

- Accounting
- Financial reporting
- Reconciliation

---

## Investigations & Exceptions

```text
camt.025
camt.026
camt.027
camt.028
camt.029
camt.056
camt.057
```

Purpose:

- Status inquiries
- Missing reports
- Payment cancellations
- Investigations

---

# CAMT Messages Most Relevant for an EIB Treasury Role

Focus first on:

```text
camt.052  Intraday account report

camt.053  End-of-day statement

camt.054  Debit/Credit notification

camt.025  Acknowledgement

camt.029  Investigation response

camt.056  Cancellation request
```

### Easy Interview Summary

```text
PAIN = Payment initiation

PACS = Payment execution and settlement

CAMT = Cash visibility, statements, balances, notifications, and investigations
```

For Treasury, Liquidity Management, and Cash Operations at the EIB, **CAMT.052, CAMT.053, and CAMT.054 are the three most critical messages because they provide the real-time and end-of-day view of cash positions and liquidity.**