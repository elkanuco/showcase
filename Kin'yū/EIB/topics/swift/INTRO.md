# SWIFT Movements Explained

## Overview

A **SWIFT movement** refers to the transmission of financial instructions between banks through the **SWIFT (Society for Worldwide Interbank Financial Telecommunication)** network.
SWIFT itself does **not transfer money**.
Instead, it provides a secure and standardized messaging platform that enables banks and financial institutions to communicate payment instructions and other financial messages. 

## What SWIFT Does

SWIFT provides:

- Secure financial messaging
- Standardized payment formats
- Bank identification through BIC/SWIFT codes
- Message validation and authentication
- End-to-end payment tracking capabilities

More than 11,000 financial institutions across over 200 countries use the SWIFT network to exchange payment instructions and financial information. 

## How a SWIFT Movement Works

### Step 1: Payment Initiation

A customer instructs their bank to send funds internationally by providing:

- Beneficiary name
- Account number or IBAN
- Beneficiary bank SWIFT/BIC code
- Currency
- Transfer amount

The sending bank creates a SWIFT message, commonly an **MT103** for customer credit transfers. 

### Step 2: Message Transmission

The sending bank transmits the payment instruction through the SWIFT network to the receiving bank or intermediary banks.

SWIFT ensures:

- Secure communication
- Authentication
- Data integrity
- Regulatory compliance support



### Step 3: Correspondent Banking

Many banks do not maintain direct accounts with every foreign bank.

When no direct relationship exists, one or more correspondent banks facilitate the transaction.

```text
Customer
    │
    ▼
Sending Bank
    │
    ▼
SWIFT Message
    │
    ▼
Correspondent Bank(s)
    │
    ▼
Beneficiary Bank
    │
    ▼
Recipient
```

The actual funds move between banks through correspondent banking relationships. 

### Step 4: Settlement

Settlement occurs through:

- Nostro accounts
- Vostro accounts
- Real-Time Gross Settlement (RTGS) systems
- Domestic payment infrastructures

The SWIFT message carries the instruction, while settlement mechanisms move the funds. 

## Common Types of SWIFT Movements

### Customer Credit Transfers

International payments between individuals or businesses.

**Typical Message Type:** MT103



### Bank-to-Bank Transfers

Payments exchanged directly between financial institutions for liquidity management, treasury operations, or settlement purposes. 

### Trade Finance Transactions

Used for:

- Letters of Credit
- Bank Guarantees
- Documentary Collections



### Securities Transactions

Instructions and confirmations related to:

- Bond settlements
- Equity settlements
- Custody operations



### Treasury and Foreign Exchange Operations

Used for:

- FX settlements
- Money market transactions
- Liquidity management



## Key Information Contained in a SWIFT Payment

A typical SWIFT payment message contains:

- Sender Bank BIC
- Receiver Bank BIC
- Beneficiary details
- Amount
- Currency
- Value date
- Payment reference
- Charges and fee instructions
- UETR (Unique End-to-End Transaction Reference)



## Tracking SWIFT Movements

### UETR

The **Unique End-to-End Transaction Reference (UETR)** is a globally unique identifier assigned to a payment transaction.

Benefits include:

- Transaction traceability
- Faster investigations
- Improved transparency



### SWIFT gpi

**SWIFT Global Payments Innovation (gpi)** enhances:

- End-to-end tracking
- Fee transparency
- Faster processing
- Payment status visibility



## Common Causes of Delays

SWIFT payments may be delayed due to:

- Compliance reviews
- Sanctions screening
- Incorrect beneficiary information
- Missing intermediary bank details
- Regulatory requirements
- Foreign exchange processing



## Relevance for the European Investment Bank (EIB)

For institutions such as the European Investment Bank (EIB), SWIFT movements support:

- Loan disbursements
- Grant payments
- Treasury operations
- Capital market transactions
- Cross-border project financing
- Correspondent banking activities

SWIFT provides the secure messaging infrastructure that enables these international financial operations. 

## Summary

A SWIFT movement is the exchange of standardized financial messages between banks through the SWIFT network. While SWIFT does not move money itself, it provides the secure communication channel that instructs banks how funds should be transferred and settled across domestic and international banking systems. 