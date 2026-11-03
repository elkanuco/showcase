# Risk-Based 4-Eyes Review Mechanism

## Overview

The Risk-Based 4-Eyes Review Mechanism ensures that activities are reviewed independently in proportion to their associated risk. By tailoring review requirements to the level of risk, the organization balances operational efficiency with effective governance, risk management, and compliance.

The mechanism aims to:

- Reduce operational, financial, security, and compliance risks.
- Prevent errors, omissions, and unauthorized actions.
- Enforce segregation of duties.
- Improve accountability and traceability.
- Apply stronger controls only where justified by risk.

---

## Scope

This mechanism applies to:

- Application and infrastructure changes
- Production deployments
- Configuration modifications
- Access management activities
- Operational procedures
- Business-critical decisions
- Financial and regulatory processes

---

## Core Principles

### Independence

The reviewer must be independent from the individual performing the activity and capable of assessing its correctness and completeness.

### Risk-Based Control

The level of review is determined by the impact and likelihood of potential failure.

### Segregation of Duties

No individual may initiate, review, and approve the same activity.

### Accountability

Review outcomes, approvals, rejections, and exceptions must be recorded and auditable.

---

## Risk Classification

Activities shall be classified according to the following risk levels:

| Risk Level | Description | Examples |
|------------|-------------|----------|
| Low | Minimal impact and easily reversible | Documentation updates, non-production housekeeping |
| Medium | Limited operational impact with low business disruption | UAT deployments, standard configuration updates |
| High | Significant operational, security, financial, or reputational impact | Production deployments, database schema changes |
| Critical | Enterprise-wide impact or regulatory consequences | Payment processing changes, privileged access modifications, emergency production fixes |

---

## Review Requirements

| Risk Level | Review Requirement |
|------------|-------------------|
| Low | Optional peer review or automated validation |
| Medium | Single independent reviewer required |
| High | Mandatory 4-Eyes review before execution |
| Critical | Mandatory 4-Eyes review plus additional management or process-owner approval |

---

## Review Workflow

```text
+----------------+
| Author         |
+----------------+
        |
        v
+----------------+
| Risk Assessment|
+----------------+
        |
        v
+----------------+
| Submit Review  |
+----------------+
        |
        v
+----------------+
| Reviewer       |
+----------------+
     |       |
     |       |
 Reject    Approve
     |       |
     v       v
 Rework   Execute
     |       |
     +-------+
             |
             v
     +----------------+
     | Audit Record   |
     +----------------+
```

---

## Reviewer Checklist

The reviewer shall verify that:

- Risk classification is appropriate.
- Business justification exists.
- Technical documentation is complete.
- Testing evidence is available.
- Rollback procedures are defined where applicable.
- Security requirements have been addressed.
- Compliance obligations are respected.
- Required approvals are present.
- Execution plans are clear and feasible.

The reviewer may:

- Approve the activity.
- Request modifications.
- Reject the activity.
- Escalate concerns when necessary.

---

## Critical Risk Activities

The following activities typically require enhanced review:

- Production releases affecting customer services.
- Authentication and authorization changes.
- Firewall or network security modifications.
- Financial transaction processing changes.
- Regulatory reporting logic updates.
- Database structure modifications.
- Privileged-access management changes.

Additional approvers may include:

- Service Owner
- Application Owner
- Security Officer
- Process Owner
- Change Advisory Board (CAB)

---

## Emergency Changes

Emergency activities may bypass pre-implementation review when immediate action is necessary to prevent significant business disruption.

### Requirements

1. Emergency justification must be documented.
2. The change must be executed by an authorized individual.
3. A retrospective 4-Eyes review must occur after implementation.
4. Findings must be documented.
5. Corrective actions must be tracked to completion.

---

## Exception Management

Exceptions to this mechanism must:

- Be formally documented.
- Include a business rationale.
- Be approved by the designated authority.
- Define compensating controls where applicable.
- Be retained for audit purposes.

Repeated exceptions should trigger a review of process effectiveness.

---

## Audit Trail Requirements

The following information shall be retained:

- Activity identifier
- Date and time
- Author
- Reviewer
- Risk classification
- Review comments
- Approval decision
- Supporting evidence
- Exception records (if applicable)

---

## Monitoring and Metrics

The effectiveness of the mechanism shall be monitored through:

| Metric | Target |
|----------|----------|
| Activities reviewed before execution | ≥ 95% |
| Critical activities reviewed before execution | 100% |
| Emergency reviews completed retrospectively | 100% |
| Segregation-of-duty violations | 0 |
| Unauthorized changes detected | 0 |

---

## RACI Matrix

| Activity | Author | Reviewer | Process Owner | Manager |
|-----------|--------|----------|---------------|---------|
| Risk Assessment | R | C | A | I |
| Activity Preparation | R | I | I | I |
| Independent Review | I | R | C | I |
| Approval Decision | I | R | A | C |
| Exception Approval | I | C | C | A |
| Audit Verification | I | I | C | A |

**Legend**

- **R** = Responsible
- **A** = Accountable
- **C** = Consulted
- **I** = Informed

---

## Benefits

Implementing a Risk-Based 4-Eyes Review Mechanism provides:

- Improved operational quality.
- Reduced risk exposure.
- Stronger control over critical activities.
- Enhanced regulatory compliance.
- Better audit readiness.
- Efficient handling of low-risk activities.
- Increased organizational accountability.

---

## Governance

The Process Owner is responsible for:

- Maintaining risk classification criteria.
- Defining approval thresholds.
- Reviewing exceptions.
- Periodically assessing control effectiveness.
- Updating this mechanism as organizational risks evolve.

