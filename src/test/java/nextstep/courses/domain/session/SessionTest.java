package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.PaymentType;
import nextstep.courses.domain.image.SessionImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    void 무료_강의_생성() {
        Session freeSession = Session.createFreeSession(1L, generateSessionDate(), generateSessionImage(), "클린코드");
        assertThat(freeSession)
                .extracting("title", "paymentType")
                .containsExactly("클린코드", PaymentType.FREE);
    }

    @Test
    void 유료_강의_생성() {
        Session paidSession = generatePaidSession();
        assertThat(paidSession)
                .extracting("title", "paymentType")
                .containsExactly("클린코드", PaymentType.PAID);
    }

    @Test
    void 무료_강의_신청() {
        Session freeSession = Session.createFreeSession(1L, generateSessionDate(), generateSessionImage(), "클린코드");
        NsUser user = NsUserTest.JAVAJIGI;
        freeSession.changeSessionRecruiting();
        freeSession.subscribe(user);

        assertThat(freeSession.getSubscribeCount()).isEqualTo(1);
    }

    @Test
    void 유료_강의_신청() {
        Session paidSession = generatePaidSession();
        NsUser user = NsUserTest.JAVAJIGI;
        paidSession.changeSessionRecruiting();
        paidSession.subscribePaidSession(user, generatePayment(paidSession, user));

        assertThat(paidSession.getSubscribeCount()).isEqualTo(1);
    }

    @Test
    void 유료_강의_신청_예외처리() {
        Session paidSession = generatePaidSession();
        NsUser user1 = NsUserTest.JAVAJIGI;
        NsUser user2 = NsUserTest.SANJIGI;
        paidSession.changeSessionRecruiting();
        paidSession.subscribe(user1);

        assertThatThrownBy(() -> paidSession.subscribePaidSession(user2, generatePayment(paidSession, user2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Session generatePaidSession() {
        return Session.createPaidSession(1L, generateSessionDate(), generateSessionImage(), "클린코드", 1,
                600000);
    }

    private SessionImage generateSessionImage() {
        return new SessionImage(1L, "next.jpg", 300, 200, 1000);
    }

    private SessionDate generateSessionDate() {
        return new SessionDate(LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31));
    }

    private Payment generatePayment(Session session, NsUser user) {
        return new Payment("id", session.getId(), user.getId(), 600000L);
    }
}
