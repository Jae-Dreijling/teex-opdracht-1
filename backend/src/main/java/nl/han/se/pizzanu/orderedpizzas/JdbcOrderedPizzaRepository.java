package nl.han.se.pizzanu.orderedpizzas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderedPizzaRepository implements OrderedPizzaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(OrderedPizza object) {
        return 0;
    }

    @Override
    public int update(OrderedPizza object) {
        return 0;
    }

    @Override
    public OrderedPizza findById(Long aLong) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM orderedpizzas WHERE id=?", id);
    }

    @Override
    public List<OrderedPizza> findAll() {
        return jdbcTemplate.query("SELECT * from orderedpizzas", BeanPropertyRowMapper.newInstance(OrderedPizza.class));
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
