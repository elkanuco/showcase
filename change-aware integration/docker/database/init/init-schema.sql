-- Project Management Database Schema
-- Integrating PRINCE2, ITIL4, and Scrum roles with hierarchical structure

-- Enable UUID extension for generating unique identifiers
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ============================================
-- CORE ENTITIES
-- ============================================

-- Projects table
CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- People table
CREATE TABLE people (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(255) NOT NULL,
    picture_url TEXT
);

-- ============================================
-- ROLE HIERARCHY
-- ============================================

-- Role categories (top level grouping)
CREATE TABLE role_categories (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    hierarchy_level INTEGER NOT NULL -- Higher number = higher in hierarchy
);

-- Roles table with category association
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    category_id UUID NOT NULL REFERENCES role_categories(id),
    hierarchy_level INTEGER NOT NULL, -- Hierarchy within category
    CONSTRAINT unique_role_category_hierarchy UNIQUE (category_id, hierarchy_level)
);

-- ============================================
-- MANY-TO-MANY RELATIONSHIPS
-- ============================================

-- Junction table: Links people to projects with their roles
CREATE TABLE project_assignments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    project_id UUID NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    person_id UUID NOT NULL REFERENCES people(id) ON DELETE CASCADE,
    role_id UUID NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT unique_project_person_role UNIQUE (project_id, person_id, role_id)
);

-- ============================================
-- INDEXES FOR PERFORMANCE
-- ============================================

CREATE INDEX idx_project_assignments_project ON project_assignments(project_id);
CREATE INDEX idx_project_assignments_person ON project_assignments(person_id);
CREATE INDEX idx_project_assignments_role ON project_assignments(role_id);
CREATE INDEX idx_roles_category ON roles(category_id);
CREATE INDEX idx_people_name ON people(full_name);
CREATE INDEX idx_projects_name ON projects(name);

-- ============================================
-- SEED DATA: ROLE CATEGORIES
-- ============================================

INSERT INTO role_categories (name, description, hierarchy_level) VALUES
('Corporate & Directing', 'PRINCE2: Corporate or program management providing mandate', 110),
('Project Board', 'PRINCE2: Strategic decision-making body providing unified direction', 100),
('Project Management', 'Roles responsible for day-to-day project execution and delivery', 90),
('Service Management', 'ITIL4: Roles focused on service design, transition, and operation', 85),
('Team Management', 'Scrum/Agile: Roles focused on team coordination and facilitation', 80),
('Quality & Assurance', 'Roles ensuring quality standards and project assurance', 75),
('Development Team', 'Technical roles responsible for building and delivering work', 70),
('Stakeholder & Business', 'Roles representing business interests and user requirements', 65);

-- ============================================
-- SEED DATA: ROLES
-- ============================================

-- Corporate & Directing (PRINCE2) - Hierarchy 110
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Corporate/Program Management', 'PRINCE2: Provides mandate and appoints Executive, oversees portfolio', 
    (SELECT id FROM role_categories WHERE name = 'Corporate & Directing'), 10),
('Portfolio Director', 'Oversees portfolio of projects and programs', 
    (SELECT id FROM role_categories WHERE name = 'Corporate & Directing'), 9);

-- Project Board (PRINCE2) - Hierarchy 100
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Executive', 'PRINCE2: Ultimate decision-maker, owns business case, appointed by corporate', 
    (SELECT id FROM role_categories WHERE name = 'Project Board'), 10),
('Senior User', 'PRINCE2: Represents end-users and specifies requirements', 
    (SELECT id FROM role_categories WHERE name = 'Project Board'), 9),
('Senior Supplier', 'PRINCE2: Represents technical and resource providers', 
    (SELECT id FROM role_categories WHERE name = 'Project Board'), 8);

-- Project Management - Hierarchy 90
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Project Manager', 'PRINCE2: Day-to-day management, reports to Project Board', 
    (SELECT id FROM role_categories WHERE name = 'Project Management'), 10),
('Project Support', 'PRINCE2: Administrative support for project manager', 
    (SELECT id FROM role_categories WHERE name = 'Project Management'), 5),
('Program Manager', 'Oversees multiple related projects', 
    (SELECT id FROM role_categories WHERE name = 'Project Management'), 9);

-- Service Management (ITIL4) - Hierarchy 85
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Service Owner', 'ITIL4: Accountable for service delivery and performance', 
    (SELECT id FROM role_categories WHERE name = 'Service Management'), 10),
('Service Manager', 'ITIL4: Manages end-to-end service lifecycle', 
    (SELECT id FROM role_categories WHERE name = 'Service Management'), 9),
('Change Manager', 'ITIL4: Controls and coordinates changes', 
    (SELECT id FROM role_categories WHERE name = 'Service Management'), 8),
('Release Manager', 'ITIL4: Plans and controls release deployment', 
    (SELECT id FROM role_categories WHERE name = 'Service Management'), 7),
('Configuration Manager', 'ITIL4: Maintains configuration management database', 
    (SELECT id FROM role_categories WHERE name = 'Service Management'), 6);

-- Team Management (Scrum/Agile) - Hierarchy 80
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Scrum Master', 'Scrum: Facilitates Scrum process, removes impediments', 
    (SELECT id FROM role_categories WHERE name = 'Team Management'), 10),
('Agile Coach', 'Mentors teams in Agile practices and principles', 
    (SELECT id FROM role_categories WHERE name = 'Team Management'), 9),
('Team Lead', 'Leads and coordinates team activities', 
    (SELECT id FROM role_categories WHERE name = 'Team Management'), 8);

-- Quality & Assurance - Hierarchy 75
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Project Assurance', 'PRINCE2: Independent monitoring of project aspects', 
    (SELECT id FROM role_categories WHERE name = 'Quality & Assurance'), 10),
('Quality Assurance Manager', 'Ensures quality standards are met', 
    (SELECT id FROM role_categories WHERE name = 'Quality & Assurance'), 9),
('Test Manager', 'Manages testing strategy and execution', 
    (SELECT id FROM role_categories WHERE name = 'Quality & Assurance'), 8);

-- Development Team - Hierarchy 70
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Product Owner', 'Scrum: Maximizes product value, manages backlog', 
    (SELECT id FROM role_categories WHERE name = 'Development Team'), 10),
('Tech Lead', 'Technical leadership and architecture decisions', 
    (SELECT id FROM role_categories WHERE name = 'Development Team'), 9),
('Developer', 'Scrum: Cross-functional team member building product', 
    (SELECT id FROM role_categories WHERE name = 'Development Team'), 8),
('DevOps Engineer', 'Manages infrastructure and deployment pipelines', 
    (SELECT id FROM role_categories WHERE name = 'Development Team'), 7),
('UX/UI Designer', 'Designs user experience and interface', 
    (SELECT id FROM role_categories WHERE name = 'Development Team'), 6);

-- Stakeholder & Business - Hierarchy 65
INSERT INTO roles (name, description, category_id, hierarchy_level) VALUES
('Business Analyst', 'Analyzes business requirements and processes', 
    (SELECT id FROM role_categories WHERE name = 'Stakeholder & Business'), 10),
('Product Manager', 'Defines product strategy and roadmap', 
    (SELECT id FROM role_categories WHERE name = 'Stakeholder & Business'), 9),
('Stakeholder', 'General stakeholder with interest in project', 
    (SELECT id FROM role_categories WHERE name = 'Stakeholder & Business'), 5);

-- ============================================
-- VIEWS FOR CONVENIENT QUERYING
-- ============================================

-- View: Complete project assignments with role details
CREATE VIEW v_project_assignments_detailed AS
SELECT 
    pa.id AS assignment_id,
    p.id AS project_id,
    p.name AS project_name,
    pe.id AS person_id,
    pe.full_name AS person_name,
    pe.picture_url,
    r.id AS role_id,
    r.name AS role_name,
    r.description AS role_description,
    rc.id AS category_id,
    rc.name AS category_name,
    rc.hierarchy_level AS category_hierarchy,
    r.hierarchy_level AS role_hierarchy
FROM project_assignments pa
JOIN projects p ON pa.project_id = p.id
JOIN people pe ON pa.person_id = pe.id
JOIN roles r ON pa.role_id = r.id
JOIN role_categories rc ON r.category_id = rc.id
ORDER BY rc.hierarchy_level DESC, r.hierarchy_level DESC;

-- View: People with their roles across all projects
CREATE VIEW v_people_roles_summary AS
SELECT 
    pe.id AS person_id,
    pe.full_name,
    COUNT(DISTINCT pa.project_id) AS project_count,
    COUNT(DISTINCT pa.role_id) AS total_roles,
    STRING_AGG(DISTINCT r.name, ', ' ORDER BY r.name) AS all_roles
FROM people pe
JOIN project_assignments pa ON pe.id = pa.person_id
JOIN roles r ON pa.role_id = r.id
GROUP BY pe.id, pe.full_name;

-- View: Project team composition
CREATE VIEW v_project_team_composition AS
SELECT 
    p.id AS project_id,
    p.name AS project_name,
    COUNT(DISTINCT pe.id) AS team_size,
    COUNT(pa.id) AS total_role_assignments,
    STRING_AGG(DISTINCT rc.name, ', ' ORDER BY rc.name) AS categories_involved
FROM projects p
JOIN project_assignments pa ON p.id = pa.project_id
JOIN people pe ON pa.person_id = pe.id
JOIN roles r ON pa.role_id = r.id
JOIN role_categories rc ON r.category_id = rc.id
GROUP BY p.id, p.name;

-- ============================================
-- CONSTRAINTS & BUSINESS RULES
-- ============================================

-- Function to ensure person has at least one role in a project
CREATE OR REPLACE FUNCTION check_person_has_role()
RETURNS TRIGGER AS $$
BEGIN
    -- When deleting, ensure person still has at least one role in the project
    IF (TG_OP = 'DELETE') THEN
        IF NOT EXISTS (
            SELECT 1 FROM project_assignments 
            WHERE project_id = OLD.project_id 
            AND person_id = OLD.person_id
            AND id != OLD.id
        ) THEN
            RAISE EXCEPTION 'Cannot remove last role assignment. Person must have at least one role in the project.';
        END IF;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Trigger to enforce at least one role per person per project
CREATE TRIGGER enforce_minimum_role
BEFORE DELETE ON project_assignments
FOR EACH ROW
EXECUTE FUNCTION check_person_has_role();

-- ============================================
-- SAMPLE DATA (Optional - for testing)
-- ============================================

-- Insert sample projects
INSERT INTO projects (name, description) VALUES
('Digital Transformation Initiative', 'Company-wide digital transformation program using PRINCE2 methodology'),
('Customer Portal Development', 'Agile development of new customer-facing portal'),
('IT Service Management Upgrade', 'ITIL4-based service management platform upgrade'),
('Mobile App Redesign', 'Complete redesign of mobile application using Scrum');

-- Insert sample people with realistic avatars
INSERT INTO people (full_name, picture_url) VALUES
-- Leadership & Management
('Sarah Mitchell', 'https://i.pravatar.cc/150?img=1'),
('James Anderson', 'https://i.pravatar.cc/150?img=2'),
('Maria Garcia', 'https://i.pravatar.cc/150?img=3'),
('Robert Chen', 'https://i.pravatar.cc/150?img=4'),
('Jennifer Thompson', 'https://i.pravatar.cc/150?img=5'),
-- Technical & Development
('Michael O''Brien', 'https://i.pravatar.cc/150?img=6'),
('Lisa Rodriguez', 'https://i.pravatar.cc/150?img=7'),
('David Kim', 'https://i.pravatar.cc/150?img=8'),
('Emily Watson', 'https://i.pravatar.cc/150?img=9'),
('Ahmed Hassan', 'https://i.pravatar.cc/150?img=10'),
-- Specialists
('Sophie Laurent', 'https://i.pravatar.cc/150?img=11'),
('Thomas Mueller', 'https://i.pravatar.cc/150?img=12'),
('Priya Sharma', 'https://i.pravatar.cc/150?img=13'),
('Carlos Santos', 'https://i.pravatar.cc/150?img=14'),
('Anna Kowalski', 'https://i.pravatar.cc/150?img=15'),
-- Team Members
('Marcus Johnson', 'https://i.pravatar.cc/150?img=16'),
('Yuki Tanaka', 'https://i.pravatar.cc/150?img=17'),
('Isabella Rossi', 'https://i.pravatar.cc/150?img=18'),
('Oliver Smith', 'https://i.pravatar.cc/150?img=19'),
('Nina Petrov', 'https://i.pravatar.cc/150?img=20');

-- ============================================
-- COMPLEX ASSIGNMENT SCENARIOS
-- ============================================

-- PROJECT 1: Digital Transformation Initiative (Full PRINCE2 structure)
-- Sarah Mitchell: Corporate level + Executive on multiple projects
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Sarah Mitchell'),
 (SELECT id FROM roles WHERE name = 'Corporate/Program Management')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Sarah Mitchell'),
 (SELECT id FROM roles WHERE name = 'Executive'));

-- James Anderson: Multi-role power user (Senior User + Project Manager + Scrum Master)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'James Anderson'),
 (SELECT id FROM roles WHERE name = 'Senior User')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'James Anderson'),
 (SELECT id FROM roles WHERE name = 'Project Manager')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'James Anderson'),
 (SELECT id FROM roles WHERE name = 'Scrum Master'));

-- Maria Garcia: Senior Supplier + Service Owner
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Maria Garcia'),
 (SELECT id FROM roles WHERE name = 'Senior Supplier')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Maria Garcia'),
 (SELECT id FROM roles WHERE name = 'Service Owner'));

-- Robert Chen: Project Assurance + Quality Assurance Manager
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Robert Chen'),
 (SELECT id FROM roles WHERE name = 'Project Assurance')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Robert Chen'),
 (SELECT id FROM roles WHERE name = 'Quality Assurance Manager'));

-- Jennifer Thompson: Product Owner + Business Analyst
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Jennifer Thompson'),
 (SELECT id FROM roles WHERE name = 'Product Owner')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Jennifer Thompson'),
 (SELECT id FROM roles WHERE name = 'Business Analyst'));

-- Michael O'Brien: Tech Lead + Developer (wearing multiple hats)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Michael O''Brien'),
 (SELECT id FROM roles WHERE name = 'Tech Lead')),
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Michael O''Brien'),
 (SELECT id FROM roles WHERE name = 'Developer'));

-- Lisa Rodriguez: ONLY Developer (single role example)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'Lisa Rodriguez'),
 (SELECT id FROM roles WHERE name = 'Developer'));

-- David Kim: ONLY DevOps Engineer (single role example)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Digital Transformation Initiative'),
 (SELECT id FROM people WHERE full_name = 'David Kim'),
 (SELECT id FROM roles WHERE name = 'DevOps Engineer'));

-- PROJECT 2: Customer Portal Development (Agile/Scrum focused)
-- Sarah Mitchell: Also Executive here (multi-project)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Sarah Mitchell'),
 (SELECT id FROM roles WHERE name = 'Executive'));

-- Emily Watson: Product Owner + Product Manager
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Emily Watson'),
 (SELECT id FROM roles WHERE name = 'Product Owner')),
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Emily Watson'),
 (SELECT id FROM roles WHERE name = 'Product Manager'));

-- Ahmed Hassan: Scrum Master + Agile Coach
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Ahmed Hassan'),
 (SELECT id FROM roles WHERE name = 'Scrum Master')),
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Ahmed Hassan'),
 (SELECT id FROM roles WHERE name = 'Agile Coach'));

-- Sophie Laurent: ONLY UX/UI Designer (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Sophie Laurent'),
 (SELECT id FROM roles WHERE name = 'UX/UI Designer'));

-- Michael O'Brien: Also on this project as Developer (multi-project person)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Michael O''Brien'),
 (SELECT id FROM roles WHERE name = 'Developer'));

-- Thomas Mueller: ONLY Developer (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Thomas Mueller'),
 (SELECT id FROM roles WHERE name = 'Developer'));

-- Priya Sharma: Developer + Test Manager
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Priya Sharma'),
 (SELECT id FROM roles WHERE name = 'Developer')),
((SELECT id FROM projects WHERE name = 'Customer Portal Development'),
 (SELECT id FROM people WHERE full_name = 'Priya Sharma'),
 (SELECT id FROM roles WHERE name = 'Test Manager'));

-- PROJECT 3: IT Service Management Upgrade (ITIL4 focused)
-- Maria Garcia: Multi-project - Service Owner here too
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Maria Garcia'),
 (SELECT id FROM roles WHERE name = 'Service Owner'));

-- Carlos Santos: Service Manager + Change Manager + Release Manager (multi-role ITIL specialist)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Carlos Santos'),
 (SELECT id FROM roles WHERE name = 'Service Manager')),
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Carlos Santos'),
 (SELECT id FROM roles WHERE name = 'Change Manager')),
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Carlos Santos'),
 (SELECT id FROM roles WHERE name = 'Release Manager'));

-- Anna Kowalski: ONLY Configuration Manager (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Anna Kowalski'),
 (SELECT id FROM roles WHERE name = 'Configuration Manager'));

-- David Kim: Multi-project DevOps Engineer
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'David Kim'),
 (SELECT id FROM roles WHERE name = 'DevOps Engineer'));

-- Marcus Johnson: ONLY Project Support (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'IT Service Management Upgrade'),
 (SELECT id FROM people WHERE full_name = 'Marcus Johnson'),
 (SELECT id FROM roles WHERE name = 'Project Support'));

-- PROJECT 4: Mobile App Redesign (Smaller team)
-- Yuki Tanaka: Scrum Master + Team Lead
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Yuki Tanaka'),
 (SELECT id FROM roles WHERE name = 'Scrum Master')),
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Yuki Tanaka'),
 (SELECT id FROM roles WHERE name = 'Team Lead'));

-- Isabella Rossi: ONLY Product Owner (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Isabella Rossi'),
 (SELECT id FROM roles WHERE name = 'Product Owner'));

-- Oliver Smith: ONLY Developer (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Oliver Smith'),
 (SELECT id FROM roles WHERE name = 'Developer'));

-- Nina Petrov: ONLY UX/UI Designer (single role)
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Nina Petrov'),
 (SELECT id FROM roles WHERE name = 'UX/UI Designer'));

-- Sophie Laurent: Multi-project designer
INSERT INTO project_assignments (project_id, person_id, role_id) VALUES
((SELECT id FROM projects WHERE name = 'Mobile App Redesign'),
 (SELECT id FROM people WHERE full_name = 'Sophie Laurent'),
 (SELECT id FROM roles WHERE name = 'UX/UI Designer'));

-- ============================================
-- HELPFUL QUERIES (Commented examples)
-- ============================================

-- Get all people in a specific project with their roles:
-- SELECT * FROM v_project_assignments_detailed WHERE project_name = 'Digital Transformation Initiative';

-- Get all projects a person is involved in:
-- SELECT * FROM v_project_assignments_detailed WHERE person_name = 'Alice Johnson';

-- Get people with multiple roles in the same project:
-- SELECT project_name, person_name, COUNT(*) as role_count, STRING_AGG(role_name, ', ') as roles
-- FROM v_project_assignments_detailed
-- GROUP BY project_id, project_name, person_id, person_name
-- HAVING COUNT(*) > 1;

-- Get role hierarchy for a project:
-- SELECT DISTINCT category_name, category_hierarchy, role_name, role_hierarchy
-- FROM v_project_assignments_detailed
-- WHERE project_name = 'Digital Transformation Initiative'
-- ORDER BY category_hierarchy DESC, role_hierarchy DESC;