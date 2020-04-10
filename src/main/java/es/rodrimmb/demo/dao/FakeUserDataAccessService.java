package es.rodrimmb.demo.dao;

import es.rodrimmb.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(User.builder().id(id).name(user.getName()).build());
        return 1;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMatbe = selectUserById(id);
        if(userMatbe.isEmpty()) {
            return 0;
        }
        DB.remove(userMatbe.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, User userToUpdate) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if(indexOfUserToUpdate >= 0) {
                        DB.set(
                                indexOfUserToUpdate,
                                User.builder().id(id).name(userToUpdate.getName()).build()
                        );
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
