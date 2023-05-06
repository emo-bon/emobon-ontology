Here a few simple routines for GraphDB are provided to help users:
1. clear a named graph,
2. update the SHACL shapes in the default SHACL graph, and
3. retrieve the SHACL shapes from the default SHACL shape graph.


# Clear a named graph
    java -classpath <path to shacl-validator-1.0-SNAPSHOT.jar> org.emobon.shacl.ClearGraph <graphDB Url> <Repository> <named graph>

This states that the `<named graph>` in the `<Repository>` of the GraphDB instance at  `<graphDB Url>` will be cleared. 
An example command may look as follows:
    
    java -classpath target/shacl-validator-1.0-SNAPSHOT.jar org.emobon.shacl.ClearGraph http://localhost:7200/ EMOBON https://www.embrc.eu/emobon/data


# Update SHACL shape
    java -classpath <path to shacl-validator-1.0-SNAPSHOT.jar> org.emobon.shacl.UpdateShacl <graphDB Url> <Repository> <shape file>

This states that the SHACL shapes of the `<Repository>` of the GraphDB instance at  `<graphDB Url>` will be replaced with
the SHACL shape information in the given `<shape file>`. An example command may look as follows:

    java -classpath target/shacl-validator-1.0-SNAPSHOT.jar org.emobon.shacl.UpdateShacl http://localhost:7200/ EMOBON ../shacl/emobonShapes.ttl


# Retrieve SHACL shapes
Currently there is no way to retrieve SHACL shapes using the workbench of GraphDB or any commandline tools that they provide. 
Howerever, the following code will retrieve the desired information.

    java -classpath <path to shacl-validator-1.0-SNAPSHOT.jar> org.emobon.shacl.RetrieveShaclShapes <graphDB Url> <Repository>

where `<graphDB Url>` is the GraphDB instance and `<Repository>` is the repository from where SHACL shapes will be retrieved
from the default SHACL shapes graph: http://rdf4j.org/schema/rdf4j#SHACLShapeGraph. An example command may look as follows:

    java -classpath target/shacl-validator-1.0-SNAPSHOT.jar org.emobon.shacl.RetrieveShaclShapes http://localhost:7200/ EMOBON