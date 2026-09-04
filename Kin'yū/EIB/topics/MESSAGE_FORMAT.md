# SWIFT and ISO 20022 Message Formats: Complete Technical Guide

## Executive Summary

Both **SWIFT MT** and **ISO 20022** follow strict, internationally standardized protocols.

They are not arbitrary message formats.

Each message:

- Has a predefined structure
- Uses a published standard
- Follows validation rules
- Can be machine-validated
- Is understood identically by all participating financial institutions worldwide

Think of them as the equivalent of:

```text
HTTP  -> Web communications

SMTP  -> Email communications

SWIFT MT / ISO 20022 -> Financial communications
```

SWIFT provides the network and messaging services, while standards such as ISO 15022 and ISO 20022 define the message content and structure. 

---

# Financial Messaging Stack

Financial messaging usually consists of several layers.

```text
┌─────────────────────────────┐
│ Business Process            │
│ Payment, Settlement, FX     │
├─────────────────────────────┤
│ Message Standard            │
│ MT / ISO 20022              │
├─────────────────────────────┤
│ Message Syntax              │
│ Tags or XML                 │
├─────────────────────────────┤
│ Transport Network           │
│ SWIFTNet / T2 / SEPA        │
├─────────────────────────────┤
│ Security Layer              │
│ Authentication/Encryption   │
└─────────────────────────────┘
```

A payment message therefore contains:

1. Business meaning
2. Format definition
3. Validation rules
4. Transport mechanism



---

# SWIFT MT Format (Legacy Standard)

## What Is SWIFT MT?

MT means:

```text
Message Type
```

The MT family is based primarily on the **ISO 15022** standard.

Examples:

```text
MT103  Customer Payment

MT202  Interbank Transfer

MT300  FX Confirmation

MT940  Statement

MT942  Intraday Report
```



---

# SWIFT MT Syntax

SWIFT MT messages use a **tag-based syntax**.

Example:

```swift
:20:ABC12345
:32A:260904EUR1000,00
:50K:JOHN SMITH
:59:ABC COMPANY
```

Each tag has a predefined meaning.

For example:

```text
:20: Transaction Reference

:23B: Bank Operation Code

:32A: Value Date + Currency + Amount

:50K: Ordering Customer

:59: Beneficiary Customer

:71A: Charges Information
```

Every bank understands these fields exactly the same way.



---

# SWIFT MT Message Structure

A SWIFT MT message consists of blocks.

```text
Block 1 : Basic Header

Block 2 : Application Header

Block 3 : User Header

Block 4 : Business Content

Block 5 : Trailer
```

Example:

```swift
{1:F01BANKBEBBAXXX0000000000}
{2:I103BANKDEFFXXXXN}
{4:
:20:REF123456
:23B:CRED
:32A:260904EUR1000,00
:50K:/12345678
JOHN SMITH
:59:/98765432
ABC COMPANY
:71A:SHA
-}
```

The actual payment information is primarily stored in Block 4.



---

# SWIFT MT Validation Rules

Every MT message has strict rules.

Example:

```text
MT103

Field 20
Mandatory

Maximum 16 characters

Reference format validation
```

Example:

```text
Field 32A

Date
Currency
Amount

Must follow:
YYMMDDCCYAmount
```

Valid:

```text
260904EUR1000,00
```

Invalid:

```text
EUR1000
```

Messages not respecting the format are rejected automatically.

---

# MT Message Categories

## Category 1

Customer Payments

```text
MT101
MT102
MT103
```

---

## Category 2

Financial Institution Transfers

```text
MT202
MT205
```

---

## Category 3

Treasury Markets

```text
MT300
MT320
MT330
```

---

## Category 5

Securities

```text
MT500+
```

---

## Category 9

Cash Management

```text
MT940
MT942
MT950
```



---

# ISO 20022 Format (Modern Standard)

## What Is ISO 20022?

ISO 20022 is the modern global financial messaging standard.

Unlike MT messages:

```text
MT = Tag Based

ISO 20022 = XML Based
```

ISO 20022 is now the strategic standard used by:

- SWIFT CBPR+
- SEPA
- ECB Target Services
- Instant Payments
- Major central banks



---

# ISO 20022 Message Syntax

ISO 20022 uses XML.

Example:

```xml
<Document>

  <FIToFICstmrCdtTrf>

    <GrpHdr>
      <MsgId>ABC123</MsgId>
    </GrpHdr>

    <CdtTrfTxInf>

      <Amt>
          <InstdAmt Ccy="EUR">
              1000.00
          </InstdAmt>
      </Amt>

      <Dbtr>
         <Nm>John Smith</Nm>
      </Dbtr>

      <Cdtr>
         <Nm>ABC Company</Nm>
      </Cdtr>

    </CdtTrfTxInf>

  </FIToFICstmrCdtTrf>

</Document>
```

This structure is much richer than MT messages.



---

# ISO 20022 Naming Convention

Message names follow:

```text
BusinessArea.MessageNumber.Version
```

Examples:

```text
pacs.008.001.08

pacs.009.001.08

camt.053.001.08

pain.001.001.09
```

Where:

```text
pacs
=
Payments Clearing and Settlement

camt
=
Cash Management

pain
=
Payment Initiation

acmt
=
Account Management

semt
=
Securities Management
```



---

# ISO 20022 Schema Definition

Every message is defined by an XML Schema Definition (XSD).

Example:

```text
pacs.008.001.08.xsd
```

The schema specifies:

- Mandatory fields
- Optional fields
- Data types
- Length restrictions
- Validation logic
- Relationships between elements

Example:

```xml
<MsgId>
```

Could be defined as:

```text
String

Maximum 35 Characters

Mandatory
```

Banks validate messages against the XSD before processing.



---

# Major ISO 20022 Families

## PAIN

Payment Initiation

Examples:

```text
pain.001
Customer Credit Transfer

pain.002
Payment Status Report

pain.008
Direct Debit Initiation
```

Used by corporates and treasury systems.

---

## PACS

Payments Clearing and Settlement

Examples:

```text
pacs.002
Payment Status

pacs.004
Payment Return

pacs.008
Customer Credit Transfer

pacs.009
Financial Institution Transfer
```

Used by banks.

---

## CAMT

Cash Management

Examples:

```text
camt.052
Intraday Reporting

camt.053
End-of-Day Statement

camt.054
Debit/Credit Notification
```

Used by Treasury.

---

## ACMT

Account Management

Examples:

```text
Account Opening

Account Maintenance

Account Closing
```

---

## SEMT

Securities Management

Examples:

```text
Position Reporting

Securities Reconciliation
```

---

## AUTH

Regulatory Reporting

Examples:

```text
Regulatory reporting exchanges
```

---

# MT vs ISO 20022 Comparison

## MT103

```text
:20:REF123

:32A:260904EUR1000,00

:50K:JOHN SMITH

:59:ABC COMPANY
```

---

## PACS.008

```xml
<MsgId>REF123</MsgId>

<InstdAmt Ccy="EUR">
1000.00
</InstdAmt>

<Dbtr>
  <Nm>John Smith</Nm>
</Dbtr>

<Cdtr>
  <Nm>ABC Company</Nm>
</Cdtr>
```

Both represent the same business transaction.

The XML version provides far richer and more structured information.



---

# Message Transport Protocols

A common misconception:

> SWIFT MT and ISO 20022 are not the transport protocols themselves.

Instead:

```text
Message Format
      ≠
Network Transport
```

Examples:

## SWIFT FIN

Traditionally transports MT messages.

---

## SWIFT InterAct

Typically transports ISO 20022 XML.

---

## SWIFT FileAct

Used for bulk file transfers.

---

## TARGET Services

ECB settlement infrastructure.

Uses ISO 20022.

---

## SEPA

Uses ISO 20022.

---

## CBPR+

Cross-border payment standard.

Uses ISO 20022.



---

# Example End-to-End Payment Flow

```text
Corporate ERP

   ↓

pain.001

   ↓

Bank

   ↓

pacs.008

   ↓

Settlement

   ↓

pacs.009

   ↓

Beneficiary Bank

   ↓

camt.054

   ↓

Treasury System

   ↓

camt.053
```

---

# What You Would Typically See at EIB

Treasury and Liquidity teams commonly interact with:

## Payments

```text
pacs.008

pacs.009

pacs.002
```

## Cash Management

```text
camt.052

camt.053

camt.054
```

## Corporate Treasury Integration

```text
pain.001

pain.002
```

## Legacy Systems

```text
MT103

MT202

MT940

MT942
```

Many banks still display MT terminology even when the underlying system is processing ISO 20022 XML.

---

# Interview Summary

```text
SWIFT MT
=
ISO 15022
=
Tag-Based Format

Example:
:20:
:32A:
:59:
```

```text
ISO 20022
=
XML-Based Format
=
XSD Validated

Example:
<MsgId>
<Amt>
<Dbtr>
<Cdtr>
```

```text
PAIN
=
Payment Initiation

PACS
=
Payment Processing

CAMT
=
Cash Management and Reporting
```

```text
SWIFT
=
Network

MT / ISO 20022
=
Message Standards
```

**The key distinction is that SWIFT is primarily the communication network, while MT (ISO 15022) and ISO 20022 define the message structure, semantics, validation rules, and business meaning of the financial information exchanged between institutions.**