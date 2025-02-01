package nl.han.se.pizzanu.orderedpizzas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcOrderedPizzaRepository implements OrderedPizzaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(OrderedPizza orderedPizza) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(
                            "INSERT INTO orderedpizzas (pizzaid,finished) VALUES(?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderedPizza.getPizzaId());
            ps.setBoolean(2, orderedPizza.isFinished());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(OrderedPizza orderedPizza) {
        return jdbcTemplate.update("UPDATE orderedpizzas SET finished=? WHERE id = ?",
                new Object[]{orderedPizza.isFinished(), orderedPizza.getId()});
    }

    @Override
    public OrderedPizza findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * from orderedpizzas WHERE id =?",
                new Object[]{id},
                BeanPropertyRowMapper.newInstance(OrderedPizza.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM orderedpizzas WHERE id=?",
                id);
    }

    @Override
    public List<OrderedPizza> findAll() {
        return jdbcTemplate.query("SELECT * from orderedpizzas",
                BeanPropertyRowMapper.newInstance(OrderedPizza.class));
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
