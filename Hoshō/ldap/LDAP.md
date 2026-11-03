# LDAP Complete Reference Guide

> A practical and technical guide to LDAP, Active Directory, Distinguished Names, Base DNs, Bind Accounts, Search Filters, Organizational Units, and Authentication.

---

# Table of Contents

1. What is LDAP?
2. LDAP Terminology
3. LDAP Directory Structure
4. Distinguished Names (DN)
5. Common Naming Conventions
6. Base DN
7. Bind DN / Principal
8. Organizational Units (OU)
9. Common Name (CN)
10. Domain Components (DC)
11. Typical Active Directory Structure
12. LDAP Authentication Flow
13. LDAP Search Operations
14. LDAP Filters
15. User Lookup Examples
16. Group Lookup Examples
17. Common Active Directory Attributes
18. LDAP URLs
19. Service Accounts
20. Security Considerations
21. Troubleshooting
22. Real World Example
23. Spring Security Configuration Example
24. Best Practices

---

# What is LDAP?

**LDAP** (Lightweight Directory Access Protocol) is a protocol used to access and manage directory services.

A directory service stores information in a hierarchical structure similar to a filesystem.

Examples:

- Users
- Groups
- Computers
- Service Accounts
- Printers
- Organizational Structures

Most enterprises use:

- Microsoft Active Directory
- OpenLDAP
- Red Hat Directory Server
- Apache Directory Server

---

# LDAP Terminology

| Term | Meaning |
|--------|---------|
| LDAP | Directory access protocol |
| DN | Distinguished Name |
| RDN | Relative Distinguished Name |
| CN | Common Name |
| OU | Organizational Unit |
| DC | Domain Component |
| Base DN | Search root |
| Bind DN | Authentication account |
| UPN | User Principal Name |

---

# LDAP Directory Structure

LDAP is hierarchical.

Example:

```text
DC=DEV
└── DC=XXX
    └── DC=ORG
        ├── OU=Users
        │   ├── CN=John Doe
        │   └── CN=Jane Smith
        │
        └── OU=Groups
            ├── CN=Administrators
            └── CN=Developers
```

Comparable filesystem:

```text
C:\
└── Users\
    └── John
```

LDAP:

```text
DC=DEV,DC=XXX,DC=ORG
 └── OU=Users
      └── CN=John Doe
```

---

# Distinguished Name (DN)

A DN uniquely identifies an object.

Example:

```text
CN=John Doe,OU=Users,DC=DEV,DC=XXX,DC=ORG
```

Read it from left to right:

```text
Object:
CN=John Doe

Inside:
OU=Users

Inside domain:
DC=DEV,DC=XXX,DC=ORG
```

A DN is equivalent to a full path.

Filesystem:

```text
C:\Users\JohnDoe
```

LDAP:

```text
CN=John Doe,OU=Users,DC=DEV,DC=XXX,DC=ORG
```

---

# Relative Distinguished Name (RDN)

The first component of a DN.

Example:

```text
CN=John Doe
```

within

```text
CN=John Doe,OU=Users,DC=DEV,DC=XXX,DC=ORG
```

RDN:

```text
CN=John Doe
```

---

# Domain Components (DC)

DC represents parts of the DNS domain.

Domain:

```text
xxx.org
```

LDAP:

```text
DC=DEV,DC=XXX,DC=ORG
```

Another example:

```text
corp.example.com
```

becomes:

```text
DC=corp,DC=example,DC=com
```

---

# Organizational Units (OU)

OU represents logical containers.

Examples:

```text
OU=Users
OU=Groups
OU=Applications
OU=Service Accounts
OU=Production
```

Nested OUs are common.

Example:

```text
OU=Service Accounts,
OU=Administrative Accounts,
OU=XXX - Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

Tree:

```text
xxx.ORG
└── XXX - Delegation
    └── Administrative Accounts
         └── Service Accounts
```

---

# Common Name (CN)

CN identifies an object or container.

Examples:

```text
CN=John Doe
CN=svc
CN=Administrators
CN=Users
```

Examples:

```text
CN=John Doe,OU=Users,DC=DEV,DC=XXX,DC=ORG
```

```text
CN=svc,OU=Service Accounts,DC=DEV,DC=XXX,DC=ORG
```

---

# Base DN

The Base DN defines where searches begin.

Search entire domain:

```text
DC=DEV,DC=XXX,DC=ORG
```

Search only users:

```text
OU=Users,DC=DEV,DC=XXX,DC=ORG
```

Search only service accounts:

```text
OU=Service Accounts,
OU=Administrative Accounts,
OU=XXX - Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

---

# Bind DN / Principal

Before searching LDAP, applications authenticate.

The account used for authentication is called:

- Bind DN
- Principal
- Service Account

Examples:

### Distinguished Name format

```text
CN=svc,
OU=Service Accounts,
OU=Administrative Accounts,
OU=XXX - Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

### UPN Format

```text
svc@xxx.org
```

### Legacy AD format

```text
DEV\svc
```

---

# Authentication Flow

```text
Application
      |
      v
LDAP Bind
      |
      v
Directory Authenticates Service Account
      |
      v
Search User
      |
      v
Validate Password
      |
      v
Authorize User
```

---

# LDAP Search Operation

Application sends:

```text
Base DN
Search Scope
Search Filter
```

Example:

```text
Base DN:
DC=DEV,DC=XXX,DC=ORG

Filter:
(sAMAccountName=jdoe)
```

LDAP returns matching objects.

---

# Search Scopes

## BASE

Search current object only.

```text
BASE
```

## ONELEVEL

Search immediate children only.

```text
ONELEVEL
```

## SUBTREE

Search everything underneath.

```text
SUBTREE
```

Most common:

```text
SUBTREE
```

---

# LDAP Filters

## Exact Match

```text
(cn=John Doe)
```

## By Login

```text
(sAMAccountName=jdoe)
```

## Email

```text
(mail=john.doe@company.com)
```

## All Users

```text
(objectClass=user)
```

## All Groups

```text
(objectClass=group)
```

## AND

```text
(&(objectClass=user)(department=IT))
```

## OR

```text
(|(department=IT)(department=DEV))
```

## NOT

```text
(!(department=HR))
```

---

# Active Directory Attributes

## User Information

```text
cn
displayName
givenName
sn
mail
telephoneNumber
```

Examples:

```text
displayName=John Doe
mail=john.doe@company.com
```

---

## Authentication Attributes

```text
sAMAccountName
userPrincipalName
```

Examples:

```text
sAMAccountName=jdoe

userPrincipalName=jdoe@xxx.org
```

---

## Group Membership

```text
memberOf
```

Example:

```text
memberOf=CN=Developers,OU=Groups,DC=DEV,DC=XXX,DC=ORG
```

---

## Management

```text
manager
department
company
title
```

---

# Common Active Directory Queries

## Find User

```text
(sAMAccountName=jdoe)
```

## Find Group

```text
(cn=Developers)
```

## Find Email

```text
(mail=*)
```

## Find Enabled Users

```text
(&(objectCategory=Person)
  (objectClass=User))
```

---

# LDAP URLs

LDAP:

```text
ldap://server.xxx.org:389
```

Secure LDAP:

```text
ldaps://server.xxx.org:636
```

Global Catalog:

```text
ldap://server.xxx.org:3268
```

Secure Global Catalog:

```text
ldaps://server.xxx.org:3269
```

---

# Service Accounts

Applications should not use human accounts.

Use dedicated service accounts.

Example:

```text
CN=svc,
OU=Service Accounts,
OU=Administrative Accounts,
OU=XXX - Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

Naming convention:

```text
svc_application
```

Examples:

```text
svc
```

---

# Security Considerations

## Do

Use:

```text
LDAPS (636)
```

Use:

```text
TLS
```

Use:

```text
Least privilege
```

Use:

```text
Dedicated service accounts
```

Rotate passwords regularly.

---

## Avoid

Anonymous binds.

```text
anonymous
```

Using administrator accounts.

```text
Administrator
```

Storing credentials in plaintext.

---

# Troubleshooting

## Invalid Credentials

Error:

```text
LDAP Error 49
```

Usually means:

- Wrong password
- Wrong username
- Expired password
- Locked account

---

## Cannot Find User

Check:

```text
Base DN
```

Check:

```text
Search Filter
```

Verify:

```text
SUBTREE scope
```

---

## Cannot Connect

Verify:

```text
Host
Port
Firewall
SSL Certificate
```

---

# Real Example

Domain:

```text
xxx.ORG
```

Base DN:

```text
DC=DEV,DC=XXX,DC=ORG
```

Service Account:

```text
CN=svc_dev,
OU=Service Accounts,
OU=Administrative Accounts,
OU=Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

UPN:

```text
svc@xxx.org
```

LDAP URL:

```text
ldaps://ldap.xxx.org:636
```

User Search Filter:

```text
(sAMAccountName={0})
```

Group Search Filter:

```text
(member={0})
```

---

# Spring Security Example

```properties
spring.ldap.urls=ldaps://ldap.xxx.org:636

spring.ldap.base=DC=DEV,DC=XXX,DC=ORG

spring.ldap.username=svc@xxx.org

spring.ldap.password=********
```

User filter:

```properties
(userPrincipalName={0})
```

or

```properties
(sAMAccountName={0})
```

---

# Best Practices

1. Use LDAPS instead of LDAP.
2. Use UPN as bind principal when possible.
3. Keep Base DN sufficiently high in the tree.
4. Use dedicated service accounts.
5. Use SUBTREE searches unless restricted.
6. Search on `sAMAccountName` for AD logins.
7. Limit permissions of bind accounts.
8. Avoid hardcoded passwords.
9. Document OU structures.
10. Monitor failed bind attempts.

---

# Quick Cheat Sheet

## Domain

```text
xxx.org
```

## Base DN

```text
DC=DEV,DC=XXX,DC=ORG
```

## Service Account DN

```text
CN=svc,
OU=Service Accounts,
OU=Administrative Accounts,
OU=XXX - Delegation,
DC=DEV,
DC=XXX,
DC=ORG
```

## Service Account UPN

```text
svc@xxx.org
```

## User Search

```text
(sAMAccountName=jdoe)
```

## Group Search

```text
(cn=Developers)
```

## LDAP

```text
ldap://host:389
```

## LDAPS

```text
ldaps://host:636
```