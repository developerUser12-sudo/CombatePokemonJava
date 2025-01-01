package Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Pokemon {

    private String nombrePokemon;
    private int vidaPokemon;
    private List<Integer> danioAtaques = new ArrayList<>();
    private List<String> nombreAtaques = new ArrayList<>();

    public Pokemon(String nombrePokemon, int vidaPokemon, List<Integer> danioAtaques, List<String> nombreAtaques) {
        this.nombrePokemon = nombrePokemon;
        this.vidaPokemon = vidaPokemon;
        this.danioAtaques = danioAtaques;
        this.nombreAtaques = nombreAtaques;
    }

    public static List<Pokemon> cargarPokemons(String nombres, String vidas, String danios, String ataques) {
        String[] nombresPokemonsArray = nombres.split(",");
        String[] vidasPokemonsArray = vidas.split(",");
        String[] daniosAtaquesArray = danios.split("/");
        String[] nombresAtaquesArray = ataques.split("/");

        List<Pokemon> pokemons = new ArrayList<>();
        for (int i = 0; i < nombresPokemonsArray.length; i++) {
            String nombreActual = nombresPokemonsArray[i];
            int vidaActual = Integer.parseInt(vidasPokemonsArray[i]);

            List<Integer> daniosActuales = new ArrayList<>();
            if (i < daniosAtaquesArray.length) {
                for (String d : daniosAtaquesArray[i].split(",")) {
                    daniosActuales.add(Integer.parseInt(d));
                }
            }

            List<String> ataquesActuales = new ArrayList<>();
            if (i < nombresAtaquesArray.length) {
                for (String a : nombresAtaquesArray[i].split(",")) {
                    ataquesActuales.add(a);
                }
            }

            pokemons.add(new Pokemon(nombreActual, vidaActual, daniosActuales, ataquesActuales));
        }

        return pokemons;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public int getVidaPokemon() {
        return vidaPokemon;
    }

    public void setVidaPokemon(int vidaPokemon) {
        this.vidaPokemon = vidaPokemon;
    }

    public List<Integer> getDanioAtaques() {
        return danioAtaques;
    }

    public List<String> getNombreAtaques() {
        return nombreAtaques;
    }

    @Override
    public String toString() {
        return nombrePokemon;
    }

    public static List<Pokemon> elegirPokemons(List<Pokemon> pokemons) {
        List<Pokemon> pokemonsElegidos = new ArrayList<>();
        Random random = new Random();
        while (pokemonsElegidos.size() < 2) {
            int n = random.nextInt(pokemons.size());
            Pokemon elegido = pokemons.get(n);
            if (!pokemonsElegidos.contains(elegido)) {
                pokemonsElegidos.add(elegido);
            }
        }
        return pokemonsElegidos;
    }

    public static void generateScanner(List<Pokemon> pokemonsElegidos) {
        boolean romper = false;
        int choose = 0;
        Random random = new Random();

        int turn = random.nextInt(2);
        Scanner sc = new Scanner(System.in);
        System.out.println("Comienzo de partida \n");
        System.out.println("Tu pokemon " + pokemonsElegidos.get(0) + "\n");
        System.out.println("Pokemon rival " + pokemonsElegidos.get(1) + "\n");
        if (turn == 1) {
            System.out.println("Empiezas tu \n");
            while (true) {
                turnoJugador(pokemonsElegidos, choose, sc, romper);
                if (pokemonsElegidos.get(1).getVidaPokemon() <= 0) {
                    System.out.println("Gana " + pokemonsElegidos.get(0).getNombrePokemon() + "\n");

                    break;
                }
                if (choose < pokemonsElegidos.get(0).getNombreAtaques().size()) {
                    turnoRival(random, pokemonsElegidos);

                    if (pokemonsElegidos.get(0).getVidaPokemon() <= 0) {
                        System.out.println("Gana " + pokemonsElegidos.get(1).getNombrePokemon());

                        break;
                    }
                }

            }
        } else {
            System.out.println("Empieza el rival \n");
            while (true) {
                if (choose < pokemonsElegidos.get(0).getNombreAtaques().size()) {
                    turnoRival(random, pokemonsElegidos);

                    if (pokemonsElegidos.get(0).getVidaPokemon() <= 0) {
                        System.out.println("Gana " + pokemonsElegidos.get(1).getNombrePokemon());

                        break;
                    }
                }
                turnoJugador(pokemonsElegidos, choose, sc, romper);
                if (pokemonsElegidos.get(1).getVidaPokemon() <= 0) {
                    System.out.println("Gana " + pokemonsElegidos.get(0).getNombrePokemon());

                    break;
                }
            }
        }

    }

    public static void turnoRival(Random random, List<Pokemon> pokemonsElegidos) {
        System.out.println("Turno del rival \n");
        System.out.println("Ataques: ");

        for (int i = 0; i < pokemonsElegidos.get(1).getNombreAtaques().size(); i++) {
            System.out.print(i + "- " + pokemonsElegidos.get(1).getNombreAtaques().get(i) + " ");
        }
        System.out.print("\n");
        int rivalChoose = random.nextInt(pokemonsElegidos.get(1).getNombreAtaques().size());
        pokemonsElegidos.get(0).setVidaPokemon(pokemonsElegidos.get(0).getVidaPokemon() - pokemonsElegidos.get(1).getDanioAtaques().get(rivalChoose));
        if (pokemonsElegidos.get(0).getVidaPokemon() <= 0) {
            pokemonsElegidos.get(0).setVidaPokemon(0);
            System.out.println(pokemonsElegidos.get(1).getNombrePokemon() + " ha lanzado " + pokemonsElegidos.get(1).getNombreAtaques().get(rivalChoose) + ". Ahora " + pokemonsElegidos.get(0).getNombrePokemon() + " tiene " + pokemonsElegidos.get(0).getVidaPokemon() + " de vida\n");
        } else {
            System.out.println(pokemonsElegidos.get(1).getNombrePokemon() + " ha lanzado " + pokemonsElegidos.get(1).getNombreAtaques().get(rivalChoose) + ". Ahora " + pokemonsElegidos.get(0).getNombrePokemon() + " tiene " + pokemonsElegidos.get(0).getVidaPokemon() + " de vida\n");

        }
    }

    public static void turnoJugador(List<Pokemon> pokemonsElegidos, int choose, Scanner sc, boolean romper) {
        while (true) {
            System.out.println("Tu turno \n");
            System.out.println("Ataques: ");

            for (int i = 0; i < pokemonsElegidos.get(0).getNombreAtaques().size(); i++) {
                System.out.print(i + "- " + pokemonsElegidos.get(0).getNombreAtaques().get(i) + " ");
            }
            System.out.print("\n");
            boolean inputCorrect = false;

            while (!inputCorrect) {
                if (sc.hasNextInt()) {
                    choose = sc.nextInt();
                    if (choose >= 0 && choose < pokemonsElegidos.get(0).getNombreAtaques().size()) {
                        inputCorrect = true;
                    } else {
                        System.out.println("Ataque incorrecto \n");
                        for (int i = 0; i < pokemonsElegidos.get(0).getNombreAtaques().size(); i++) {
                            System.out.print(i + "- " + pokemonsElegidos.get(0).getNombreAtaques().get(i) + " ");
                        }
                        System.out.println("\n");

                    }
                } else {
                    System.out.println("Por favor, ingresa un número valido \n");
                    for (int i = 0; i < pokemonsElegidos.get(0).getNombreAtaques().size(); i++) {
                        System.out.print(i + "- " + pokemonsElegidos.get(0).getNombreAtaques().get(i) + " ");
                    }
                    System.out.println("\n");
                    sc.next();
                }
            }
            for (int i = 0; i < pokemonsElegidos.get(0).getNombreAtaques().size(); i++) {

                if (choose == i) {
                    pokemonsElegidos.get(1).setVidaPokemon(pokemonsElegidos.get(1).getVidaPokemon() - pokemonsElegidos.get(0).getDanioAtaques().get(i));
                    if (pokemonsElegidos.get(1).getVidaPokemon() <= 0) {
                        pokemonsElegidos.get(1).setVidaPokemon(0);
                        System.out.println(pokemonsElegidos.get(0).getNombrePokemon() + " ha lanzado " + pokemonsElegidos.get(0).getNombreAtaques().get(i) + ". Ahora " + pokemonsElegidos.get(1).getNombrePokemon() + " tiene " + pokemonsElegidos.get(1).getVidaPokemon() + " de vida \n");
                    } else {
                        System.out.println(pokemonsElegidos.get(0).getNombrePokemon() + " ha lanzado " + pokemonsElegidos.get(0).getNombreAtaques().get(i) + ". Ahora " + pokemonsElegidos.get(1).getNombrePokemon() + " tiene " + pokemonsElegidos.get(1).getVidaPokemon() + " de vida \n");

                    }
                    romper = true;
                    break;
                }

            }
            if (romper) {
                break;
            }

        }
    }

}
