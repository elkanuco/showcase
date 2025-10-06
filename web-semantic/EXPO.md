# simple POC setup

> [!Note]
>
> In this exercise I :
>
> - deploy Virtuoso Triple store using Docker compose
> - load RDF files containing triples generated with generic AI
> - expose some queries and data inference
>
> An alternative is to use the **publicly accessible** SPARQL endpoint [HERE](CELLAR.md)

</br>

> [!Important]
>
> Inspect contents on the docker folder </br>
> Run ``docker-compose up`` or ``docker-compose down`` to setup or tear down [Virtuoso Triple store](https://hub.docker.com/r/openlink/virtuoso-opensource-7) </br>
> Inform yourself on [Virtuoso](https://vos.openlinksw.com/owiki/wiki/VOS)</br>
> Find how to load files</br>
> Access [Virtuoso's conductor page](http://localhost:8890/conductor/rdf_import.vspx) to load the RDF files in the folder /import.</br>
> To login as admin and load the files use dba/dba </br>
> Access the [sparql endpoint](http://localhost:8890/sparql/) to perform queries </br>
> Inform yourself over an example of an ontology and SPARQL:
>
> - <http://rdfs.org/resume-rdf/>
> - <https://www.w3.org/TR/sparql11-query/>
> - <https://jena.apache.org/tutorials/sparql.html>
> - <https://sparql.dev/>

## Requirements

Using the [Interactive SQL prompt](http://localhost:8890/conductor/rdf_import.vspx) accessible through the Conductor's main page I had to run some commands:

``` sql
ld_dir ('/import', 'base.rdfs', 'http://rdfs.org/resume-rdf/base.rdfs');
ld_dir ('/import', 'cv.rdfs', 'http://rdfs.org/resume-rdf/cv.rdfs');
ld_dir('/import', 'markzuckerberg.ttl', 'http://example.org/zuckerberg-resume');
ld_dir('/import', 'elonmusk.ttl', 'http://example.org/musk-resume');
rdf_loader_run (); -- after each
-- define inference rules set
rdfs_rule_set ('cv_rules', 'http://rdfs.org/resume-rdf/cv.rdfs', 'http://rdfs.org/resume-rdf/base.rdfs');
-- check
SELECT * FROM DB.DBA.SYS_RDF_SCHEMA WHERE RS_NAME = 'cv_rules';
```

Ran a few queries to check if the data was there

- <http://localhost:8890/sparql/>

``` sql
--check triple count
SELECT COUNT(*) AS ?triples WHERE { GRAPH <http://rdfs.org/resume-rdf/base.rdfs> { ?s ?p ?o } }
SELECT COUNT(*) AS ?triples WHERE { GRAPH <http://rdfs.org/resume-rdf/cv.rdfs> { ?s ?p ?o } }
SELECT COUNT(*) AS ?triples WHERE { GRAPH <http://example.org/zuckerberg-resume> { ?s ?p ?o } }
SELECT COUNT(*) AS ?triples WHERE { GRAPH <http://example.org/musk-resume> { ?s ?p ?o } }
```

## Play with it

``` sql

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?name ?jobTitle ?company ?startDate ?endDate ?isCurrent
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      BIND(?companyName AS ?company)
      OPTIONAL { ?wh cv:startDate ?startDate }
      OPTIONAL { ?wh cv:endDate ?endDate }
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      BIND(?companyName AS ?company)
      OPTIONAL { ?wh cv:startDate ?startDate }
      OPTIONAL { ?wh cv:endDate ?endDate }
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  }
}
ORDER BY ?name ?startDate



PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
SELECT ?person ?name ?birthPlace
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?person cv:birthPlace ?birthPlace .
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?person cv:birthPlace ?birthPlace .
    }
  }
}
ORDER BY ?name




PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?jobTitle ?company ?jobDescription ?isCurrent
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      ?wh cv:jobDescription ?jobDescription .
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      ?wh cv:jobDescription ?jobDescription .
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  }
FILTER (?isCurrent = "true"^^xsd:boolean)  
}
ORDER BY ?person ?jobTitle





PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?name ?skillName ?skillLevel ?yearsExperience ?lastUsed ?readingLevel ?writingLevel
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?yearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?lastUsed }
      OPTIONAL { ?skill cv:lngSkillLevelReading ?readingLevel }
      OPTIONAL { ?skill cv:lngSkillLevelWritten ?writingLevel }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?yearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?lastUsed }
      OPTIONAL { ?skill cv:lngSkillLevelReading ?readingLevel }
      OPTIONAL { ?skill cv:lngSkillLevelWritten ?writingLevel }
    }
  }
}
ORDER BY ?name ?skillName


PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?name ?eduOrg ?degreeType ?major ?minor ?startDate ?gradDate ?description
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasEducation ?edu .
      ?edu cv:studiedIn ?eduOrg .
      ?eduOrg cv:Name ?eduOrgName .
      BIND(?eduOrgName AS ?eduOrg)
      OPTIONAL { ?edu cv:degreeType ?degreeType }
      OPTIONAL { ?edu cv:eduMajor ?major }
      OPTIONAL { ?edu cv:eduMinor ?minor }
      OPTIONAL { ?edu cv:eduStartDate ?startDate }
      OPTIONAL { ?edu cv:eduGradDate ?gradDate }
      OPTIONAL { ?edu cv:eduDescription ?description }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasEducation ?edu .
      ?edu cv:studiedIn ?eduOrg .
      ?eduOrg cv:Name ?eduOrgName .
      BIND(?eduOrgName AS ?eduOrg)
      OPTIONAL { ?edu cv:degreeType ?degreeType }
      OPTIONAL { ?edu cv:eduMajor ?major }
      OPTIONAL { ?edu cv:eduMinor ?minor }
      OPTIONAL { ?edu cv:eduStartDate ?startDate }
      OPTIONAL { ?edu cv:eduGradDate ?gradDate }
      OPTIONAL { ?edu cv:eduDescription ?description }
    }
  }
}
ORDER BY ?name ?startDate


PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?name ?birthPlace ?nationality ?maritalStatus ?noOfChildren ?otherType ?otherDescription
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      OPTIONAL { ?person cv:birthPlace ?birthPlace }
      OPTIONAL { ?person cv:hasNationality ?nationality }
      OPTIONAL { ?person cv:maritalStatus ?maritalStatus }
      OPTIONAL { ?person cv:noOfChildren ?noOfChildren }
      OPTIONAL { ?cv cv:hasOtherInfo ?other .
                 ?other cv:otherInfoType ?otherType ;
                         cv:otherInfoDescription ?otherDescription }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      OPTIONAL { ?person cv:birthPlace ?birthPlace }
      OPTIONAL { ?person cv:hasNationality ?nationality }
      OPTIONAL { ?person cv:maritalStatus ?maritalStatus }
      OPTIONAL { ?person cv:noOfChildren ?noOfChildren }
      OPTIONAL { ?cv cv:hasOtherInfo ?other .
                 ?other cv:otherInfoType ?otherType ;
                         cv:otherInfoDescription ?otherDescription }
    }
  }
}
ORDER BY ?name



PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>


SELECT ?person ?eduOrg ?degreeType ?eduMajor ?eduGradDate ?eduDescription
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasEducation ?edu .
      ?edu cv:studiedIn ?eduOrg .
      ?eduOrg cv:Name ?eduOrgName .
      ?edu cv:degreeType ?degreeType .
      ?edu cv:eduMajor ?eduMajor .
      OPTIONAL { ?edu cv:eduGradDate ?eduGradDate }
      OPTIONAL { ?edu cv:eduDescription ?eduDescription }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasEducation ?edu .
      ?edu cv:studiedIn ?eduOrg .
      ?eduOrg cv:Name ?eduOrgName .
      ?edu cv:degreeType ?degreeType .
      ?edu cv:eduMajor ?eduMajor .
      OPTIONAL { ?edu cv:eduGradDate ?eduGradDate }
      OPTIONAL { ?edu cv:eduDescription ?eduDescription }
    }
  }
}
ORDER BY ?person ?eduOrg


PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?name ?targetJobType ?targetCareerLevel ?targetCompanyIndustry ?targetSalary ?willTravel
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasTarget ?target .
      OPTIONAL { ?target cv:targetJobType ?targetJobType }
      OPTIONAL { ?target cv:targetCareerLevel ?targetCareerLevel }
      OPTIONAL { ?target cv:targetCompanyIndustry ?targetCompanyIndustry }
      OPTIONAL { ?target cv:targetSalary ?targetSalary }
      OPTIONAL { ?target cv:conditionWillTravel ?willTravel }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasTarget ?target .
      OPTIONAL { ?target cv:targetJobType ?targetJobType }
      OPTIONAL { ?target cv:targetCareerLevel ?targetCareerLevel }
      OPTIONAL { ?target cv:targetCompanyIndustry ?targetCompanyIndustry }
      OPTIONAL { ?target cv:targetSalary ?targetSalary }
      OPTIONAL { ?target cv:conditionWillTravel ?willTravel }
    }
  }
}
ORDER BY ?name


PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?skillName ?skillLevel ?skillYearsExperience ?skillLastUsed
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?skillYearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?skillLastUsed }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?skillYearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?skillLastUsed }
    }
  }
}
ORDER BY ?person ?skillName


PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>


SELECT ?person ?name ?birthPlace
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?person cv:birthPlace ?birthPlace .
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?person cv:birthPlace ?birthPlace .
    }
  }
}
ORDER BY ?name

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>


SELECT ?person ?jobTitle ?company ?jobDescription ?isCurrent
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      ?wh cv:jobDescription ?jobDescription .
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasWorkHistory ?wh .
      ?wh cv:jobTitle ?jobTitle .
      ?wh cv:employedIn ?company .
      ?company cv:Name ?companyName .
      ?wh cv:jobDescription ?jobDescription .
      OPTIONAL { ?wh cv:isCurrent ?isCurrent }
    }
  }

FILTER (?isCurrent = "true"^^xsd:boolean)  # Only current jobs
}
ORDER BY ?person ?jobTitle

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?otherInfoType ?otherInfoDescription
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasOtherInfo ?otherInfo .
      ?otherInfo cv:otherInfoType ?otherInfoType .
      ?otherInfo cv:otherInfoDescription ?otherInfoDescription .
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasOtherInfo ?otherInfo .
      ?otherInfo cv:otherInfoType ?otherInfoType .
      ?otherInfo cv:otherInfoDescription ?otherInfoDescription .
    }
  }
}
ORDER BY ?person

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?person ?skillName ?skillLevel ?skillYearsExperience ?skillLastUsed
WHERE {
  {
    GRAPH <http://example.org/zuckerberg-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?skillYearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?skillLastUsed }
    }
  } UNION {
    GRAPH <http://example.org/musk-resume> {
      ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .  # To bind ?person for output
      ?cv cv:hasSkill ?skill .
      ?skill cv:skillName ?skillName .
      OPTIONAL { ?skill cv:skillLevel ?skillLevel }
      OPTIONAL { ?skill cv:skillYearsExperience ?skillYearsExperience }
      OPTIONAL { ?skill cv:skillLastUsed ?skillLastUsed }
    }
  }
}
ORDER BY ?person ?skillName

SELECT ?person ?name ?jobTitle
FROM NAMED <http://example.org/zuckerberg-resume>
FROM NAMED <http://example.org/musk-resume>
FROM NAMED <http://example.org/daniel-mendes-resume>
WHERE {
  GRAPH ?g {
    ?cv cv:aboutPerson ?person .
    ?person foaf:name ?name .
    ?cv cv:hasWorkHistory ?wh .
    ?wh cv:jobTitle ?jobTitle .
  }
}
ORDER BY ?name

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT DISTINCT  ?person ?name ?targetJobType ?targetCareerLevel ?targetCompanyIndustry ?targetSalary ?willTravel
WHERE {
  ?cv cv:aboutPerson ?person .
      ?person foaf:name ?name .
      ?cv cv:hasTarget ?target .
      OPTIONAL { ?target cv:targetJobType ?targetJobType }
      OPTIONAL { ?target cv:targetCareerLevel ?targetCareerLevel }
      OPTIONAL { ?target cv:targetCompanyIndustry ?targetCompanyIndustry }
      OPTIONAL { ?target cv:targetSalary ?targetSalary }
      OPTIONAL { ?target cv:conditionWillTravel ?willTravel }
}
ORDER BY ?name


SELECT DISTINCT ?g
WHERE {
  GRAPH ?g { ?s ?p ?o }
}
```

## Show case *data inference*

With inference extra triples are extracted

``` sql
--> WITHOUT INFERENCE
SELECT ?type
FROM <http://example.org/musk-resume>
WHERE {
  <http://example.org/cv/elon-musk> rdf:type ?type .
}
--> RESULT
type
http://rdfs.org/resume-rdf/cv.rdfs#CV
-- WITH INFERENCE
DEFINE input:inference 'cv_rules'
SELECT ?type
FROM <http://example.org/musk-resume>
FROM <http://rdfs.org/resume-rdf/cv.rdfs>
WHERE {
  <http://example.org/cv/elon-musk> rdf:type ?type .
}
--> RESULT
type
http://rdfs.org/resume-rdf/cv.rdfs#CV
http://xmlns.com/wordnet/1.6/Curriculum_Vitae -- EXTRA INFERED DATA
```

Find all cv:CV_Entry instances (e.g., work history, education) for both Musk and Zuckerberg.

``` sql
DEFINE input:inference 'cv_rules' -- WITHOUT THIS the query returns nothing
PREFIX cv: <http://rdfs.org/resume-rdf/cv.rdfs#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
SELECT ?person ?entry ?entryType
FROM <http://example.org/musk-resume>
FROM <http://example.org/zuckerberg-resume>
FROM <http://rdfs.org/resume-rdf/cv.rdfs>
WHERE {
  ?cv cv:aboutPerson ?person .
  { ?cv cv:hasWorkHistory ?entry } UNION { ?cv cv:hasEducation ?entry } .
  ?entry rdf:type ?entryType .
  FILTER (?entryType = <http://rdfs.org/resume-rdf/cv.rdfs#CV_Entry>)
}
```

## Count infered triples

```sql
DEFINE input:inference 'cv_rules'
SELECT (COUNT(*) AS ?count)
FROM <http://example.org/musk-resume>
FROM <http://rdfs.org/resume-rdf/cv.rdfs>
WHERE { ?s ?p ?o }
```

## How to clear data

``` sql
SPARQL CLEAR GRAPH <http://example.org/zuckerberg-resume>;
SPARQL CLEAR GRAPH <http://example.org/musk-resume>;
```
