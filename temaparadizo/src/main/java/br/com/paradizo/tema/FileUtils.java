package br.com.paradizo.tema;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;

public class FileUtils {

    public static String getResourceContents(String resource) {
        try {
            Path resourcePath = getResourceAsPath(resource);
            return getPathContents(resourcePath);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPathContents(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    private static Path getResourceAsPath(String resource) throws URISyntaxException {
        URI uri = FileUtils.class.getResource(resource).toURI();

        if (isResourceInJar(uri)) {
            return getResourceFromJar(uri);
        } else {
            return Paths.get(uri);
        }
    }

    private static Path getResourceFromJar(URI fullURI) {
        String[] uriParts = fullURI.toString().split("!");
        URI jarURI = URI.create(uriParts[0]);
        FileSystem fs;

        try {
            fs = FileSystems.newFileSystem(jarURI, Collections.<String, String>emptyMap());
        } catch (IOException e) {
            fs = FileSystems.getFileSystem(jarURI);
        }
        String resourceURI = uriParts[1];
        return fs.getPath(resourceURI);
    }

    private static boolean isResourceInJar(URI uri) {
        return uri.getScheme().equals("jar");
    }

}
