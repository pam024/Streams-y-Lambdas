package proyecto1;

/**
 *
 * @author pamela
 */

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Materia{
   private String codigo; // codigo de 6 caracteres
   private String titulo;
   private int numeroCred;
   private String nivel; // Pre, Pos, Tec
   private List<String> prerequisitos;
   
   public Materia(String codigo, String titulo, int numeroCred, String nivel, List<String> prerequisitos) {
      this.codigo = codigo;
      this.titulo = titulo;
      this.numeroCred = numeroCred;
      this.nivel = nivel;
      this.prerequisitos = prerequisitos;
   }
   
   //Set codigo
   public void setCodigo(String codigo){
       this.codigo = codigo;
   }
   
   //Set titulo
   public void setTitulo(String titulo){
       this.titulo = titulo;
   }
   
   //Set creditos
   public void setCreditos(int numeroCred){
       this.numeroCred = numeroCred;
   }
   
   //set nivel
   public void setNivel(String nivel){
       this.nivel = nivel;
   }   
   
   //Set prerequisitos
   public void setPrerequisitos(List<String> prerequisitos){
       this.prerequisitos = prerequisitos;
   }
   
   // Get codigo
   public String getCodigo() {
      return codigo;
   }

   // Get titulo
   public String getTitulo() {
      return titulo;
   }

   // Get numeroCreditos
   public int getNumCreds() {
      return numeroCred;
   }

   // Get nivel
   public String getNivel() {
      return nivel;
   }

   // Get prerequisitos
   public List<String> getPrerequisitos() {
      return prerequisitos;
   }
   
   //Ordena prerequisitos alfabeticamente
   public List<String> sortPrerequisitos(){
       this.prerequisitos = prerequisitos.stream().sorted().collect(Collectors.toList());
       return prerequisitos;
   }
   
   //Ordena prerequisitos inverso alfabeticamente
   public List<String> sortPrerequisitosRev(){
       this.prerequisitos = prerequisitos.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
       return prerequisitos;
   }

   @Override
   public String toString() {
      return String.format("%-8s %-20s %-8s %-8s %-8s", codigo, titulo, numeroCred, nivel, prerequisitos);
   }
}
