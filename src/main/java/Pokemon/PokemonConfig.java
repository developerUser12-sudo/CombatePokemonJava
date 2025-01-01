package Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;

@Configuration
public class PokemonConfig {

    @Autowired
    @Qualifier("kantoPokemons")
    private List<Pokemon> kantoPokemons;

    @Autowired
    @Qualifier("jotoPokemons")
    private List<Pokemon> jotoPokemons;

    @Bean
    public List<Pokemon> pokemonsElegidos() {
        Random random = new Random();
        return random.nextBoolean() ? kantoPokemons : jotoPokemons;
    }
}
