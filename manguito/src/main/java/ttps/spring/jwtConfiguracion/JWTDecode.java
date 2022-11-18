package ttps.spring.jwtConfiguracion;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTDecode {

	public static DecodedJWT decodeJWT(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256("privada"))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
		DecodedJWT jwt = verifier.verify(token);
		//verificarCaducidad(jwt);
		return jwt;
	}
	
	private static boolean verificarCaducidad(DecodedJWT token) {
		//token.getExpiresAt()
		return true;
	}
	
}
