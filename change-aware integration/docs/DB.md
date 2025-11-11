# DB model

> [!NOTE]
>
> the following DB use cases involve 20 People with roles across all categories</br></br>
> Multi-role, Multi-project Power Users:</br>
> Sarah Mitchell: Corporate/Program Management + Executive (across 2 projects)</br>
> James Anderson: Senior User + Project Manager + Scrum Master (3 roles, 1 project)</br>
> Michael O'Brien: Tech Lead + Developer on Project 1, Developer on Project 2 (multi-project)</br>
> Maria Garcia: Senior Supplier + Service Owner on Project 1, Service Owner on Project 3</br></br>
> Multi-role Single Project:</br>
> Carlos Santos: Service Manager + Change Manager + Release Manager (ITIL specialist)</br>
> Emily Watson: Product Owner + Product Manager</br>
> Ahmed Hassan: Scrum Master + Agile Coach</br></br>
> Single Role Examples:</br>
> Lisa Rodriguez, David Kim, Sophie Laurent, Thomas Mueller, Anna Kowalski, Marcus Johnson, Isabella Rossi, Oliver Smith, Nina Petrov (each with only 1 role)

## Database Structure

### Core Tables

    projects - stores project information
    people - stores person details including picture URL
    role_categories - groups roles hierarchically (7 categories)
    roles - individual roles with hierarchy within categories
    project_assignments - junction table linking people to projects with specific roles

### Roles

    Corporate & Directing (Highest) - provides mandate
    Project Board (Prince2) - Executive, Senior User, Senior Supplier
    Project Management - Project Manager, Program Manager, Project Support
    Service Management (ITIL4) - Service Owner, Change Manager, Release Manager
    Team Management (Scrum) - Scrum Master, Agile Coach, Team Lead
    Quality & Assurance - Project Assurance, QA Manager, Test Manager
    Development Team - Product Owner, Tech Lead, Developer, DevOps Engineer
    Stakeholder & Business - Business Analyst, Product Manager, Stakeholder

### Key Features

    Multi-role support: People can have multiple roles in the same project
    Multi-project support: People can work on multiple projects with different roles
    Hierarchy: Both categories and roles within categories have hierarchy levels
    Business rule: Trigger ensures people have at least one role per project
    Convenient views: Pre-built views for common queries
    Sample data: Includes test data to get started
    Random avatars: Uses pravatar.cc for picture URLs
    The schema uses UUIDs for flexibility and includes indexes for optimal query performance.

### Hierarchy

```ascii
Level 110: Corporate & Directing
    ├── Corporate/Program Management (provides mandate)
    └── Portfolio Director
           │
           │ appoints
           ▼
Level 100: Project Board
    ├── Executive (appointed by corporate)
    ├── Senior User
    └── Senior Supplier
           │
           │ directs
           ▼
Level 90: Project Management
    ├── Project Manager
    ├── Program Manager
    └── Project Support
           │
Level 85: Service Management (ITIL4)
    ├── Service Owner
    ├── Service Manager
    ├── Change Manager
    ├── Release Manager
    └── Configuration Manager

Level 80: Team Management (Scrum/Agile)
    ├── Scrum Master
    ├── Agile Coach
    └── Team Lead

Level 75: Quality & Assurance
    ├── Project Assurance
    ├── Quality Assurance Manager
    └── Test Manager

Level 70: Development Team
    ├── Product Owner
    ├── Tech Lead
    ├── Developer
    ├── DevOps Engineer
    └── UX/UI Designer

Level 65: Stakeholder & Business
    ├── Business Analyst
    ├── Product Manager
    └── Stakeholder
```

## Table Relationships

```ascii
role_categories (1) ──────< (M) roles

projects (1) ──────< (M) project_assignments >────── (M) people
                            │
                            └────< (M) roles
```

## Schema

```ascii
┌─────────────────────────┐
│   role_categories       │
├─────────────────────────┤
│ PK id (UUID)            │
│    name                 │
│    description          │
│    hierarchy_level      │
└───────────┬─────────────┘
            │ 1
            │
            │ M
┌───────────┴─────────────┐
│   roles                 │
├─────────────────────────┤
│ PK id (UUID)            │
│ FK category_id          │
│    name                 │
│    description          │
│    hierarchy_level      │
└───────────┬─────────────┘
            │
            │ M
            │
            │
           ┌┴─────────────────────────────────────┐
           │                                      │
           │                                      │
┌──────────┴──────────┐      ┌────────────────────┴────┐
│   projects          │      │   people                │
├─────────────────────┤      ├─────────────────────────┤
│ PK id (UUID)        │      │ PK id (UUID)            │
│    name             │      │    full_name            │
│    description      │      │    picture_url          │
└──────────┬──────────┘      └──────────┬──────────────┘
           │ 1                          │ 1
           │                            │
           │ M                        M │
           │         ┌──────────────────┘
           │         │
           └────►┌───┴──────────────────────┐
                 │ project_assignments      │
                 ├──────────────────────────┤
                 │ PK id (UUID)             │
                 │ FK project_id            │
                 │ FK person_id             │
                 │ FK role_id               │
                 └──────────────────────────┘
```

| [Back](../README.md)|
|--------|
