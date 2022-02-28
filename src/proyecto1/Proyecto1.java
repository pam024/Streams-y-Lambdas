package proyecto1;

/**
 *
 * @author pamela
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Proyecto1 {

    public static void main(String[] args) throws IOException{  
        String root = System.getProperty("user.dir"); //da acceso al directorio principal
        String path = root + File.separator + "src" + File.separator + "proyecto1" + File.separator + "Materias.csv"; //correr en NetBeans
        //String path = root + File.separator + "Materias.csv"; //busca la direccion exacta del archivo   

        try(Stream<String> streamFile = Files.lines(Paths.get(path))){//devuelve un Stream de lineas archivo
            List<Materia> finales = new ArrayList<>();
            
            //Lee archivo CSV y almacena en lista de materias
            finales = streamFile.map(linea -> linea.split(","))
                    .map(values -> {
                        String[] prereq = values[4].split(" ");
                        Materia materias = new Materia(values[0], values[1], Integer.parseInt(values[2]), values[3], Arrays.asList(prereq));
                        return materias;
                    }).collect(Collectors.toList());
            
            //1) Imprime la lista de materias
            System.out.println("LISTA DE MATERIAS:");
            finales.stream().forEach(s -> System.out.println(s));
            
            //2) Lista de materias ordenada por codigo y prerequisitos ordenados alfabeticamente
            System.out.println("\nLISTA ORDENADA POR CODIGO Y PREREQUISITOS ORDEN ALFABETICO:");
            finales.stream().sorted(Comparator.comparing(Materia::getCodigo)).forEach(p ->{
                   p.sortPrerequisitos();
                   System.out.println(p);
            });
            
            //3) Muestra lista de materias ordenadas por titulo y prerequisitos orden alfabeto inverso 
            System.out.println("\nLISTA ORDENADA POR TITULO Y PREREQUISITOS ORDEN ALFABETICO INVERSO:");
            finales.stream().sorted(Comparator.comparing(Materia::getTitulo)).forEach(p ->{
                p.sortPrerequisitosRev();    
                System.out.println(p);
            });
            
            //4) Lista materias agrupados por # creditos y ordenadas por titulo
            System.out.println("\nLISTA ORDENADA POR NUMS CREDITOS Y TITULO ORDENADO:");            
            Map<Integer, List<Materia>> groupbyCreds =
                finales.stream().collect(Collectors.groupingBy(Materia::getNumCreds));//toma lista original y va a la operacion terminal collect
                groupbyCreds.forEach(                                                 //numero creditos es la clave
                (creditos, materiaByCred) -> {//extrae cada entrada del mapa
                        System.out.printf("%n%s%n", creditos);
                        materiaByCred.stream().sorted(Comparator.comparing(Materia::getTitulo))
                                .forEach(
                                materia -> System.out.printf("   %s%n", materia));
                        });
                 
            //5) Lista materias agrupados por nivel y ordenadas por codigo    
            System.out.println("\nLISTA ORDENADA POR NIVEL Y CODIGO ORDENADO:");            
            Map<String, List<Materia>> groupbyNivel =
                finales.stream().collect(Collectors.groupingBy(Materia::getNivel));//toma lista original y va a la operacion terminal collect
                groupbyNivel.forEach(                                              //nivel es la clave
                (nivel, code) -> {
                        System.out.printf("%n%s%n", nivel);
                        code.stream().sorted(Comparator.comparing(Materia::getCodigo))
                                .forEach(
                                level -> System.out.printf("   %s%n", level));
                        });

            //6) Imprime lista materias que tengan como prerequisitos 2 o mas 'MAT' y ordenada por codigo
            System.out.println("\nLISTA MATERIAS CON 2 O MAS PREREQUISITOS 'MAT' Y ORDENADO POR CODIGO:");             
            finales.stream()
                  .filter(f -> f.getPrerequisitos().size()>=2)         
                  .filter(m -> m.getPrerequisitos().stream().anyMatch(a -> a.contains("MAT")))//devuelve los elemento del stream si coincide con el predicate 
                  .sorted(Comparator.comparing(Materia::getCodigo)).collect(Collectors.toList())
                   .forEach( s-> System.out.println(s));
            
            //7) Muestra lista materias que NO tengan como prerequisitos 'MAT' y ordenado por titulo
            System.out.println("\nLISTA MATERIAS SIN PREREQUISITOS 'MAT' Y ORDENADO POR TITULO:");            
            finales.stream().filter(f -> f.getPrerequisitos().stream().allMatch(a -> !a.contains("MAT")))//devuelve todos los elementos del stream que no coincidan con del predicate
                   .sorted(Comparator.comparing(Materia::getTitulo))
                   .forEach(s 
                           -> System.out.println(s));



        } 
        catch(IOException e){
            e.printStackTrace();
        }        
        
    }
        
}
