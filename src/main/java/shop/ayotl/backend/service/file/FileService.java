package shop.ayotl.backend.service.file;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FileService {
    public boolean fileIsNull(MultipartFile file) {
        if (file == null) {
            return true;
        }

        final boolean isEmpty = file.isEmpty();
        final String contentType = file.getContentType();

        return contentType == null || isEmpty;
    }

    public String saveFile(
            MultipartFile multipartFile,
            String filename,
            String pathString
    ) throws IOException {
        if (! pathString.endsWith("/")) {
            pathString += "/";
        }

        final var targetDirectory = Paths.get(pathString);

        if (! targetDirectory.toFile().exists()) {
            Files.createDirectory(targetDirectory);
        }

        final String targetDirectoryCanonicalPath = targetDirectory.toFile().getCanonicalPath();

        final var file = new File(pathString + filename);
        final String fileCanonicalPath = file.getCanonicalPath();

        if (! fileCanonicalPath.startsWith(targetDirectoryCanonicalPath)) {
            throw new IOException("File and directory are different");
        }

        multipartFile.transferTo(file.toPath());

        return file.getPath();
    }

    public void deleteFile(String filePath) throws IOException {
        if (! StringUtils.hasText(filePath)) {
            return;
        }

        final var path = Paths.get(filePath);

        if (! path.toFile().exists()) {
            return;
        }

        Files.delete(path);
    }

    public String fileToBase64(File file) throws IOException {
        try (final var fileInputStream = new FileInputStream(file)) {
            final byte[] fileBytes = IOUtils.toByteArray(fileInputStream);
            final Base64.Encoder base64Encoder = Base64.getEncoder();
            final byte[] base64FileBytes = base64Encoder.encode(fileBytes);

            return new String(base64FileBytes, StandardCharsets.UTF_8);
        }
    }

    public String base64StringToDataUrl(final String base64String, final String mimeType) {
        return "data:" + mimeType + ";base64," + base64String;
    }

    public String fileToDataUrl(final File file, String mimeType) throws IOException {
        final var fileBase64 = fileToBase64(file);

        return base64StringToDataUrl(fileBase64, mimeType);
    }
}
