# ISO 20022 PACS Messages Explained

## What Does PACS Mean?

**PACS** stands for:

```text
Payments Clearing and Settlement
```

PACS messages belong to the **ISO 20022** standard and are used to exchange payment information between financial institutions and payment infrastructures. They are gradually replacing legacy SWIFT MT messages. 

---

# PACS Message Family

The PACS family covers the entire lifecycle of a payment:

```text
Initiation
   ↓
Settlement
   ↓
Clearing
   ↓
Cancellation
   ↓
Status updates
   ↓
Returns
```

Most banks, central banks, TARGET Services, SEPA, SWIFT CBPR+, T2, TIPS, and instant payment systems use PACS messages. 

---

# Most Important PACS Messages

## PACS.002

### FIToFIPaymentStatusReport

Used to provide payment status information.

Example:

```text
Payment Accepted
Payment Rejected
Payment Pending
Payment Settled
```

Treasury and operations teams frequently analyze PACS.002 for payment investigations.

---

## PACS.003

### FIToFIDirectDebit

Used for direct debit transactions between financial institutions.

Example:

```text
Utility company
      ↓
SEPA Direct Debit
      ↓
Customer account
```

Mostly used in retail payment schemes.

---

## PACS.004

### PaymentReturn

Used to return a previously settled payment.

Example:

```text
Beneficiary account closed
      ↓
Payment returned
```

Common reasons:

- Invalid account
- Closed account
- Account blocked
- Regulatory issues

---

## PACS.007

### FIToFIPaymentReversal

Requests reversal of an incorrectly processed payment.

Example:

```text
Duplicate payment
Fraudulent payment
Processing error
```

---

## PACS.008

### FIToFICustomerCreditTransfer

The most important PACS message.

Equivalent to:

```text
MT103
```

Used for customer payments.

Example:

```text
Corporation A
     ↓
Bank A
     ↓ PACS.008
Bank B
     ↓
Corporation B
```

Contains:

- Debtor
- Creditor
- Amount
- Currency
- Remittance information
- Settlement details



---

## PACS.009

### FinancialInstitutionCreditTransfer

Equivalent to:

```text
MT202
```
