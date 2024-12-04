package shop.ayotl.backend.service.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import shop.ayotl.backend.common.constant.DatePattern;
import shop.ayotl.backend.converter.date.DateConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class FileService {
    public boolean fileIsNull(MultipartFile file) {
        if (file == null) {
            return true;
        }

        final var isEmpty = file.isEmpty();
        final var contentType = file.getContentType();

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

        final var targetDirectoryCanonicalPath = targetDirectory.toFile().getCanonicalPath();
        final var fileNameOnly = FilenameUtils.getBaseName(filename);
        final var extension = FilenameUtils.getExtension(filename);
        final var now = LocalDateTime.now();

        final var file = new File(
                pathString + fileNameOnly + " - " +
                        DateConverter.temporalToString(now, DatePattern.DD_MM_YYYY_HH_MM_SS)
                        + "." + extension
        );
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
