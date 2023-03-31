package org.emobon.shacl;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;

public class ClearGraph {
    public static void main(String[] args)  {
        String address = args[0]; /* i.e. http://localhost/ */
        String repositoryName = args[1]; /* i.e. myRepo */
        String graph = args[2]; /* i.e. http://rdf4j.org/schema/rdf4j#SHACLShapeGraph */

        ValueFactory valueFactory = SimpleValueFactory.getInstance();
        IRI graphIRI = valueFactory.createIRI(graph);
        System.out.println("graphIRI = " + graphIRI);

        HTTPRepository repository = new HTTPRepository(address, repositoryName);
        try (RepositoryConnection connection = repository.getConnection()) {
            connection.begin();
            connection.clear(graphIRI);
            connection.commit();
        }
    }
}