package in.doracorp.auth.util;

import in.doracorp.auth.exception.ServerException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;


import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class Sign {
    public static String generateToken(Map<String,Object> payload) {
        try {
            String privateKeyContent = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5ZQeU2UHu+jbQ" +
                    "x3mdZPxQ2aeMGuNrb9RWuah7GX7USC3j+X9satTIf8WsoI1FZSG4E3S3g8VZKplq" +
                    "BmxDwFIYSZBxnPDWccXKf1hs8OJ/KvXtxouJd7PUBtaXxib1rYOLZdC74b0bXB7B" +
                    "lh6SJY5flgkc4BwnRH1LsMzLVAVepsvdimSw5YU4UPL6RMDO7g0t0w7PwqHTNjHx" +
                    "1ETL43GAtxIBGPKC1mkKWeWCj4g8qOx7QK070Il3SqZ3Hs6LROyd9ia19cHeQNBn" +
                    "2SYZTWbNyXyu/YNo+JExQZZD3gm+6LPT9rY1cqjDT63smfRmFeScHPUqb0Yvqy2t" +
                    "98gHIuX9AgMBAAECggEAFr1/BzxO4L1C6VZOYzFL3FmWOj03aqo2OsezFBu1ztwQ" +
                    "+kb+4ObfCr4sGlc8fNKW0Y6xYQEsvRnYj4S81Y1K2efMFo4ljGg61p0gKjIHK4oI" +
                    "Iw5Dd1fTcE0WbVoaKpsJ6O39gyOBSqXWBUYgodSGziVdIMmXKnSK2d8/Kiw4LGUb" +
                    "tmJ20ySTGx06U4MJK7mxIL9ICoTU2M+RYOTCs1CTR+w92IeWOu5udP2/8oTwEVkC" +
                    "4LMJlhOySlQmqK0+H5o0iRq0gq3CSfz1vA6CtdTsbqTQJMmiCV+uYc8eoT39FDSK" +
                    "SPGulFX2qCxVlVUOPRJ32/lmGTOY6IWJJABvj0zikQKBgQDg7P7RsOuZLbLYNWU1" +
                    "TmQvmW1yOJ57MK8lpJbyuynjqNGn6KVHh5oz2jYFCuqRWNZB7px2LyNIW9KF7MKd" +
                    "GuEIS0zBGFKLYqqxG3Wa7jQfyzGA7TmGoXZO97NHGnSZAsGXQrWWnoLlV3G0EUpI" +
                    "A409SR/ZKYnDm0o+nymn3suYpwKBgQDTAe1zp5c2Zr+PjpBq44KI9hZwPuvIRpXW" +
                    "/3eMUe0awcn4cbPd2tvGreeCXT9mMYkSx7qwdCgrIfkX79iZ0yUhJlBNyxV8+g98" +
                    "YkqH2HAftGHtGWRRGM9rPNHvsmpM9H1w+daElWQDLD/g69RhCHNZy/a3dPl8FEeS" +
                    "6PbcL2r8uwKBgFpU08HDG9tYzwql5w+RfArhw2Kr+nnfA3hKsZSqNhiIJCsa88XW" +
                    "X8eTSz6bM7lLxECWY5/TLohQ2CuQ+le+A8Ig+6JQ8lWoOoUGUiT2pVhfcnIk04WO" +
                    "MsVkFUvpBAqvYLAuyl7DKi6Q/8khaIOjaYXDaFb+p6U3KIodwcz5XSrhAoGARQUj" +
                    "Y6pVFvFjBZUvfhcvMpe0XUDnpRoZlzr3IsoFkbQD2aVvnUkijImD6auQul/Ho4H7" +
                    "eg50uS8iqIbGxgI/6ej7aYbdX4T4HcqU8HyGqIg7Y+/jVXeVGVHKpeopRUTP522B" +
                    "ktq+3R7TQkJBtP+ro5rI99dmaATGKLLVVM6Z41MCgYEAnmV1p0D9+tdb3oru/Rhp" +
                    "fHs2vfSIrIr/ze+D409rwDPm+8Zg3kGbi3sB4d2wiacVq6ti9eyMdHoDi/Va79oo" +
                    "SCShaYmwfKZ1qTlNdb93ZJ54HMdHaRGS1g4yUPill2UXI7IayswFqwa0aeTgVke8" +
                    "Itf8KRjkamd1ICfWVd1WmlQ=";
            KeyFactory kf = KeyFactory.getInstance("RSA");
            Date issuedAt = new Date();
            Date expireAt = new Date(issuedAt.getTime() + 900000);
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            PrivateKey privateKey = kf.generatePrivate(keySpecPKCS8);
            return Jwts.builder().setClaims(payload).setIssuedAt(issuedAt).setExpiration(expireAt).signWith(SignatureAlgorithm.RS512, privateKey).compact();
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e.getMessage());
            throw new ServerException("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
