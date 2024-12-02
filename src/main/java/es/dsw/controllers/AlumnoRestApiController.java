package es.dsw.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.dsw.models.Alumno;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RestController
public class AlumnoRestApiController {
 
    // Mapa en memoria para almacenar los tokens generados (en memoria de aplicación).
    private static final Map<String, String> tokenStore = new HashMap<>();
    
    @GetMapping("/autenticacion")
    public ResponseEntity<String> autenticar(HttpServletRequest request) {
    	CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    	
        if (csrfToken == null) {
            return new ResponseEntity<>("Token CSRF no disponible", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
        String token = csrfToken.getToken();

        // Obtener el nombre del usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Almacenar el token asociado al usuario
        tokenStore.put(token, username);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
	 
	@GetMapping(value = "/getAll", produces="application/json") 
	public ResponseEntity<ArrayList<Alumno>> getAll(@RequestHeader("X-CSRF-TOKEN") String token){
		

        // Verificar si el token es válido
        if (!tokenStore.containsKey(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
		ArrayList<Alumno> objResultado = new ArrayList<Alumno>();
		
		objResultado.add(new Alumno("55665544A", "Alfredo", "Martín Pérez", 34, true));
		objResultado.add(new Alumno("55665543B", "Carlos", "Gutierrez Bueno", 22, true));
		objResultado.add(new Alumno("55665542B", "Susana", "Hernandez Martín", 45, true));
		objResultado.add(new Alumno("55665541C", "Beatriz", "Bueno Pérez", 50, false));
		objResultado.add(new Alumno("55665549C", "Alejandro", "Sanchez Montesdeoca", 38, true));
		objResultado.add(new Alumno("55662544D", "Abian", "Pérez Pérez", 76, false));
		objResultado.add(new Alumno("55664544E", "Gustavo", "Ortega Dorta", 28, true));
		objResultado.add(new Alumno("55665564F", "Hector", "Dorta Pérez", 30, true));
		objResultado.add(new Alumno("55665554G", "Begoña", "Betancort Hernandez", 31, true));
		objResultado.add(new Alumno("15665144H", "Sara", "Martín Pérez", 39, true));
		objResultado.add(new Alumno("25665544T", "Kilian", "Bueno Jimenez", 42, false));
		objResultado.add(new Alumno("35665544N", "Kevin", "Montesdeoca Betancort", 40, true));
		
		return new ResponseEntity<>(objResultado, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getOne", produces="application/json")
	public ResponseEntity<Alumno> getOne(@RequestHeader("X-CSRF-TOKEN") String token,  
			                             @RequestParam(defaultValue="") String nif) {
		
		
        // Verificar si el token es válido
        if (!tokenStore.containsKey(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
		if (nif.trim().equals("")) {
			return null;
		} else {
			return new ResponseEntity<>(new Alumno(nif, "Alfredo", "Martín Pérez", 34, true), HttpStatus.OK);
		}
		
	}
	
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> agregarAlumno(@RequestHeader("X-CSRF-TOKEN") String token, 
    		                                    @RequestBody Alumno alumno) {

        // Verificar si el token es válido
        if (!tokenStore.containsKey(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        return new ResponseEntity<>("Alumno recibido correctamente", HttpStatus.CREATED);
    }
    
 
}
