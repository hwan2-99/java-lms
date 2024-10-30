package nextstep.courses.domain.image;

public class SessionImage {
    private final String fileName;
    private final ImageSize imageSize;

    public SessionImage(String fileName, int width, int height, int volume) {
        ImageExtension.confirmImageExtension(fileName);
        this.fileName = fileName;
        this.imageSize = new ImageSize(width, height, volume);
    }
}
