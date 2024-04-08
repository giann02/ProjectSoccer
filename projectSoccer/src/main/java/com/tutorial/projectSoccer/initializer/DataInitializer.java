package com.tutorial.projectSoccer.initializer;

import com.tutorial.projectSoccer.model.Equipo;
import com.tutorial.projectSoccer.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EquipoRepository equipoRepository;

    @Autowired
    public DataInitializer(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (equipoRepository.count() == 0) {
            equipoRepository.saveAll(getEquipos());
        }
    }


    private List<Equipo> getEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo(1L,"Real Madrid", "La Liga", "España"));
        equipos.add(new Equipo(2L,"FC Barcelona", "La Liga", "España"));
        equipos.add(new Equipo(3L, "Manchester United", "Premier League", "Inglaterra"));
        equipos.add(new Equipo(4L, "Liverpool FC", "Premier League", "Inglaterra"));
        equipos.add(new Equipo(5L, "Juventus FC", "Serie A", "Italia"));
        equipos.add(new Equipo(6L, "AC Milan", "Serie A", "Italia"));
        equipos.add(new Equipo(7L, "Bayern Munich", "Bundesliga", "Alemania"));
        equipos.add(new Equipo(8L, "Borussia Dortmund", "Bundesliga", "Alemania"));
        equipos.add(new Equipo(9L, "Paris Saint-Germain", "Ligue 1", "Francia"));
        equipos.add(new Equipo(10L, "Olympique de Marseille", "Ligue 1", "Francia"));
        equipos.add(new Equipo(11L, "FC Porto", "Primeira Liga", "Portugal"));
        equipos.add(new Equipo(12L, "Sporting CP", "Primeira Liga", "Portugal"));
        equipos.add(new Equipo(13L, "Ajax Amsterdam", "Eredivisie", "Países Bajos"));
        equipos.add(new Equipo(14L, "Feyenoord", "Eredivisie", "Países Bajos"));
        equipos.add(new Equipo(15L, "Celtic FC", "Scottish Premiership", "Escocia"));
        equipos.add(new Equipo(16L, "Rangers FC", "Scottish Premiership", "Escocia"));
        equipos.add(new Equipo(17L, "Galatasaray SK", "Süper Lig", "Turquía"));
        equipos.add(new Equipo(18L, "Fenerbahçe SK", "Süper Lig", "Turquía"));
        equipos.add(new Equipo(19L, "FC Zenit Saint Petersburg", "Premier League Rusa", "Rusia"));
        equipos.add(new Equipo(20L, "Spartak Moscow", "Premier League Rusa", "Rusia"));
        equipos.add(new Equipo(21L, "SL Benfica", "Primeira Liga", "Portugal"));
        equipos.add(new Equipo(22L, "Besiktas JK", "Süper Lig", "Turquía"));
        equipos.add(new Equipo(23L, "SSC Napoli", "Serie A", "Italia"));
        equipos.add(new Equipo(24L, "Atlético Madrid", "La Liga", "España"));
        return equipos;
    }

}