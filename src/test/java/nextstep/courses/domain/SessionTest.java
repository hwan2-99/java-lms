package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.courses.domain.image.SessionImage;
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
        Session paidSession = Session.createPaidSession(1L, generateSessionDate(), generateSessionImage(), "클린코드", 100, 100000);
        assertThat(paidSession)
                .extracting("title", "paymentType")
                .containsExactly("클린코드", PaymentType.PAID);
    }

    @Test
    void 무료_강의_신청() {
        Session freeSession = Session.createFreeSession(1L, generateSessionDate(), generateSessionImage(), "클린코드");
        NsUser user = NsUserTest.JAVAJIGI;
        freeSession.changeSessionRecruiting();
        freeSession.subscribeFreeSession(user);

        assertThat(freeSession.getSubscribeCount()).isEqualTo(1);
    }

    private SessionImage generateSessionImage() {
        return new SessionImage(1L, "next.jpg", 300, 200, 1000);
    }

    private SessionDate generateSessionDate() {
        return new SessionDate(LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31));
    }
}
