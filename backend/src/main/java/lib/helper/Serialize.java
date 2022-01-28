package lib.helper;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.n1analytics.paillier.PaillierPublicKey;
import com.n1analytics.paillier.cli.SerialisationUtil;

public class Serialize {
	public static ObjectNode serializePublicKey(BigInteger modulus) {
		ObjectNode data;
		ObjectMapper mapper = new ObjectMapper();
		 
	    data = mapper.createObjectNode();
	    data.put("alg", "PAI-GN1");
	    data.put("kty", "DAJ");

	    // Convert n to base64 encode
	    String encodedModulus = new String(Base64.encodeBase64URLSafeString(modulus.toByteArray()));
	    data.put("n", encodedModulus);

	    ArrayNode an = data.putArray("key_ops");
	    an.add("encrypt");
	    
	    return data;
	}
	
	public static PaillierPublicKey deserializePublicKey(String serializedPublicKey) {
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> publicKey;
		try {
			publicKey = mapper.readValue(serializedPublicKey, Map.class);
			
			return SerialisationUtil.unserialise_public(publicKey);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
