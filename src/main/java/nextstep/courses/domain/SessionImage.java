package nextstep.courses.domain;

public class SessionImage {
    private final Long id;
    private final String fileName;
    private final ImageSize imageSize;

    public SessionImage(Long id, String fileName, int width, int height, int volume) {
        ImageExtension.confirmImageExtension(fileName);
        this.id = id;
        this.fileName = fileName;
        this.imageSize = new ImageSize(width, height, volume);
    }
}
