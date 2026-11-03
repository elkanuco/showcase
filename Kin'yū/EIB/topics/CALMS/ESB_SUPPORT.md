# ESB Support, SWIFT Messaging, and Kafka Integration in CALMS (EIB Context)

## Overview

Within the European Investment Bank (EIB), **ESB Support** is responsible for ensuring the reliable exchange of messages between internal applications, external banking networks, and integration platforms.

ESB (Enterprise Service Bus) acts as the integration layer that connects systems such as:

- SWIFT infrastructure
- CALMS
- Kafka
- Data Warehouses
- Reporting platforms
- Other EIB applications

```text
SWIFT
   |
   v
+-----------+
| ESB Layer |
+-----------+
   |
   +----> CALMS
   |
   +----> Kafka
   |
   +----> Data Warehouse
   |
   +----> Other Systems
```

The main objective of ESB Support is to ensure that business-critical messages are transmitted securely, correctly, and on time.

---

## Key Responsibilities of ESB Support

### Message Monitoring

Monitor message flows across all integration channels to ensure successful delivery.

### Incident Investigation

Investigate situations where:

- Messages are delayed
- Messages are lost
- Systems fail to communicate
- Invalid message formats are detected

### Message Replay and Recovery

Resubmit or replay messages when processing failures occur.

### Routing Management

Ensure messages are routed to the correct target systems.

### Transformation Services

Convert messages between different formats required by internal and external systems.

### Operational Support

Provide support for:

- Production incidents
- Integration failures
- Performance bottlenecks
- Capacity monitoring

---

## ESB and SWIFT Messaging

SWIFT is one of the most critical integrations in the banking environment.

### Inbound SWIFT Messages

```text
SWIFT Network
      |
      v
     ESB
      |
      v
    CALMS
```

Examples include:

- MT103
- MT202
- MT940
- MT942
- ISO 20022 MX messages

### ESB Processing Flow

When a SWIFT message arrives:

1. Message received from SWIFT Gateway.
2. Format validation performed.
3. Business routing rules applied.
4. Additional metadata enrichment (if required).
5. Message forwarded to CALMS.

---

### Outbound SWIFT Messages

```text
CALMS
   |
   v
 ESB
   |
   v
SWIFT Gateway
   |
   v
SWIFT Network
```

Typical process:

1. CALMS generates a payment or business instruction.
2. ESB transforms the message into the required SWIFT format.
3. Message is transmitted through the SWIFT infrastructure.
4. Acknowledgements are tracked and processed.

---

## Common SWIFT Issues Investigated by ESB Support

- Rejected messages (NACKs)
- Missing acknowledgements
- Duplicate transmissions
- Connectivity failures
- Validation errors
- Message format issues
- Delayed processing

---

# Kafka's Role in the CALMS Ecosystem

Modern architectures increasingly use Kafka for event streaming and system decoupling.

Traditional integration:

```text
System A ---> ESB ---> System B
```

Event-driven integration:

```text
Producer
   |
   v
Kafka Topic
   |
   +----> CALMS
   +----> Reporting
   +----> Analytics
   +----> Monitoring
```

---

## Kafka Use Cases in CALMS

Kafka may be used for:

- Event distribution
- Near real-time processing
- Asynchronous communication
- Decoupling applications
- Message replay and recovery
- Audit event distribution

Example:

```text
SWIFT Message
      |
      v
     ESB
      |
      v
Kafka Topic
swift.inbound
      |
      +----> CALMS Consumer
      +----> Audit Consumer
      +----> Monitoring Consumer
```

---

# Relationship Between ESB and Kafka

A common integration pattern is:

```text
SWIFT
   |
   v
 ESB
   |
   v
Kafka
   |
   v
CALMS
```

### ESB Responsibilities

- External connectivity
- Security enforcement
- Protocol handling
- Message validation
- Message transformation
- Routing

### Kafka Responsibilities

- Event streaming
- Message persistence
- Scalability
- Consumer decoupling
- Replay capability

---

# Areas Typically Owned by ESB Support

## Connectivity

- SWIFT Gateway connections
- SSL certificates
- MQ/JMS channels
- Service endpoints
- Network communication

## Message Processing

- Routing failures
- Transformation failures
- Invalid payloads
- Delivery failures

## Kafka Integration

- Producer failures
- Consumer lag
- Topic configuration issues
- Dead Letter Queues (DLQ)
- Retry mechanisms
- Offset-related investigations

---

# Typical CALMS Support Scenarios

## Scenario 1: Missing SWIFT Message

Business issue:

> A SWIFT MT940 was received but no corresponding record exists in CALMS.

Investigation path:

```text
SWIFT
   |
   v
Gateway
   |
   v
ESB
   |
   v
Kafka
   |
   v
CALMS
```

Checks performed:

- Was the message received from SWIFT?
- Was it accepted by the ESB?
- Was it routed correctly?
- Was it published to Kafka?
- Was it consumed successfully by CALMS?

---

## Scenario 2: Stuck Payment Instruction

Business issue:

> Payment status was never updated.

Investigation:

```text
CALMS
   |
   v
Kafka
   |
   v
ESB
   |
   v
SWIFT
```

The goal is to identify where processing stopped.

---

## Scenario 3: Kafka Processing Failure

Business issue:

> Expected Kafka events are not being processed.

Checks may include:

- Topic health
- Consumer group status
- Consumer lag
- Message offsets
- Retry queues
- Dead Letter Queues (DLQ)

---

# Traceability and Message Tracking

When investigating issues, ESB Support typically traces messages using:

- SWIFT Reference
- Business Identifier
- Correlation ID
- Kafka Message Key
- UUID
- Payment Reference
- Transaction Identifier

These identifiers help determine:

- Where a message entered the system
- Which systems processed it
- Where failures occurred
- Whether reprocessing is required

---

# Practical View for a CALMS Developer

When someone says:

> "Please raise it with ESB Support"

it usually means the issue may exist somewhere within the integration chain:

```text
SWIFT
 ↓
Gateway
 ↓
ESB
 ↓
Kafka
 ↓
CALMS
```

or

```text
CALMS
 ↓
Kafka
 ↓
ESB
 ↓
SWIFT
```

ESB Support then performs end-to-end tracing to determine whether the issue is caused by:

- SWIFT connectivity
- Message transformation
- Routing failures
- Kafka publication problems
- Kafka consumption problems
- CALMS processing failures

---

# Summary

In the CALMS ecosystem, ESB Support acts as the bridge between business applications and the banking messaging infrastructure.

Their role is to ensure that:

- SWIFT messages are received and delivered correctly.
- Kafka events are published and consumed successfully.
- Message transformations and routing rules are applied correctly.
- Integration incidents are detected and resolved quickly.
- End-to-end message flow remains reliable across the EIB application landscape.

From a CALMS perspective, ESB Support is often the primary team responsible for investigating issues occurring anywhere between:

```text
CALMS ↔ Kafka ↔ ESB ↔ SWIFT
```

and ensuring reliable processing of all financial and operational messages.