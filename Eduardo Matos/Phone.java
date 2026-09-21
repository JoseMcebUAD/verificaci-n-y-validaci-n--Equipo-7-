package toast.appback.src.users.domain;

import toast.appback.src.shared.domain.DomainError;
import toast.appback.src.shared.domain.Validators;
import toast.appback.src.shared.utils.result.Result;

import java.util.Objects;

public class Phone {

    private final String countryCode;
    private final String number;

    private Phone(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public static Result<Phone, DomainError> create(String countryCode, String number) {
        Result<Void, DomainError> result = Result.empty();
        result.collect(isValidCode(countryCode));
        result.collect(isValidPhoneNumber(number));
        if (result.isFailure()) {
            return result.castFailure();
        }
        return Result.ok(new Phone(countryCode, number));
    }

    public static Phone load(String countryCode, String number) {
        return create(countryCode, number)
                .orElseThrow(() -> new IllegalArgumentException("invalid phone data: " + countryCode + ", " + number));
    }

    private static Result<Void, DomainError> isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return Validators.emptyValue("number");
        }
        if (!phoneNumber.matches("\\d{4,15}")) {
            return Validators.invalidFormat("number", phoneNumber, "must contain only digits and be between 4 and 15 characters long");
        }
        return Result.ok();
    }

    private static Result<Void, DomainError> isValidCode(String code) {
        if (code == null || code.isBlank()) {
            return Validators.emptyValue("phoneCountryCode");
        }
        if (!code.matches("\\+\\d{1,4}")) {
            return Validators.invalidFormat("phoneCountryCode", code, "must start with '+' followed by 1 to 4 digits");
        }
        return Result.ok();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getValue() {
        return countryCode + "-" + number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneCountryCode='" + countryCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Phone phone)) return false;
        return Objects.equals(countryCode, phone.countryCode) && Objects.equals(number, phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }
}
