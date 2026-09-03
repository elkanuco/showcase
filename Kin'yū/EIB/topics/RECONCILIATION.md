# Reconciliation Types in Banking, Treasury, Liquidity Management, and Investment Banking

## Overview

Reconciliation is the process of comparing two or more independent records to ensure that transactions, balances, positions, and accounting entries are complete, accurate, and consistent.

In institutions such as the **European Investment Bank (EIB)**, reconciliations are performed daily across:

- Treasury
- Payments
- Accounting
- Capital Markets
- Loans
- Investments
- Securities
- Cash Management
- Regulatory Reporting

---

# 1. Cash Reconciliation

## Purpose

Verify that internal cash records match actual bank balances.

## Comparison

```text
Treasury System
      vs
Bank Statement
```

## Sources

- CAMT.052
- CAMT.053
- CAMT.054
- MT940
- MT942

## Typical Breaks

- Missing transactions
- Duplicate postings
- Incorrect value dates

---

# 2. Bank Account Reconciliation

## Purpose

Ensure general ledger balances match external bank accounts.

## Comparison

```text
General Ledger
      vs
Bank Account Statement
```

## Typical Frequency

- Daily
- Monthly

---

# 3. Nostro Reconciliation

## Purpose

Reconcile accounts held with correspondent banks.

## Comparison

```text
Internal Nostro Ledger
           vs
Correspondent Bank Statement
```

## Used By

- Treasury
- Payments
- Liquidity Management

## Typical Breaks

- Unmatched SWIFT payments
- Settlement delays
- Incorrect value dates

---

# 4. Vostro Reconciliation

## Purpose

Reconcile accounts maintained for other banks.

## Comparison

```text
Vostro Customer Ledger
           vs
Actual Account Activity
```

---

# 5. General Ledger (GL) Reconciliation

## Purpose

Ensure accounting entries are correctly reflected in financial records.

## Comparison

```text
Subledger
      vs
General Ledger
```

## Examples

- Loans
- Securities
- Treasury
- Fixed Assets

---

# 6. Subledger Reconciliation

## Purpose

Validate that operational systems agree with accounting records.

## Comparison

```text
Operational System
        vs
Accounting Ledger
```

Examples:

```text
Loan System
Treasury System
Investment System
```

---

# 7. Cash Position Reconciliation

## Purpose

Verify liquidity positions.

## Comparison

```text
Expected Cash Position
           vs
Actual Cash Position
```

## Used By

- Treasury
- Liquidity Management

---

# 8. Intraday Liquidity Reconciliation

## Purpose

Track liquidity throughout the day.

## Sources

```text
CAMT.052
CAMT.054
Payment Systems
Treasury Platforms
```

## Goal

Ensure sufficient liquidity exists for payment obligations.

---

# 9. End-of-Day Liquidity Reconciliation

## Purpose

Validate treasury closing positions.

## Sources

```text
Treasury Position
vs
Closing Bank Balances
```

---

# 10. Loan Reconciliation

## Purpose

Ensure loan balances are accurate.

## Comparison

```text
Loan Management System
           vs
General Ledger
```

## Verify

- Principal
- Interest
- Fees

---

# 11. Loan Repayment Reconciliation

## Purpose

Confirm borrower payments were received and allocated correctly.

## Comparison

```text
Expected Repayment Schedule
               vs
Actual Receipts
```

---

# 12. Interest Reconciliation

## Purpose

Validate interest calculations.

## Comparison

```text
Calculated Interest
          vs
Received/Paid Interest
```

---

# 13. Securities Reconciliation

## Purpose

Confirm security holdings.

## Comparison

```text
Custodian Records
        vs
Internal Records
```

## Instruments

- Bonds
- Equities
- Funds

---

# 14. Position Reconciliation

## Purpose

Ensure portfolio positions are correct.

## Comparison

```text
Front Office
      vs
Back Office
```

## Checks

- Quantities
- Nominal amounts
- Ownership

---

# 15. Custody Reconciliation

## Purpose

Verify securities held by custodians.

## Comparison

```text
Internal Position
       vs
Custodian Position
```

---

# 16. Trade Reconciliation

## Purpose

Ensure trades are captured correctly.

## Comparison

```text
Trade Execution
         vs
Settlement Records
```

---

# 17. Settlement Reconciliation

## Purpose

Confirm settled transactions.

## Comparison

```text
Expected Settlements
           vs
Actual Settlements
```

---

# 18. Payment Reconciliation

## Purpose

Ensure payment instructions have settled.

## Comparison

```text
Payment Instruction
            vs
Bank Confirmation
```

## Message Examples

```text
pacs.008
pacs.009
MT103
MT202
```

---

# 19. SWIFT Reconciliation

## Purpose

Validate SWIFT traffic.

## Comparison

```text
SWIFT Message
      vs
Internal Transaction
```

## Messages

```text
MT103
MT202
pacs.008
pacs.009
```

---

# 20. Treasury Reconciliation

## Purpose

Validate treasury transactions and balances.

## Areas

- Funding
- Investments
- FX
- Money Markets

---

# 21. Foreign Exchange (FX) Reconciliation

## Purpose

Ensure FX trades settle correctly.

## Comparison

```text
Trade Confirmation
         vs
Settlement Record
```

---

# 22. Money Market Reconciliation

## Purpose

Verify deposits and borrowings.

## Instruments

- Deposits
- Commercial Paper
- Certificates of Deposit

---

# 23. Derivatives Reconciliation

## Purpose

Validate derivative positions.

## Instruments

- Interest Rate Swaps
- Currency Swaps
- Futures
- Options

---

# 24. Collateral Reconciliation

## Purpose

Ensure collateral balances agree.

## Comparison

```text
Counterparty Records
          vs
Internal Books
```

## Used For

- Repo
- Derivatives
- Secured Funding

---

# 25. Margin Reconciliation

## Purpose

Verify variation and initial margin.

## Typical Area

```text
Cleared Derivatives
```

---

# 26. Repo Reconciliation

## Purpose

Confirm securities financing transactions.

## Comparison

```text
Repo Trade
      vs
Collateral Movement
```

---

# 27. Investment Portfolio Reconciliation

## Purpose

Validate portfolio holdings.

## Checks

- Quantity
- Market Value
- Accrued Interest

---

# 28. NAV Reconciliation

## Purpose

Validate fund Net Asset Value.

## Used By

- Asset Managers
- Investment Funds

---

# 29. Accounting Reconciliation

## Purpose

Verify accounting records.

## Comparison

```text
Accounting Entries
         vs
Source Transactions
```

---

# 30. Intercompany Reconciliation

## Purpose

Match transactions between entities.

## Example

```text
Entity A Receivable
      vs
Entity B Payable
```

---

# 31. Regulatory Reporting Reconciliation

## Purpose

Ensure regulatory reports agree with source systems.

## Reports

- COREP
- FINREP
- Liquidity Reports

---

# 32. Capital Adequacy Reconciliation

## Purpose

Validate regulatory capital calculations.

---

# 33. LCR Reconciliation

## Purpose

Validate Liquidity Coverage Ratio data.

## Components

```text
HQLA
Cash Outflows
Cash Inflows
```

---

# 34. NSFR Reconciliation

## Purpose

Validate Net Stable Funding Ratio calculations.

---

# 35. Financial Statement Reconciliation

## Purpose

Ensure financial statements are complete.

## Components

- Balance Sheet
- Income Statement
- Cash Flow Statement

---

# 36. Accrual Reconciliation

## Purpose

Validate accrued income and expenses.

---

# 37. Tax Reconciliation

## Purpose

Ensure tax calculations are correct.

---

# 38. Fixed Asset Reconciliation

## Purpose

Validate fixed asset records.

---

# 39. Suspense Account Reconciliation

## Purpose

Resolve unidentified transactions.

## Goal

```text
Suspense Balance = Zero
```

---

# 40. Exception Reconciliation

## Purpose

Investigate unmatched transactions.

## Examples

```text
Amount mismatch
Missing transaction
Duplicate transaction
Value date mismatch
```

---

# 41. Data Reconciliation

## Purpose

Validate consistency between systems.

## Example

```text
Treasury Platform
      vs
Data Warehouse
```

---

# 42. Front-to-Back Reconciliation

## Purpose

Ensure trade lifecycle consistency.

## Comparison

```text
Front Office
Middle Office
Back Office
```

---

# 43. Front-to-Accounting Reconciliation

## Purpose

Validate accounting impact of trades.

---

# 44. Position-to-Cash Reconciliation

## Purpose

Link security positions to cash movements.

---

# 45. Corporate Actions Reconciliation

## Purpose

Verify corporate action processing.

## Examples

- Coupon Payments
- Dividends
- Redemptions
- Stock Splits

---

# 46. Coupon Reconciliation

## Purpose

Validate bond coupon payments.

---

# 47. Bond Redemption Reconciliation

## Purpose

Verify principal repayment at maturity.

---

# 48. Funding Reconciliation

## Purpose

Reconcile borrowing activities.

## Examples

- Bond Issues
- Commercial Paper
- Medium-Term Notes

---

# 49. Capital Markets Reconciliation

## Purpose

Validate issuance and settlement of securities.

---

# 50. End-to-End Treasury Reconciliation

## Purpose

Ensure complete integrity across:

```text
Trade
 ↓
Settlement
 ↓
Cash
 ↓
Accounting
 ↓
Reporting
```

This is the highest-level reconciliation often performed at institutions such as the **EIB**, where treasury, payments, accounting, securities, loans, and regulatory reporting must all reconcile consistently.

---
# 51. Intraday Reconciliation

## Purpose

Ensure that cash movements, balances, and payment flows occurring during the business day are correctly reflected across all systems.

## Objective

Provide a real-time view of liquidity and identify breaks before end-of-day processing.

## Comparison

```text
Treasury System
      vs
Bank Intraday Reports

Expected Payments
      vs
Actual Payments

Forecasted Liquidity
      vs
Current Liquidity
```

## Data Sources

- CAMT.052
- CAMT.054
- PACS.008
- PACS.009
- RTGS systems
- T2/TARGET Services
- SWIFT messages

## Typical Checks

### Incoming Payments

```text
Expected inflows
      vs
Received inflows
```

### Outgoing Payments

```text
Authorized payments
       vs
Executed payments
```

### Cash Balances

```text
Treasury cash position
         vs
Bank reported balance
```

### Liquidity Buffers

```text
Available liquidity
        vs
Required liquidity
```

## Typical Breaks

- Delayed payments
- Missing payment confirmations
- Duplicate transactions
- Value date differences
- Unexpected cash movements

## Business Importance

For institutions such as EIB:

```text
Intraday Reconciliation
        ↓
Intraday Liquidity Monitoring
        ↓
Ability to fund payments
        ↓
Avoid settlement failures
```

## Frequency

Typically:

```text
Real-time

Every 15 minutes

Hourly

Multiple cycles per day
```

---

# 52. T-1 Reconciliation (Previous-Day Reconciliation)

## Purpose

Validate today's opening position against yesterday's closing position.

## Definition

```text
T      = Today

T-1    = Previous Business Day
```

## Objective

Ensure continuity of balances between business days.

## Comparison

```text
Yesterday Closing Balance
             vs
Today Opening Balance
```

## Formula

```text
Opening Balance (T)

=

Closing Balance (T-1)
+
Late Adjustments
+
Back-Valued Transactions
```

## Typical Areas

### Cash

```text
Yesterday closing cash
           vs
Today's opening cash
```

### Securities

```text
Yesterday positions
          vs
Today's positions
```

### Liquidity

```text
T-1 liquidity position
            vs
T liquidity starting position
```

### Accounts

```text
Bank Statement T-1
          vs
GL Opening Balance
```

## Typical Breaks

- Overnight settlements
- Late postings
- Failed trades
- Accounting adjustments
- Corporate actions
- Incorrect carry-forwards

## Example

### End of Yesterday

```text
Closing Cash Position

€1,250,000,000
```

### Start of Today

```text
Opening Cash Position

€1,245,000,000
```

### Difference

```text
- €5,000,000
```

Investigation required:

```text
Missing posting?
Late settlement?
Value date adjustment?
```

## Business Importance

Without T-1 reconciliation, Treasury cannot trust:

- Opening balances
- Cash forecasts
- Liquidity reports
- Regulatory reports

---

# 53. T+1 Reconciliation

## Purpose

Validate transactions on the day after processing.

## Definition

```text
T+1 = Next Business Day
```

Common in:

- Securities operations
- Custody
- Capital Markets
- Investment Funds

## Example

```text
Trade executed on Monday

Settlement expected Tuesday

Reconciliation performed Tuesday
```

---

# 54. Real-Time Liquidity Reconciliation

## Purpose

Monitor available cash against payment obligations continuously.

## Comparison

```text
Current Balance
      vs
Scheduled Outflows
```

Used heavily in:

- Central Banks
- EIB
- ECB
- Large Investment Banks

---

# 55. Forecast-to-Actual Reconciliation

## Purpose

Measure liquidity forecast accuracy.

## Comparison

```text
Expected Cash Flow
        vs
Actual Cash Flow
```

## Example

Forecast:

```text
€500M inflow
```

Actual:

```text
€450M inflow
```

Variance:

```text
-€50M
```

Treasury investigates the deviation.

---

# 56. Opening-to-Closing Position Reconciliation

## Purpose

Explain how today's closing position was reached.

## Formula

```text
Opening Balance
+ Inflows
- Outflows
+ Adjustments
=
Closing Balance
```

Often considered the foundation of daily treasury reconciliation.

---

# Treasury Reconciliations Most Critical at EIB

```text
Intraday Reconciliation
T-1 Reconciliation
Cash Reconciliation
Bank Reconciliation
Nostro Reconciliation
Payment Reconciliation
Settlement Reconciliation
Liquidity Reconciliation
Forecast-to-Actual Reconciliation
Opening-to-Closing Reconciliation
GL Reconciliation
Front-to-Accounting Reconciliation
```

## Simplified Timeline

```text
Yesterday
(T-1)
   ↓
T-1 Reconciliation
   ↓
Opening Balance Confirmed

During Day
(T)
   ↓
Intraday Reconciliation
   ↓
Liquidity Monitoring
   ↓
Payments Monitoring

End of Day
(T)
   ↓
Cash Reconciliation
   ↓
Bank Reconciliation
   ↓
GL Reconciliation

Next Day
(T+1)
   ↓
T+1 Validation
```

For an EIB Liquidity/Treasury Operations role, **Intraday Reconciliation** and **T-1 Reconciliation** are among the most important operational controls because they ensure that liquidity positions are accurate before, during, and after the payment day.

# Reconciliations Most Relevant for an EIB Treasury/Liquidity Role

If you're working in Treasury Operations, Liquidity Management, Payments, or a TMS (e.g., GTreasury, Quantum, Kyriba, SAP Treasury), focus on:

```text
1. Cash Reconciliation
2. Bank Reconciliation
3. Nostro Reconciliation
4. Payment Reconciliation
5. SWIFT Reconciliation
6. Liquidity Reconciliation
7. Intraday Liquidity Reconciliation
8. Treasury Reconciliation
9. Funding Reconciliation
10. FX Reconciliation
11. Settlement Reconciliation
12. General Ledger Reconciliation
13. Front-to-Back Reconciliation
14. Front-to-Accounting Reconciliation
15. Regulatory/LCR Reconciliation
```

In an EIB / Treasury Context a common hierarchy is:

1. T-a Balance Reconciliation
   ↓
   Verify carried-forward balance

2. Intraday Reconciliation
   ↓
   Monitor current-day movements

3. End-of-Day Reconciliation
   ↓
   Confirm closing balance

4. T+1 Validation
   ↓
   Confirm next-day continuity

These account for the vast majority of reconciliation activities in large investment and development banks.