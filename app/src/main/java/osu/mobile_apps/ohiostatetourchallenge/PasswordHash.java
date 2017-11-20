package osu.mobile_apps.ohiostatetourchallenge;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

class PasswordHash {

    static String hashPassword(String password) {
        final HashCode hashCode = Hashing.sha1().hashString(password, Charset.defaultCharset());
        return hashCode.toString();
    }

    static Boolean checkHashEquality(String hashValue, String valueToHash) {
        return hashValue.equals(hashPassword(valueToHash));
    }
}
