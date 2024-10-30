package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");


    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static void confirmImageExtension(String fileName) {
        String fileExtension = getFileExtension(fileName);
        boolean isValid = Arrays.stream(values())
                .anyMatch(imageExtension -> imageExtension.extension.equalsIgnoreCase(fileExtension));

        if (!isValid) {
            throw new IllegalArgumentException("지원하지 않는 확장자입니다.");
        }
    }

    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex + 1);
        }
        throw new IllegalArgumentException("확장자가 존재하지 않습니다.");
    }
}
