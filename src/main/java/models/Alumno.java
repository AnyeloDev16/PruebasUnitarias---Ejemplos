package models;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    
    private String nombre;
    private List<Integer> notas;

    public Alumno(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getNotas() {
        return notas;
    }

    public void setNotas(List<Integer> notas) {
        this.notas = notas;
    }
    
    public boolean agregarNota(Integer nota){
        return (nota == null || nota > 20 || nota < 0) ? false : notas.add(nota);
    }
    
    public double calcularPromedio(){
        return notas.stream()
                .reduce((int1, int2) -> int1+int2).orElse(0)/((notas.isEmpty()) ? 1 : notas.size());
    }
    
}
