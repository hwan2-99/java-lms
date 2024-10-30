package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    void 무료_강의_생성() {
        Session freeSession = Session.createFreeSession(1L, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31),
                generateSessionImage(), "클린코드");
        assertThat(freeSession)
                .extracting("title", "paymentType")
                .containsExactly("클린코드", PaymentType.FREE);
    }

    @Test
    void 유료_강의_생성() {
        Session paidSession = Session.createPaidSession(1L, LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31),
                generateSessionImage(), "클린코드", 100, 100000);
        assertThat(paidSession)
                .extracting("title", "paymentType")
                .containsExactly("클린코드", PaymentType.PAID);
    }

    private SessionImage generateSessionImage() {
        return new SessionImage(1L, "next.jpg", 300, 200, 1000);
    }
}
