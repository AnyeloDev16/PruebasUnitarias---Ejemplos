package services;

import java.util.List;
import models.Alumno;
import repositories.AlumnoRepository;

public class AlumnoServiceImpl implements AlumnoService{

    AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno findAlumnoForName(String nombre) {
    
        if(nombre == null){
            return null;
        }
        
        return this.alumnoRepository.findAll().stream()
                .filter((a) -> a.getNombre().equals(nombre))
                .findFirst().orElse(null);
    
    }

    @Override
    public boolean saveAlumno(Alumno alumno) {
    
        if(alumno == null || alumno.getNombre() == null || alumno.getNombre().isBlank()){
            return false;
        }
        
        return this.alumnoRepository.save(alumno);
        
    }
    
}
