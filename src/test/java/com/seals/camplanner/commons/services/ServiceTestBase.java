package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.models.BaseEntity;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServiceTestBase<T extends BaseEntity> {

    public static final Random RANDOM = new Random();

    protected abstract BaseServiceImpl<T> getService();

    protected abstract JpaRepository<T, Long> getRepositoryMock();

    protected abstract T getSample();

    @Test
    public void findByIdTest() {
        T expected = this.getSample();
        Long id = expected.getId();
        Mockito.when(this.getRepositoryMock().findById(id)).thenReturn(Optional.of(expected));
        T actual = this.getService().findById(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdNotFoundTest() {
        Long id = RANDOM.nextLong();
        Mockito.when(this.getRepositoryMock().findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> this.getService().findById(id));
    }

    @Test
    public void findAllTest() {
        T location1 = this.getSample();
        T location2 = this.getSample();
        T location3 = this.getSample();
        List<T> expected = List.of(location1, location2, location3);
        Mockito.when(this.getRepositoryMock().findAll()).thenReturn(expected);
        List<T> actual = this.getService().findAll();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(actual.containsAll(expected));
    }

    @Test
    public void findAllEmptyTest() {
        List<T> expected = List.of();
        Mockito.when(this.getRepositoryMock().findAll()).thenReturn(expected);
        List<T> actual = this.getService().findAll();
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    public void saveTest() {
        T toSave = this.getSample();
        toSave.setId(null);
        long newId = RANDOM.nextLong();
        Mockito.when(this.getRepositoryMock().save(toSave)).thenAnswer(invocation -> {
            T saved = this.getSample();
            BeanUtils.copyProperties(toSave, saved);
            saved.setId(newId);
            return saved;
        });
        T saved = this.getService().save(toSave);
        Mockito.verify(this.getRepositoryMock()).save(toSave);
        Assertions.assertEquals(newId, saved.getId());
        toSave.setId(newId);
        Assertions.assertEquals(toSave, saved);
    }

    @Test
    public void deleteByIdTest() {
        Long idToDelete = RANDOM.nextLong();
        this.getService().deleteById(idToDelete);
        Mockito.verify(this.getRepositoryMock()).deleteById(idToDelete);
    }

    @Test
    public void deleteAllTest() {
        this.getService().deleteAll();
        Mockito.verify(this.getRepositoryMock()).deleteAll();
    }
}
