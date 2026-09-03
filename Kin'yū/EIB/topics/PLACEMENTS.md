# Placements in Treasury and Investment Banking

## Overview

A **placement** is a transaction where an institution places excess cash with another financial institution for a specified period to earn interest while maintaining liquidity and capital security.

From a Treasury perspective:

```text
Excess Cash
     ↓
Placed with Counterparty Bank
     ↓
Earn Interest
     ↓
Receive Principal + Interest at Maturity
```

For institutions such as the **EIB**, placements are an important component of **liquidity management**, ensuring that surplus funds generate returns without compromising the ability to meet future obligations.

---

# Why Treasury Makes Placements

Treasury departments rarely leave large amounts of cash idle.

Instead, excess liquidity is invested to:

- Generate interest income
- Preserve capital
- Maintain liquidity
- Manage short-term funding needs
- Optimize balance sheet efficiency
- Meet investment policy requirements

Example:

```text
Available Cash

€500 Million

Immediate Need

€100 Million

Excess Cash

€400 Million
```

Treasury may place the excess €400 million overnight or for a defined term.

---

# How a Placement Works

## Day 1

Treasury places funds:

```text
EIB
  ↓
Places €100M
  ↓
Commercial Bank
```

Term:

```text
1 month
```

Interest Rate:

```text
2.50%
```

---

## Maturity Date

The counterparty returns:

```text
Principal
+
Interest
```

Example:

```text
€100M Principal

+

Interest Earned
```

---

# Types of Placements

## 1. Overnight Placement (O/N)

Duration:

```text
1 Business Day
```

Example:

```text
Today → Tomorrow
```

Purpose:

- Daily liquidity management
- Very short-term cash investment

Most common treasury transaction.

---

## 2. Tomorrow Next (T/N)

Duration:

```text
Tomorrow until following day
```

Example:

```text
Start tomorrow
End next business day
```

Often used in money markets.

---

## 3. Spot Next (S/N)

Duration:

```text
Spot date to next business day
```

Common in interbank markets.

---

## 4. Term Placement

Duration:

```text
1 Week
1 Month
3 Months
6 Months
12 Months
```

Examples:

```text
1W
1M
3M
6M
12M
```

Treasury commits funds until maturity.

---

# Major Placement Instruments

## Interbank Deposits

Treasury places funds with another bank.

Example:

```text
EIB
  ↓
Places €50M
  ↓
Bank ABC
```

At maturity:

```text
Bank ABC
  ↓
Returns Principal + Interest
```

---

## Fixed-Term Deposits

Funds locked for a defined period.

Characteristics:

- Known maturity
- Fixed interest rate
- Low operational complexity

---

## Call Deposits

Funds can be withdrawn on demand.

Benefits:

- Flexible
- High liquidity

Drawback:

- Lower yield

---

## Notice Deposits

Withdrawal requires advance notice.

Example:

```text
7-Day Notice
30-Day Notice
```

Balances yield and liquidity.

---

# Treasury Classification of Placements

## Short-Term Placements

Maturity:

```text
Less than 1 year
```

Examples:

- Overnight
- 1 week
- 1 month
- 3 months

---

## Long-Term Placements

Maturity:

```text
Greater than 1 year
```

Less common for liquidity management.

---

# Placement Lifecycle

## Step 1: Deal Execution

Treasury agrees:

```text
Amount
Rate
Counterparty
Maturity
Currency
```

---

## Step 2: Settlement

Cash leaves account.

Example:

```text
Debit Cash Account

€100M
```

---

## Step 3: Daily Monitoring

Treasury monitors:

- Maturity date
- Interest accrual
- Counterparty exposure

---

## Step 4: Maturity

Funds return.

Example:

```text
Principal
+
Interest
```

---

# Placement Reconciliation

Placements require several reconciliations.

---

## Cash Reconciliation

Verify settlement occurred.

```text
Placement Instruction
        vs
Bank Statement
```

---

## Treasury Reconciliation

Verify trade details.

```text
Treasury Deal
      vs
Confirmation
```

---

## Accounting Reconciliation

Verify accounting entries.

```text
Treasury System
      vs
General Ledger
```

---

## Interest Reconciliation

Verify interest earned.

```text
Expected Interest
         vs
Received Interest
```

---

# Placement Risks

## Credit Risk

Counterparty may fail.

Example:

```text
Bank Default
```

Mitigation:

- Credit limits
- Counterparty ratings
- Diversification

---

## Liquidity Risk

Funds unavailable before maturity.

Example:

```text
6-Month Placement

Unexpected cash need after 2 months
```

---

## Interest Rate Risk

Market rates change after placement.

Example:

```text
Placement Rate = 2%

Market Rate = 4%
```

Opportunity cost arises.

---

## Operational Risk

Examples:

- Incorrect settlement instruction
- Wrong amount
- Wrong maturity date

---

# Placement Accounting Example

Treasury places:

```text
€100M
```

for:

```text
30 days
```

at:

```text
3%
```

Interest:

```text
€100,000,000 × 3% × (30/360)

=
€250,000
```

At maturity:

```text
Principal = €100,000,000

Interest  = €250,000

Total     = €100,250,000
```

---

# Placements vs Investments

Placements are often confused with investments.

## Placement

```text
Short-term

Liquidity management

Capital preservation
```

Examples:

- Deposits
- Money market placements

---

## Investment

```text
Longer-term

Return generation

Portfolio strategy
```

Examples:

- Bonds
- Funds
- Equities

---

# Placements at the EIB

The EIB regularly manages substantial liquidity reserves.

When cash is temporarily not required for:

```text
Loan disbursements
Debt repayments
Operational expenses
```

Treasury may place funds using:

- Interbank deposits
- Money market transactions
- Short-term investments
- Highly rated counterparties

The objectives are:

```text
Capital preservation

Liquidity availability

Yield optimization

Risk management
```

---

# Related SWIFT / ISO Messages

Placements often generate or interact with:

## MT Messages

```text
MT320  Fixed-Term Deposit

MT202  Financial Institution Transfer

MT940  Statement

MT942  Intraday Report
```

---

## ISO 20022 Messages

```text
pacs.009  Interbank Settlement

camt.052  Intraday Balance

camt.053  End-of-Day Statement

camt.054  Debit/Credit Notification
```

---

# Typical Interview Question

## What is a Placement?

**Answer:**

A placement is a short-term investment of excess cash with a financial institution for a defined period and interest rate. Treasury departments use placements to optimize liquidity, preserve capital, and generate interest income while ensuring funds remain available to meet future obligations.

---

# Key Concepts to Remember

```text
Placement = Temporary investment of excess cash

Goal:
- Earn interest
- Preserve capital
- Maintain liquidity

Main Types:
- Overnight
- Tomorrow Next
- Spot Next
- Term Deposit
- Call Deposit
- Notice Deposit

Main Risks:
- Credit Risk
- Liquidity Risk
- Interest Rate Risk
- Operational Risk

Main Reconciliations:
- Cash Reconciliation
- Treasury Reconciliation
- Accounting Reconciliation
- Interest Reconciliation
```