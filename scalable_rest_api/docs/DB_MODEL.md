# DB MODEL

This document describes the db model

```ascii
+-------------+          +-----------------------+           +------------------+
|   subject   |1        *|        account        |1         *|    transaction   |
+-------------+----------+-----------------------+-----------+------------------+
| id  (PK)    |          | id    (PK)            |           | id   (PK)        |
| type        |          | owner_id (FK )        |---------->| account_id (FK)  |
| name        |          | base_currenc y_id (FK)|           | type             |
+-------------+          | type                  |           | currency_id (FK) |----+
                         | bank_id (FK)          |           | amount           |    |
                         | iban                  |           | created_at       |    |
                         | balance               |           +------------------+    |
                         | name                  |                                   |
                         +-----------------------+                                   |
                              |    |                                                 |
                 +------------+    +---------+                                       |
                 |                           |                                       |
                 V                           V                                       |
           +-----------+               +-----------+                                 |
           |  bank     |               | currency  |<--------------------------------+
           +-----------+               +-----------+
           | id (PK)   |               | id (PK)   |
           | name      |               | code      |
           | bic       |               +-----------+
           +-----------+
```

| [Back](../README.md)|
|--------|
