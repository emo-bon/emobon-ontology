This repository contains everything related to the EMO BON ontology

**Update: semantic artefacts are now maintained at https://github.com/emo-bon/ns**

# Directory structure
* `+/data/`: Directory where EMO BON data translated to RDF (turtle syntax) is stored
* `+/exampleData`: This where example data that are used by EMO BON is store. This is for information purpose and is not 
actually used.
* `+/ontology`: This where the EMO BON ontology is created. The ontology is created using Protege and stored as RDF/XML 
syntax. For ontologies RDF/XML is preferred to Turtle syntax since some OWL axioms cannot be stored in Turtle syntax.
* `+/ontology/dependencies`: This is where dependent ontologies on which the EMO BON ontology is based are stored. This is 
mostly for information purposes. That is the EMO BON ontology does not import these ontologies, but just use relevant
classes and properties directly by referring to the appropriate IRI. The `/ontology/dependencies/envo` directory is used
to create an extract of the ENVO ontology.
* `+/ontology/extracts`: Currently the only extract is from the ENVO ontology. This is used to validate biome data. 
* `+/queries`: Here example SPARQL queries are provided to illustrate how data can be queries.
* `+/shacl`: The SHACL shapes for validating the data is provided here.
* `+/shacl-validator`: Unfortunately the GraphDB workbench does not provide an easy why to update SHACL shapes. This 
directory contains minimal Java code to enable updates of the SHACL shapes graph in GraphDB.
* `+/uml`: A conceptual model of the EMO BON data model is provided as a UML class diagram.

# Steps to validate data
The current steps assume the use of Ontotext Graph DB. Steps on how to update GraphDB with SHACL shapes etc are given 
[here](./shacl-validator/README.md). 
1. Load ontologies into their own named graph, i.e. https://www.embrc.eu/emobon/ontology using the GraphDB workbench
   1. ./ontology/extracts/envo-module.owl
   2. ./ontology/EmoBonOntology.rdf
2. Load SHACL shapes. See [update SHACL shape](./shacl-validator/README.md#update-shacl-shape)
3. Load data using GraphDB workbench.

# Steps to update ENVO ontology extract
Pre-requisite: Ensure that [ROBOT](https://robot.obolibrary.org) is installed. 
1. Change directory to where copy of ENVO is kept: `cd ./ontology/dependencies/envo`
2. Dowload latest ENVO: `wget http://purl.obolibrary.org/obo/envo.owl`
3. Change directory to where ENVO extract is kept: `cd ./ontology/extracts`
4. Run extraction script: `generateExtracts.sh`
5. You should now have a new `envo-module.owl` in `./ontology/extracts/` that can be imported into your RDF triple store.

