package org.emobon.shacl;
import org.eclipse.rdf4j.common.exception.ValidationException;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.RDF4J;
import org.eclipse.rdf4j.model.vocabulary.SHACL;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.WriterConfig;
import org.eclipse.rdf4j.rio.helpers.BasicWriterSettings;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateShacl {
    public static void main(String[] args)  {
        String address = args[0]; /* i.e. http://localhost/ */
        String repositoryName = args[1]; /* i.e. myRepo */
        String shacl = args[2];
        File shaclFile = new File(shacl);

        HTTPRepository repository = new HTTPRepository(address, repositoryName);
        try (RepositoryConnection connection = repository.getConnection()) {
            connection.begin();
            connection.clear(RDF4J.SHACL_SHAPE_GRAPH);
            connection.add(shaclFile, RDFFormat.TURTLE, RDF4J.SHACL_SHAPE_GRAPH);
            connection.commit();
        } catch (RepositoryException re){
            Throwable cause = re.getCause();
            if (cause instanceof ValidationException) {
                Model validationReportModel = ((ValidationException) cause).validationReportAsModel();
                Rio.write(validationReportModel, System.out, RDFFormat.TURTLE);
            }
            throw re;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
















