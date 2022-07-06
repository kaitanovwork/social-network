package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.service.abst.entity.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbstractServiceImpl<T, PK> implements AbstractService<T, PK> {

    private final JpaRepository<T, PK> jpaRepository;

    @Override
    public Optional<T> findById(PK id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    @Transactional
    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        jpaRepository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(PK id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(PK id) {
        return jpaRepository.existsById(id);
    }
}
