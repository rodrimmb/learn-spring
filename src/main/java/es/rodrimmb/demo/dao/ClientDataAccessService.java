package es.rodrimmb.demo.dao;

import es.rodrimmb.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ClientDataAccessService implements ClientDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertClient(UUID id, Client client) {
        final String sql = "INSERT INTO client (id, name) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, id, client.getName());
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public Optional<Client> selectClientById(UUID id) {
        final String sql = "SELECT * FROM client WHERE id = ?";
        Client client = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> Client.builder()
                    .id(UUID.fromString(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .build()
        );
        return Optional.ofNullable(client);
    }

    @Override
    public List<Client> selectAllClient() {
        final String sql = "SELECT * FROM client";
        return jdbcTemplate.query(sql, (resultSet, i) -> Client.builder()
                    .id(UUID.fromString(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .build()
        );
    }

    @Override
    public int deleteClientById(UUID id) {
        final String sql = "DELETE FROM client WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }

    @Override
    public int updateClientById(UUID id, Client client) {
        final String sql = "UPDATE client SET name = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, client.getName(), id);
            return 1;
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
