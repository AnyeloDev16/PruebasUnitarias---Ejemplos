package services;

import models.Alumno;

public interface AlumnoService {
    
    Alumno findAlumnoForName(String nombre);
    boolean saveAlumno(Alumno alumno);
    
    
}
