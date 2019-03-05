package checkout;

import java.time.LocalDate;

public interface WithExpirationDate {
    boolean isExpired(LocalDate currentDate);
}
