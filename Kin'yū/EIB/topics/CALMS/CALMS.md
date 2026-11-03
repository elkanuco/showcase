# CALMS Overview

## Purpose

CALMS is a business-critical EIB platform involved in the processing, tracking, distribution, and management of financial and operational messages

# CALMS - SWIFT / ESB / Kafka Detailed Sequence Diagram

```mermaid
sequenceDiagram

    autonumber

    participant SWIFT as SWIFT Network
    participant ESB as ESB Integration Layer
    participant Kafka as Kafka Topic
    participant CALMS as CALMS Service
    participant DB as Oracle Database
    participant MON as Monitoring/Audit
    participant EXT as Downstream Systems

    rect rgb(240,248,255)
    Note over SWIFT,CALMS: Inbound Financial Message Flow
    end

    SWIFT->>ESB: MT/MX Message
    activate ESB

    ESB->>ESB: Validate Format
    ESB->>ESB: Apply Routing Rules
    ESB->>ESB: Enrich Metadata

    ESB->>Kafka: Publish Event<br/>swift.inbound

    deactivate ESB

    Kafka-->>CALMS: Consume Event
    activate CALMS

    CALMS->>CALMS: Business Validation

    alt Valid Message

        CALMS->>DB: Persist Message
        activate DB
        DB-->>CALMS: Commit
        deactivate DB

        CALMS->>MON: Publish Audit Event
        CALMS->>EXT: Trigger Downstream Processing

        CALMS->>Kafka: Publish Status Event
        Note right of CALMS: PAYMENT_RECEIVED

    else Invalid Message

        CALMS->>MON: Validation Error
        CALMS->>Kafka: Publish Error Event
        Note right of CALMS: BUSINESS_REJECTED

    end

    deactivate CALMS

    rect rgb(245,255,245)
    Note over CALMS,SWIFT: Outbound Payment Instruction Flow
    end

    CALMS->>DB: Retrieve Transaction
    DB-->>CALMS: Business Data

    CALMS->>Kafka: Publish Outbound Event
    Note right of Kafka: payment.outbound

    Kafka-->>ESB: Consume Event
    activate ESB

    ESB->>ESB: Build SWIFT Payload
    ESB->>ESB: Technical Validation

    alt Validation Successful

        ESB->>SWIFT: Send MT/MX Message
        activate SWIFT

        SWIFT-->>ESB: ACK

        deactivate SWIFT

        ESB->>Kafka: Publish Delivery Status

    else Validation Failed

        ESB->>Kafka: Publish Failure Status
        ESB->>MON: Technical Error

    end

    deactivate ESB

    Kafka-->>CALMS: Delivery Status Event

    activate CALMS
    CALMS->>DB: Update Transaction Status
    DB-->>CALMS: Updated
    deactivate CALMS

    rect rgb(255,248,240)
    Note over ESB,CALMS: Error and Retry Flow
    end

    Kafka-->>CALMS: Message Consumption

    activate CALMS

    alt Processing Exception

        CALMS->>MON: Log Error
        CALMS->>Kafka: Retry Event

        loop Configured Retries
            Kafka-->>CALMS: Redelivery
            CALMS->>CALMS: Reprocess Message
        end

        alt Retries Exhausted

            CALMS->>Kafka: Dead Letter Queue (DLQ)
            CALMS->>MON: Raise Incident

        end

    end

    deactivate CALMS

    rect rgb(255,240,240)
    Note over MON,ESB: Production Support Investigation
    end

    MON->>ESB: Incident Raised
    MON->>CALMS: Incident Raised

    ESB->>Kafka: Search by Correlation ID
    CALMS->>DB: Search by Reference Number

    DB-->>CALMS: Message History
    Kafka-->>ESB: Topic/Offset Details

    ESB-->>MON: Integration Findings
    CALMS-->>MON: Business Findings
```
----
```mermaid
flowchart LR

    A[SWIFT Network]
    B[ESB]
    C[Kafka]
    D[CALMS]
    E[Oracle]
    F[Audit & Monitoring]
    G[Downstream Systems]

    A --> B
    B --> C
    C --> D
    D --> E

    D --> F
    D --> G

    G --> C
    C --> B
    B --> A
```
----
```mermaid
sequenceDiagram

    participant User as CALMS Support
    participant CALMS
    participant Oracle
    participant Kafka
    participant ESB
    participant SWIFT

    User->>CALMS: Search Reference Number

    CALMS->>Oracle: Transaction Lookup
    Oracle-->>CALMS: Found

    CALMS->>Kafka: Check Correlation ID
    Kafka-->>CALMS: Offset & Timestamp

    CALMS->>ESB: Verify Delivery
    ESB-->>CALMS: Delivered

    ESB->>SWIFT: Verify ACK
    SWIFT-->>ESB: ACK/NACK

    ESB-->>User: Integration Status
    CALMS-->>User: Business Status
```