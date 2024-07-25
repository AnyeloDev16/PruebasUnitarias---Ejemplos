package repositories;

import java.util.List;
import models.Alumno;

public interface AlumnoRepository {
    
    List<Alumno> findAll();
    boolean save(Alumno alumno);
    
}
