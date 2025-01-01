package Pokemon;

import static Pokemon.Pokemon.elegirPokemons;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PokemonConfig.class, KantoPokemonConfig.class, JotoPokemonConfig.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción:");
        System.out.println("1. Cargar Pokémon desde Kanto");
        System.out.println("2. Cargar Pokémon desde Johto");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        List<Pokemon> pokemons = null;

        if (opcion == 1) {
            pokemons = context.getBean("kantoPokemons", List.class);
            System.out.println("Se han cargado los Pokémon de Kanto.");
        } else if (opcion == 2) {
            pokemons = context.getBean("jotoPokemons", List.class);
            System.out.println("Se han cargado los Pokémon de Johto.");
        } else {
            System.out.println("Opción no válida. Se cargarán los Pokémon de forma aleatoria.");
            pokemons = context.getBean("kantoPokemons", List.class); 
        }
        List<Pokemon> pokemonsElegidos = elegirPokemons(pokemons);
         Pokemon.generateScanner(pokemonsElegidos);
        context.close();
    }
}
