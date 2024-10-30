package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDate {
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        isFasterStartDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void isFasterStartDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일이 종료일보다 빠를 수 없습니다.");
        }
    }
}
