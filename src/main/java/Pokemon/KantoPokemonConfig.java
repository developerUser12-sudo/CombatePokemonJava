package Pokemon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;
import org.springframework.context.annotation.Bean;

@Configuration
@PropertySource("classpath:pokemonsKanto.txt")
public class KantoPokemonConfig {

    @Value("${nombre}")
    private String nombresKanto;

    @Value("${vida}")
    private String vidasKanto;

    @Value("${ataques}")
    private String ataquesKanto;

    @Value("${danoAtaque}")
    private String daniosKanto;


    public KantoPokemonConfig() {
    }

    public String getNombresKanto() {
        return nombresKanto;
    }

    public String getVidasKanto() {
        return vidasKanto;
    }

    public String getAtaquesKanto() {
        return ataquesKanto;
    }

    public String getDaniosKanto() {
        return daniosKanto;
    }


     @Bean
    public List<Pokemon> kantoPokemons() {
        return Pokemon.cargarPokemons(nombresKanto, vidasKanto, daniosKanto, ataquesKanto);
    }
}
