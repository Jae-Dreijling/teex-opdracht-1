package nl.han.se.pizzanu.pizzas;

import nl.han.se.pizzanu.pizzas.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Pizza pizza) {
        return jdbcTemplate.update("INSERT INTO pizzas (name, description) VALUES(?,?)",
                new Object[] { pizza.getName(), pizza.getDescription() });
    }

    @Override
    public int update(Pizza pizza) {
        return jdbcTemplate.update("UPDATE pizzas SET name=?, description=?",
                new Object[] { pizza.getName(), pizza.getDescription(), pizza.getId() });
    }

    @Override
    public Pizza findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM pizzas WHERE id=?",
                BeanPropertyRowMapper.newInstance(Pizza.class), id);
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM pizzas WHERE id=?", id);
    }

    @Override
    public List<Pizza> findAll() {
        return jdbcTemplate.query("SELECT * from pizzas", BeanPropertyRowMapper.newInstance(Pizza.class));
    }

    @Override
    public List<Pizza> findByNameContaining(String name) {
        return jdbcTemplate.query("SELECT * from pizzas WHERE name LIKE '%" + name + "%'", BeanPropertyRowMapper.newInstance(Pizza.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from pizzas");
    }
}
