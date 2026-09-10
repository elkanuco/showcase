# Treasury Concepts: Credit Lines, Mandates, Counterparties, Liquidity Products, Mandate Balances, Time-Critical Payments, and LP Exclusion Filters

---

# 1. Credit Lines

## Definition

A **credit line** (or exposure limit) is the maximum amount of financial exposure an institution is willing to take with a counterparty.

It represents a risk management control used to limit potential losses.

```text
Counterparty Limit = €500 Million

Current Exposure = €350 Million

Available Credit Line = €150 Million
```

---

## Why Credit Lines Exist

Treasury departments place funds, execute FX trades, purchase securities, and enter derivatives with many counterparties.

If a counterparty defaults, the institution may lose money.

Credit lines help control:

- Credit risk
- Concentration risk
- Counterparty risk

---

## Types of Credit Lines

### Deposit Line

Maximum amount that can be placed with a bank.

Example:

```text
Bank XYZ Limit

€200 Million
```

---

### FX Line

Maximum FX trading exposure.

Example:

```text
EUR/USD Counterparty Limit

€100 Million
```

---

### Derivative Line

Maximum exposure arising from:

- Swaps
- Options
- Futures
- Forwards

---

### Settlement Line

Controls settlement exposure during transaction settlement windows.

---

# 2. Mandates

## Definition

A **mandate** defines the rules and authority under which funds can be invested.

Think of a mandate as:

```text
Investment Policy
+
Risk Limits
+
Authorized Products
+
Eligible Counterparties
```

A mandate tells treasury:

```text
What can be done

How much can be invested

With whom it can be done
```

---

## Example

Liquidity mandate:

```text
Target:
Capital Preservation

Rating:
Minimum AA-

Maximum Maturity:
1 Year

Products Allowed:
Deposits
Money Market Funds
Government Bonds
```

---

## Why Mandates Exist

Mandates ensure:

- Regulatory compliance
- Risk control
- Governance
- Auditability

---

# 3. Counterparties

## Definition

A **counterparty** is the external institution participating in a transaction.

Example:

```text
EIB
 ↓
Places Deposit
 ↓
Bank ABC
```

Bank ABC is the counterparty.

---

## Types of Counterparties

### Commercial Banks

Examples:

```text
BNP Paribas

Deutsche Bank

ING
```

---

### Central Banks

Examples:

```text
ECB

Banque de France
```

---

### Supranational Institutions

Examples:

```text
EIB

EBRD

World Bank
```

---

### Asset Managers

Example:

```text
BlackRock

Amundi
```

---

## Counterparty Assessment

Typical criteria:

- Credit rating
- CDS spread
- Financial strength
- Country risk
- Capital ratios

---

# 4. Liquidity Products

## Definition

Liquidity products are financial instruments used to manage surplus or short-term funding needs.

The primary objective:

```text
Preserve Capital
Maintain Liquidity
Generate Yield
```

---

## Common Liquidity Products

### Overnight Deposits

```text
Maturity: 1 day
```

---

### Time Deposits

```text
1 Week

1 Month

3 Months
```

---

### Call Accounts

Funds can be withdrawn immediately.

---

### Notice Accounts

Withdrawal requires advance notice.

Example:

```text
7-day notice

30-day notice
```

---

### Money Market Funds (MMF)

Highly liquid pooled investment funds.

Invest in:

- Commercial Paper
- Deposits
- Treasury Bills

---

### Treasury Bills

Short-term government securities.

---

### Commercial Paper

Short-term corporate debt.

---

### Certificates of Deposit (CD)

Bank-issued short-term debt instruments.

---

### Reverse Repos

Treasury lends cash against collateral.

```text
Cash Out
 ↓
Receive Securities
 ↓
Cash Returned at Maturity
```

---

# 5. Mandate Balances

## Definition

A mandate balance represents the amount of cash currently allocated under a specific investment mandate.

Example:

```text
Liquidity Mandate

Limit:
€5 Billion

Current Balance:
€3.8 Billion
```

---

## Why It Matters

Treasury must monitor:

```text
Mandate Limit

vs

Current Utilization
```

to avoid breaches.

---

## Example

```text
Mandate Capacity:
€5 Billion

Invested:
€4.6 Billion

Available:
€400 Million
```

---

# 6. Time-Critical Payments

## Definition

Time-critical payments are payments that must be executed before a defined deadline.

Missing the deadline can have:

- Financial consequences
- Regulatory consequences
- Reputation impact

---

## Examples

### Debt Repayments

```text
Bond Redemption
```

Must settle on maturity date.

---

### Bond Coupons

```text
Interest payment to investors
```

Must be paid on schedule.

---

### Loan Disbursements

```text
Contractual payment date
```

---

### Central Bank Payments

```text
TARGET Services deadlines
```

---

### Margin Calls

```text
Derivative collateral payments
```

Often same-day deadlines.

---

## Treasury Monitoring

Time-critical payments are often prioritized:

```text
Priority = High

Cannot be deferred
```

---

# 7. LP Exclusion Filters

## Definition

LP often stands for:

```text
Liquidity Placement
```

or

```text
Liquidity Product
```

depending on the treasury platform.

An **LP Exclusion Filter** is a set of rules used to exclude counterparties, instruments, or transactions from placement eligibility.

---

## Purpose

Prevent treasury from placing funds where doing so would violate:

- Credit limits
- Mandates
- Compliance rules
- Risk policies

---

## Examples

### Credit Rating Filter

Exclude:

```text
Rating < A-
```

Example:

```text
BBB Bank
 ↓
Excluded
```

---

### Credit Line Filter

Exclude counterparties with no remaining capacity.

Example:

```text
Limit = €500M

Current Exposure = €500M

Available = €0

Result = Excluded
```

---

### Country Filter

Exclude jurisdictions.

Example:

```text
Not Allowed:

Country X
Country Y
```

---

### Maturity Filter

Example:

```text
Maximum Target Maturity:

90 Days
```

Products above 90 days are automatically excluded.

---

### Product Filter

Only approved products allowed.

Example:

```text
Allowed:
- Deposits
- MMFs
- T-Bills

Excluded:
- Equities
- High Yield Bonds
```

---

### ESG Filter

Exclude counterparties that fail sustainability criteria.

---

# Example Treasury Workflow

```text
Available Cash
      ↓
Check Mandate
      ↓
Check Credit Lines
      ↓
Check Counterparty Limits
      ↓
Apply LP Exclusion Filters
      ↓
Select Eligible Liquidity Product
      ↓
Execute Placement
      ↓
Monitor Mandate Balances
      ↓
Reconcile Settlement
```

---

# How These Concepts Relate

```text
Counterparty
      ↓
Assigned Credit Line
      ↓
Governed by Mandate
      ↓
Eligible via LP Filters
      ↓
Used for Liquidity Product
      ↓
Consumes Mandate Balance
      ↓
May Support Time-Critical Payments
```

---

# EIB Treasury Interview Summary

## Credit Line

```text
Maximum exposure allowed to a counterparty.
```

## Mandate

```text
Investment rules and constraints governing treasury activity.
```

## Counterparty

```text
Institution with which treasury executes transactions.
```

## Liquidity Product

```text
Instrument used to invest or fund short-term cash.
```

## Mandate Balance

```text
Current amount invested under a mandate.
```

## Time-Critical Payment

```text
Payment that must settle before a fixed deadline.
```

## LP Exclusion Filter

```text
Rule-based control preventing placements that violate risk, compliance, liquidity, or mandate requirements.
```