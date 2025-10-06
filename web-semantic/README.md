# Web Semantic

> [!TIP]
> This is a web semantic dōjō
>
> - CHECK OUT A SETUP for a  ["DEMO" HERE](EXPO.md)

## What is the Web Semantic ?

Typically the sentence we find as an answer to this question is "Semantic Web is an extension of the existing World Wide Web". Which is basically true because instead of having resources dispersed and looked through based on scoring algorithms that can link apples to computers, the Semantic Web goes beyond that by binding meaningful and human-readable context (backed by a structured framework that defines the concepts, entities, and relationships within a specific domain of knowledge, an ontology) behind each unique resource ensuring that when we look through the graph we have a more specific and concise result

## Why Web Semantic ?

> Publications Office of the European Union, OP is “an inter-institutional body, and functions under the auspices of the European Commission. It is governed by a Management Committee, on which each institution is represented by its Secretary-General. In order to bring information to the citizen, the Publications Office cooperates with the EU institutions, agencies and bodies to further enhance the transparency of the legislative process and of European policies and to facilitate access to European legislation and information published in the L (Legislation), C (Information and Notices) and S (Public procurement) series of the Official Journal and on the related EUR-Lex and TED websites. The citizen has access to European Publications through the EU Bookshop and contact information for EU Officials can be found via EU “Whoiswho”, the official directory of the European Union.”

This is an interesting subject and a few years back in my career I had the chance of working on multiple projects related to Semantic Web technologies at the European Union Publication Office. These projects were part of the large application chain responsible for receiving, validating, aggregating and publishing of content.

<ol>
<li>
<details>
<summary>SYBORG</summary>

> SYBORG (SYnchronized Buffered ORGanizer) is a standalone Java application developed as an integration chain designed to receive and process data packages created by CERES (Common European Registry for Enforcement Sources). This application acted as a buffered organizer that synchronizes incoming data streams to ensure orderly processing and integration within the broader system of cross-border enforcement information exchange. It supported interoperability and seamless data integration necessary for EU-wide legal and administrative enforcement cooperation.
</details>
</li>
<li>
<details>
<summary>new.CERES</summary>

> The new.CERES (Common Electronic REception System) was a project designed as an electronic reception system to digitize and automate the reception and handling of official publications and documents within the EU institutions and bodies. The system aimed to streamline the process of receiving, managing, and distributing official documents electronically, replacing traditional paper-based workflows. It supported secure check-in, digital document handling, visitor or document tracking, and provides a standardized platform to improve operational efficiency and compliance with EU regulations on official publications. As part of an integrated framework for managing electronic reception, standardization, and archival of official EU documents and publications new.CERES served as the front-end electronic reception gateway, METACONV ensures metadata standardization and conversion, SYBORG manages the data flow and synchronization, and CELLAR stores and preserves the publications and records securely.
</details>
</li>
<li>
<details>
<summary>CELLAR</summary>

> CELLAR is the common digital repository of the Publications Office of the European Union (OPOCE) designed for the storage, management, and dissemination of official EU publications and their metadata. It serves as a centralized archive, storing multilingual publications and metadata, and provides access for both humans and machines using semantic web technologies. CELLAR is the common repository for all public websites of the Publications Office having as objective to provide the content to the different portals of the Publications Office including EUR-Lex, TED, CORDIS and EU bookshop. CELLAR can be seen as a FRBR (Functional Requirements for Bibliographic Records) system, based on RDF (Resource Description Framework) a metadata data model. Basically, a repository of documents and associated meta-data. It's part of a whole integration system that manages the distribution of legal document publications throughout official European entities and services.
</details>
</li>
<li>
<details>
<summary>OP Portal Search System</summary>

> The OP Portal Search System is an online search platform developed by the Publications Office of the European Union (OPOCE). It provides users with access to a vast range of EU general publications, legal documents, data, and metadata stored in the EU’s digital repository, CELLAR. The Search Layer designed for the Publications Office (OP) to be used as the single interface to access all content (data and metadata) stored and managed by the Publications Office. Basically, OPPSS provides an interface for the indexation and multilingual search of the content published by OP.
</details>
</li>
</ol>

## What type of data gets published ?

| Category         | Description |
|------------------|-------------|
| **EU Law** | The Official Journal of the European Union (which includes legislation, regulations, directives, decisions, and international agreements), as well as preparatory and public legal documents. (Accessible via the EUR-Lex website). |
| **Publications** | Reports, studies, books, brochures, and other informational material from all EU institutions and bodies. |
| **Official Data** | European data, open data, reference data, and metadata (accessible via the data.europa.eu portal and EU Vocabularies). |
| **Procurement** | Public procurement notices (EU tenders). |
| **Research Results** | Information about the results of EU-funded projects (e.g., via the CORDIS service). |
| **Administrative** | Directories, such as the EU Whoiswho (a directory of the European institutions). |
| **Archival** | Archived websites of EU institutions and agencies (EU Web Archive). |

## How is this packaged ?

IMMC and METS are two important technical standards used by the Publications Office's central repository, known as Cellar, to manage the intake and storage of digital publications and their associated metadata.</br>
The IMMC protocol is a standardised way for EU institutions to package and exchange official documents and metadata with the Publications Office. It was developed to streamline the interinstitutional decision-making process. The IMMC package contains the digital documents along with structured metadata in XML files. This metadata is crucial for categorisation, processing instructions, and easy retrieval later on.</br>
METS is a standard for encoding descriptive, administrative, and structural metadata regarding objects within a digital library. At the Publications Office, a METS package is a zip file containing the actual digital publications, descriptive and technical metadata, and a description of the document's structure. After the IMMC package is received, it is validated, and a METS package is generated for storage in the central repository (Cellar). METS provides a standardized, reliable format for packaging the document and all its associated data, which is essential for long-term preservation (archiving in the EUDOR system) and for dissemination to end-user portals like EUR-Lex.</br>
By converting content into this structured format, the Publications Office ensures the information is Findable, Accessible, Interoperable, and Reusable (FAIR data), supporting transparency and modern data-driven technologies like Artificial Intelligence.

## How is this structured ?

It followed a FRBR model and its  WEMI hierarchy where a document (work) can have multiple translations (expressions), and each translation can have multiple formats (manifestation), and each format is linked to a binary file (item). At each level there was contextual metadata linking them with each other and other resources.

> FRBR (Functional Requirements for Bibliographic Records) is a conceptual entity-relationship model developed by the International Federation of Library Associations and Institutions (IFLA) in 1998. It is a foundational model for modern library cataloging, designed to restructure bibliographic databases to better reflect the conceptual structure of information resources and the needs of the user.
Its main purpose is to help users find, identify, select, and obtain the information they need by clearly defining and distinguishing between the abstract content of a work and its physical manifestations.

### The WEMI Hierarchy

| Entity | Definition | Example |
|---------|-------------|----------|
| **Work** | The abstract, distinct intellectual or artistic creation. The 'idea' or content itself, independent of language, format, or realization. | The story of *Romeo and Juliet*. |
| **Expression** | The intellectual or artistic realization of the work in a specific form, such as a particular language, script, or musical notation. | The original Italian text of *Romeo and Juliet*, or a specific English translation of it. |
| **Manifestation** | The physical embodiment of an expression. This includes all the physical objects that share the same publication characteristics (e.g., publisher, physical form, date). | The 1992 paperback edition of the English translation, or the 2005 e-book edition. |
| **Item** | The single, concrete exemplar of a manifestation. The actual object the user possesses or accesses, with its own unique history (e.g., copy number, physical condition). | The copy of the 1992 paperback edition with a particular library's stamp and call number. |

### Data and metadata

Data refers to the raw facts and resources. It serves as the foundation for analysis and decision-making. </br>
Metadata, on the other hand, is often described as “data about data”. </br>
It provides context and meaning to data by describing its content, structure, origin, and purpose. </br></br>
At the time I was involved data was stored in Fedora Commons (or Flexible Extensible Digital Object Repository Architecture).</br>
A digital asset management (DAM) content repository architecture upon which institutional repositories, digital archives, and digital library systems might be built.</br></br>
Metadata on the other hand was stored in the form of triples. For its storage, at the time I was involved there was a transition from Oracle RDF store to Virtuoso triple store.</br>

A triple, also known as a Semantic Triple or RDF Triple (Resource Description Framework), is the atomic unit of data in the Semantic Web model. It's a simple, three-part statement that expresses a fact about the world, much like a simple English sentence. The power of triples comes from connecting them. The Object of one triple can be the Subject of another, creating a vast, interconnected network of facts, which is a Knowledge Graph.

| Triple | Subject | Predicate | Object |
|---------|----------|------------|---------|
| **Triple 1 (Fact)** | Big Ben | is located in | London |
| **Triple 2 (New Fact)** | London | is the capital of | UK |
| **Triple 3 (New Fact)** | UK | has currency | Pound |

By linking these, a system can automatically infer complex facts, like "Big Ben is in the UK," without that fact being explicitly stated. In this graph structure, the Subjects and Objects are the nodes, and the Predicates are the edges (relationships).

| Component | Description | Role in Sentence | Example: "Big Ben is located in London" |
|------------|--------------|------------------|----------------------------------------|
| **Subject** | The entity being described. | Noun | Big Ben |
| **Predicate** | The relationship, attribute, or property connecting the subject and object. | Verb or Verb Phrase | is located in |
| **Object** | The value or related entity. | Noun or Literal Value | London |

### How do we represent this ?

RDF, SKOS, and OWL are foundational World Wide Web Consortium (W3C) standards that define how data and its meaning are structured and represented in a way that is machine-readable, making them essential when working with triple stores.</br>

RDF is a standard model for data interchange that represents information as a graph composed of triples. Each part of a triple is typically identified by a Uniform Resource Identifier (URI), allowing concepts to be unambiguously linked both within and across different datasets (Linked Data).

SKOS is an RDF vocabulary (a set of predefined RDF classes and properties) designed to represent the structure and content of Knowledge Organization Systems (KOS), such as thesauri, classification schemes, subject heading lists, and taxonomies.

OWL is an RDF vocabulary (also a set of classes and properties) used to author ontologies. An ontology is a formal and explicit specification of a shared conceptualization. OWL is more expressive than RDF Schema (RDFS) and provides a logic-based language to define complex classes, properties, and constraints, enabling logical inference.

### How can we browse through this ?

A triple store (or RDF store) is a purpose-built database for storing and retrieving Resource Description Framework (RDF) data. The other formats, SKOS and OWL, are RDF vocabularies built on top of RDF to provide more specialized ways of modeling knowledge.

SPARQL (pronounced "sparkle") is the standard query language for the Semantic Web. It stands for SPARQL Protocol and RDF Query Language.

In simple terms, if the Semantic Web uses RDF (Resource Description Framework) to store data as a huge, interconnected graph (a "Knowledge Graph"), SPARQL is the tool used to ask questions of that graph. It is the equivalent of SQL for relational databases, but designed specifically for the graph-based data model of RDF.

> Find the name of any entity that Alice knows.

```sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?name
WHERE {
  <http://example.org/person/alice> foaf:knows ?friend .
  ?friend foaf:name ?name .
}
```

| Query Type | Purpose | Result |
|-------------|----------|---------|
| **SELECT** | Retrieves values for variables. (Most common) | A tabular result set (rows and columns). |
| **CONSTRUCT** | Retrieves data and builds a new RDF graph (set of triples) from it. | A new RDF graph. |
| **ASK** | Checks if a specific pattern exists in the data. | A simple Boolean answer (true or false). |
| **DESCRIBE** | Returns an RDF graph that provides a description of a resource. | An RDF graph describing the entity. |

Checkout the **publicly accessible** SPARQL endpoint for [OPOCE](https://op.europa.eu/en/) : <https://publications.europa.eu/webapi/rdf/sparql>

## Personal milestones

- Lead the migration from Jboss to Tomcat along with Atomikos and handled the JDK migration as well
- Participated in the call for tenders answering the questions about a potential migration from Oracle RDF store to an alternative framework. We won the contract and migrated to Virtuoso Triple store
