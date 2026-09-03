# Payment Message Types

**SWIFT message types (MT messages)** are used to represent different kinds of cash movements, transfers, statements, 
and confirmations between financial institutions. SWIFT itself is a messaging network; it does **not move money**, but sends standardized instructions between banks. 

---

# Main SWIFT Categories

| Category | Description |
|----------|-------------|
| MT1xx | Customer Payments and Cheques |
| MT2xx | Financial Institution Transfers |
| MT3xx | Treasury and Foreign Exchange |
| MT4xx | Collections and Cash Letters |
| MT5xx | Securities |
| MT6xx | Metals and Syndications |
| MT7xx | Trade Finance and Guarantees |
| MT9xx | Cash Management and Reporting |



---

# Most Important SWIFT Messages for Treasury and Liquidity

## MT103 - Customer Credit Transfer

Used when a customer sends money to another customer.

Example:

```text
Company A → Company B
```

Contains:

- Ordering customer
- Beneficiary customer
- Amount
- Currency
- Value date
- Payment details

Typical use:

```text
Supplier payment
International wire transfer
Payroll payment
```



---

## MT202 - Financial Institution Transfer

Used for transfers between banks.

Example:

```text
Bank A → Bank B
```

Contains:

- Sending bank
- Receiving bank
- Settlement amount
- Settlement date

Typical use:

```text
Interbank liquidity movement
Treasury funding
Settlement transfer
```



---

## MT202 COV - Cover Payment

Used alongside an MT103.

The payment information travels via:

```text
MT103
```

while the actual settlement between correspondent banks occurs via:

```text
MT202 COV
```

Common in international correspondent banking.



---

# Treasury and FX Movements

## MT300 - Foreign Exchange Confirmation

Used to confirm FX trades.

Example:

```text
EUR/USD trade
GBP/EUR trade
```

Treasury desks and investment banks use MT300 extensively.



---

## MT320 - Fixed-Term Deposit

Used for:

```text
Money market placements
Interbank deposits
```

Example:

```text
EIB places €100M overnight with another bank
```



---

# Cash Management Messages

These are extremely important for Treasury departments.

---

## MT940 - End-of-Day Statement

Provides a bank account statement.

Typical contents:

```text
Opening balance
Credits
Debits
Closing balance
```

Used by Treasury systems for reconciliation.



---

## MT941 - Balance Report

Provides account balance information only.

Example:

```text
Current balance
Available balance
```

Useful for liquidity monitoring.



---

## MT942 - Interim Transaction Report

Intraday account activity.

Used during the day to track:

```text
Incoming payments
Outgoing payments
Expected liquidity
```

Important for real-time treasury operations.



---

## MT950 - Statement Message

Interbank account statement message.

Mostly used between financial institutions.



---

# Trade Finance Movements

## MT700 - Letter of Credit

Used in international trade.

Example:

```text
Importer requests a Letter of Credit
```



---

## MT760 - Guarantee

Used to issue:

- Bank Guarantees
- Standby Letters of Credit



---

# Typical Treasury Cash Flow at EIB

A simplified EIB liquidity flow might look like:

```text
1. Bond Issuance
   ↓
   MT202 Incoming

2. Treasury Investments
   ↓
   MT320

3. FX Hedge
   ↓
   MT300

4. Loan Disbursement
   ↓
   MT103 or MT202

5. Borrower Repayment
   ↓
   MT103 Incoming

6. Account Reporting
   ↓
   MT940 / MT942
```

---

# Legacy MT vs New ISO 20022 (MX)

Since the SWIFT migration to ISO 20022, many MT messages are being replaced by XML-based MX messages:

| MT Message | ISO 20022 Equivalent |
|------------|----------------------|
| MT103 | pacs.008 |
| MT202 | pacs.009 |
| MT940 | camt.053 |
| MT942 | camt.052 / camt.054 |

However, many banks and treasury teams still use the MT terminology in day-to-day operations. 

---

# Key Messages to Know for an EIB Treasury Role

If you work in Treasury, Liquidity Management, Cash Management, or Payments, the most important messages are:

```text
MT103  Customer payments
MT202  Interbank transfers
MT202 COV Cover payments
MT300  FX trades
MT320  Money market deposits
MT940  End-of-day statements
MT942  Intraday reports
MT950  Interbank statements
```

These messages drive the majority of daily cash, liquidity, funding, and settlement activities within investment and development banks.

# Comprehensive List of SWIFT Movements and Message Types

## 1. Customer Payments

Used for transferring funds on behalf of customers.

### MT103
Single Customer Credit Transfer

Use Cases:
- International wire transfers
- Corporate payments
- Supplier payments
- Retail customer transfers

### MT103 STP
Straight Through Processing version of MT103.

Use Cases:
- Automated processing
- Faster settlement
- Reduced manual intervention

### MT101
Request for Transfer

Use Cases:
- Corporate treasury payments
- Cash management

### MT102
Multiple Customer Credit Transfers

Use Cases:
- Payroll payments
- Bulk payment processing

---

## 2. Financial Institution Transfers

Used between banks for liquidity and settlement purposes.

### MT202
General Financial Institution Transfer

Use Cases:
- Interbank fund movements
- Treasury settlements

### MT202 COV
Cover Payment

Use Cases:
- Associated with MT103 payments
- Settlement between correspondent banks

---

## 3. Cash Management Movements

### MT940
Customer Statement Message

Use Cases:
- Daily account statements
- Reconciliation

### MT941
Balance Report

Use Cases:
- Intraday cash monitoring

### MT942
Interim Transaction Report

Use Cases:
- Real-time reporting
- Treasury monitoring

### MT950
Statement Message

Use Cases:
- Correspondent bank account statements

---

## 4. Treasury and Foreign Exchange

### MT300
Foreign Exchange Confirmation

Use Cases:
- FX spot trades
- FX forwards
- FX swaps

### MT320
Fixed Loan/Deposit Confirmation

Use Cases:
- Money market operations

### MT330
Interest Rate Transaction Confirmation

Use Cases:
- Interest rate swaps

### MT340
Forward Rate Agreement

Use Cases:
- FRA settlements

### MT350
Commodity Trade Confirmation

Use Cases:
- Commodity derivatives

---

## 5. Securities Movements

### MT500 Series

#### MT502
Order to Buy/Sell

#### MT509
Trade Status

#### MT515
Client Confirmation

#### MT517
Buy/Sell Confirmation

#### MT535
Statement of Holdings

#### MT536
Statement of Transactions

#### MT537
Statement of Pending Transactions

#### MT540
Receive Free

#### MT541
Receive Against Payment

#### MT542
Deliver Free

#### MT543
Deliver Against Payment

#### MT564
Corporate Action Notification

#### MT566
Corporate Action Confirmation

Use Cases:
- Bond settlements
- Equity settlements
- Custody operations
- Investment fund servicing

---

## 6. Documentary Credits (Letters of Credit)

### MT700
Issue of Documentary Credit

### MT701
Continuation of MT700

### MT707
Amendment to Documentary Credit

### MT710
Advice of Third Bank LC

### MT720
Transfer of Documentary Credit

### MT730
Acknowledgement

### MT732
Advice of Discharge

### MT734
Advice of Refusal

### MT740
Authorization to Reimburse

### MT742
Reimbursement Claim

### MT750
Advice of Discrepancy

### MT752
Authorization to Pay

### MT754
Advice of Payment

Use Cases:
- International trade
- Import/export financing

---

## 7. Bank Guarantees and Standby Letters of Credit

### MT760
Guarantee or Standby Letter of Credit

### MT767
Amendment to Guarantee

### MT768
Acknowledgement of Guarantee

### MT769
Advice of Reduction or Release

Use Cases:
- Performance guarantees
- Financial guarantees
- Bid bonds
- Project finance guarantees

---

## 8. Collections

### MT400
Advice of Payment

### MT410
Acknowledgement

### MT412
Advice of Acceptance

### MT420
Tracer

### MT422
Advice of Fate and Request

Use Cases:
- Documentary collections
- Trade settlement

---

## 9. Correspondent Banking Movements

### Nostro Account Funding

Movement Types:
- Prefunding
- Liquidity transfers
- Settlement balances

### Vostro Account Transactions

Movement Types:
- Incoming customer payments
- Outgoing customer payments
- Treasury settlements

### Cover Payments

Movement Types:
- MT202 COV settlements
- Multi-bank routing

---

## 10. SWIFT gpi Movements

### Tracked Payments

Features:
- End-to-end tracking
- Fee transparency
- Real-time status updates

### UETR Tracking

Features:
- Unique identifier
- Investigation support
- Compliance tracing

---

## 11. Corporate Treasury Movements

### Liquidity Management

Examples:
- Cash concentration
- Pooling
- Sweeping

### Funding Transfers

Examples:
- Intra-group transfers
- Working capital funding

### Investment Transactions

Examples:
- Money market investments
- Time deposits

---

## 12. EIB Relevant SWIFT Movements

For the European Investment Bank (EIB), typical SWIFT traffic includes:

### Loan Operations

- Loan disbursements
- Loan repayments
- Interest payments

### Treasury Operations

- FX transactions
- Liquidity management
- Investment settlements

### Capital Markets

- Bond issuance settlements
- Coupon payments
- Redemption payments

### Grant Payments

- EU program disbursements
- Development financing payments

### Trade Finance

- Guarantees
- Letters of credit

### Correspondent Banking

- Nostro account management
- Cash movements
- Settlement transfers

---

# Modern ISO 20022 (MX) Equivalents

SWIFT is progressively migrating from MT messages to ISO 20022 MX messages.

Common examples:

| Legacy MT | ISO 20022 MX |
|------------|-------------|
| MT103 | pacs.008 |
| MT202 | pacs.009 |
| MT900 | camt.054 |
| MT940 | camt.053 |
| MT942 | camt.052 |

---

# High-Level Classification of SWIFT Movements

1. Customer Payments
2. Financial Institution Transfers
3. Treasury Operations
4. Foreign Exchange Operations
5. Securities Settlement
6. Fund Administration
7. Trade Finance
8. Bank Guarantees
9. Documentary Collections
10. Cash Management
11. Correspondent Banking
12. Capital Markets
13. Loan Operations
14. Liquidity Management
15. ISO 20022 Payment Messages
16. SWIFT gpi Tracked Payments