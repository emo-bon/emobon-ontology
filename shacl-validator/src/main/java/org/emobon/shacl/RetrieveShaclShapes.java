package org.emobon.shacl;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.RDF4J;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.WriterConfig;
import org.eclipse.rdf4j.rio.helpers.BasicWriterSettings;

import java.util.stream.Collectors;

public class RetrieveShaclShapes {
    public static void main(String[] args)  {
        String address = args[0]; /* i.e. http://localhost/ */
        String repositoryName = args[1]; /* i.e. myRepo */

        HTTPRepository repository = new HTTPRepository(address, repositoryName);
        try (RepositoryConnection connection = repository.getConnection()) {
            Model statementsCollector = new LinkedHashModel(
                    connection.getStatements(null, null,null, RDF4J.SHACL_SHAPE_GRAPH)
                    .stream()
                    .collect(Collectors.toList()));
            Rio.write(statementsCollector, System.out, RDFFormat.TURTLE, new WriterConfig().set(
                    BasicWriterSettings.INLINE_BLANK_NODES, true));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}