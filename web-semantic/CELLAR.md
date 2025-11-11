# using the **publicly accessible** SPARQL endpoint

> [!Note]
>
> [OPOCE](https://op.europa.eu/en/) exposes the endpoint <https://publications.europa.eu/webapi/rdf/sparql>
>
> Useful references:
>
> - <https://op.europa.eu/en/advanced-sparql-query-editor>
> - <https://op.europa.eu/en/web/cellar/cellar-data>
> - <https://op.europa.eu/en/web/cellar/cellar-data/metadata/knowledge-graph>
>
> Example of services using CELLAR:
>
> - [EUR-LEX](https://eur-lex.europa.eu/content/help/data-reuse/reuse-contents-eurlex-details.html)
> - [OP Portal Search](https://op.europa.eu/en/web/about-us/legal-notices/publications-office-of-the-european-union)
>

Play with it

``` sql
--  Count the number of WORKs per monthly created during 2025
DEFINE input:inference "cdm_rule_set"
PREFIX cdm: <http://publications.europa.eu/ontology/cdm#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX owl: <http://www.w3.org/2002/07/owl#> 
PREFIX at: <http://publications.europa.eu/ontology/authority/>
SELECT ?month as ?MONTH COUNT(distinct ?work) as ?TOTAL
WHERE { 
?work cdm:date_document ?date_doc .   
filter (   year(?date_doc) = 2025   )
}
GROUP BY (month(?date_doc) AS ?month)   
ORDER BY ?month

-- RESULT (at the time of execution 06-10-2025)
| MONTH | TOTAL |
|--------|--------|
| 1 | 41041 |
| 2 | 35775 |
| 3 | 35427 |
| 4 | 29966 |
| 5 | 66933 |
| 6 | 75647 |
| 7 | 85146 |
| 8 | 64988 |
| 9 | 76357 |
| 10 | 11503 |

```

## Check out **data inference**

```sql
DEFINE input:inference "cdm_rule_set"
PREFIX cdm: <http://publications.europa.eu/ontology/cdm#> 
SELECT * WHERE { ?s cdm:date_document ?o. }
limit 100 --IMPORTANT DUE TO AMOUNT OF DATA
```
