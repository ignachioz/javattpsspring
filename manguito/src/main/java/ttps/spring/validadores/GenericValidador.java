package ttps.spring.validadores;

import java.util.List;

public class GenericValidador {

	
	public static Boolean validarVacios(String propiedad) {
		return propiedad.isEmpty();
	}
	
	public static Boolean validarVacios(Double valor) {
		return String.valueOf(valor).isEmpty();
	}
	
	public static <T> Boolean validarVacios(List<T> valor) {
		return valor.size()==0;
		
	}
	
}
