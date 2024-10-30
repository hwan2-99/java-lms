package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Subscriber {
    private Long userId;
    private Long sessionId;
    private LocalDateTime subscriptionDate;

    public Subscriber(Long sessionId, Long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.subscriptionDate = LocalDateTime.now();
    }
}
