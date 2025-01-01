package Pokemon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.List;
import org.springframework.context.annotation.Bean;

@Configuration
@PropertySource("classpath:pokemonsJoto.txt")
public class JotoPokemonConfig {

    @Value("${nombre}")
    private String nombresJoto;

    @Value("${vida}")
    private String vidasJoto;

    @Value("${ataques}")
    private String ataquesJoto;

    @Value("${danoAtaque}")
    private String daniosJoto;

    

    public String getNombresJoto() {
        return nombresJoto;
    }

    public void setNombresJoto(String nombresJoto) {
        this.nombresJoto = nombresJoto;
    }

    public String getVidasJoto() {
        return vidasJoto;
    }

    public void setVidasJoto(String vidasJoto) {
        this.vidasJoto = vidasJoto;
    }

    public String getAtaquesJoto() {
        return ataquesJoto;
    }

    public void setAtaquesJoto(String ataquesJoto) {
        this.ataquesJoto = ataquesJoto;
    }

    public String getDaniosJoto() {
        return daniosJoto;
    }

    public void setDaniosJoto(String daniosJoto) {
        this.daniosJoto = daniosJoto;
    }

   @Bean
    public List<Pokemon> jotoPokemons() {
        return Pokemon.cargarPokemons(nombresJoto, vidasJoto, daniosJoto, ataquesJoto);
    }
}
