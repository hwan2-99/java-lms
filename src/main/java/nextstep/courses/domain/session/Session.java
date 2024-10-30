package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.PaymentType;
import nextstep.courses.domain.image.SessionImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final SessionDate sessionDate;
    private final SessionImage sessionImage;
    private final PaymentType paymentType;
    private String title;
    private int subscribeMax;
    private int price;
    private SessionStatus sessionStatus;
    private List<Subscriber> subscribers = new ArrayList<>();

    private Session(Long id, SessionDate sessionDate, SessionImage sessionImage, PaymentType paymentType,
                    String title) {
        this(id, sessionDate, sessionImage, paymentType, title, Integer.MAX_VALUE, 0);
    }

    private Session(Long id, SessionDate sessionDate, SessionImage sessionImage, PaymentType paymentType,
                    String title, int subscribeMax, int price) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.sessionImage = sessionImage;
        this.paymentType = paymentType;
        this.title = title;
        this.sessionStatus = SessionStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
    }

    public static Session createFreeSession(Long id, SessionDate sessionDate, SessionImage sessionImage,
                                            String title) {
        return new Session(id, sessionDate, sessionImage, PaymentType.FREE, title);
    }

    public static Session createPaidSession(Long id, SessionDate sessionDate, SessionImage sessionImage,
                                            String title, int subscribeMax, int price) {
        return new Session(id, sessionDate, sessionImage, PaymentType.PAID, title, subscribeMax, price);
    }

    public void subscribe(NsUser user) {
        checkRecruiting();
        checkDuplicateSubscription(user);
        subscribers.add(new Subscriber(id, user.getId()));
    }

    public void subscribePaidSession(NsUser user, Payment payment) {
        validatePayment(payment.isSame(price));
        checkMaxSubscriber();
        subscribe(user);
    }

    private void checkRecruiting() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의는 모집중이 아닙니다.");
        }
    }

    private void checkMaxSubscriber() {
        if (subscribers.size() >= subscribeMax) {
            throw new IllegalArgumentException("이미 정원이 다 찬 강의입니다.");
        }
    }

    public void changeSessionRecruiting() {
        changeSessionStatus(SessionStatus.RECRUITING);
    }

    public void changeSessionClosed() {
        changeSessionStatus(SessionStatus.CLOSED);
    }

    private void changeSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    private void validatePayment(boolean isSameMoney) {
        if (!isSameMoney) {
            throw new IllegalArgumentException("결제금액과 강의 가격이 다릅니다.");
        }
    }

    private void checkDuplicateSubscription(NsUser user) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getUserId().equals(user.getId())) {
                throw new IllegalArgumentException("이미 이 강의에 수강신청한 사용자입니다.");
            }
        }
    }

    public int getSubscribeCount() {
        return subscribers.size();
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
