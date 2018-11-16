package br.com.libshare.favorites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {

	public List<FavoritesEntity> findByBook(Long bookCode);

	public List<FavoritesEntity> findByUser(Long userCode);
}