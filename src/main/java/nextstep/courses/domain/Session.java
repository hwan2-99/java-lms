package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private final Long id;
    private final SessionDate sessionDate;
    private final SessionImage sessionImage;
    private final PaymentType paymentType;
    private String title;
    private int subscribeMax;
    private int price;
    private SessionStatus sessionStatus;

    private Session(Long id, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, PaymentType paymentType,
                    String title) {
        this.id = id;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionImage = sessionImage;
        this.paymentType = paymentType;
        this.title = title;
        this.sessionStatus = SessionStatus.READY;
    }

    private Session(Long id, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, PaymentType paymentType,
                    String title, int subscribeMax, int price) {
        this.id = id;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionImage = sessionImage;
        this.paymentType = paymentType;
        this.title = title;
        this.sessionStatus = SessionStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
    }

    public static Session createFreeSession(Long id, LocalDate startDate, LocalDate endDate, SessionImage sessionImage,
                                            String title) {
        return new Session(id, startDate, endDate, sessionImage, PaymentType.FREE, title);
    }

    public static Session createPaidSession(Long id, LocalDate startDate, LocalDate endDate, SessionImage sessionImage,
                                            String title, int subscribeMax, int price) {
        return new Session(id, startDate, endDate, sessionImage, PaymentType.PAID, title, subscribeMax, price);
    }
}
